package progmatic.bookingmanager.services;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import progmatic.bookingmanager.databaseEntity.Booking;
import progmatic.bookingmanager.databaseEntity.EmailTemplate;
import progmatic.bookingmanager.databaseEntity.Guest;
import progmatic.bookingmanager.databaseEntity.PaymentGateway;
import progmatic.bookingmanager.dtos.GuestDto;
import progmatic.bookingmanager.databaseEntity.PaymentTransaction;
import progmatic.bookingmanager.dtos.PaymentLinkGeneratorDto;
import progmatic.bookingmanager.dtos.PaymentLinkDetailRequestToBackendDto;
import progmatic.bookingmanager.dtos.PaymentLinkDetailsDto;
import progmatic.bookingmanager.dtos.PaymentLinkRelatedTransactionDto;
import progmatic.bookingmanager.dtos.PaymentLinkRequestToBackendDto;
import progmatic.bookingmanager.dtos.PaymentLinkRequestToServerDto;
import progmatic.bookingmanager.repositories.BookingRepository;
import progmatic.bookingmanager.repositories.EmailTemplateRepository;
import progmatic.bookingmanager.repositories.GuestRepository;
import progmatic.bookingmanager.repositories.PaymentGatewayRepository;
import progmatic.bookingmanager.repositories.PaymentTransactionRepository;

@Service
public class PaymentGatewayService {
    
    private static final Logger LOG = LoggerFactory.getLogger(PaymentGatewayService.class);
    
    @Autowired
    PaymentGWUtilService paymentGWUtilService;

    @Autowired
    PaymentGatewayRepository gatewayRepository;

    @Autowired
    BookingRepository bookingRepo;

    @Autowired
    PaymentTransactionRepository paymentTransactionRepository;
    
    @Autowired
    EmailService emailService;
    
    @Autowired
    EmailTemplateRepository emailTemplateRepository;
    
    @Autowired
    GuestsService guestService;

    @Transactional
    public String giveTheAnswerToAppAndDoDBChanges(PaymentLinkRequestToBackendDto plrbdto) throws UnsupportedEncodingException, IOException, ParseException {
        Booking actBooking = getBookingByIdFromUrl(plrbdto);
        String userId = actBooking.getGuestId().getId() + "";
        String requestJsonToPGW = createRequestBodyToPGW(plrbdto, userId);
        HttpResponse responseFromPGW = getResponseFromPGW(requestJsonToPGW);
        String responseJsonFromPGW = getResponseJsonFromPGW(responseFromPGW);
        Gson gson = new Gson();
        PaymentLinkGeneratorDto plgdto = gson.fromJson(responseJsonFromPGW, PaymentLinkGeneratorDto.class);
        PaymentGateway paymentGateway = createNewPaymentGateway(requestJsonToPGW, actBooking);
        addDataAndResponseJsonToPaymentGateway(paymentGateway, responseJsonFromPGW, plgdto);
        sendAutomaticEmailWithPaymentLink(paymentGateway);
        String paymentLink = paymentGateway.getPaymentLink();
        if (paymentLink != null) {
            return paymentLink;
        }
        return "Nem sikerült a kérés, ismételd meg!";
    }

    public String getResponseJsonFromPGW(HttpResponse responseFromPGW) throws IOException, ParseException {
        String line;
        StringBuilder result = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(responseFromPGW.getEntity().getContent()));
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        String responseJson = result.toString();
        return responseJson;
    }

    @Transactional
    public PaymentGateway createNewPaymentGateway(String requestJson, Booking actBooking) {
        PaymentGateway paymentGateway = new PaymentGateway();
        paymentGateway.setRequestData(requestJson);
        paymentGateway.setBookingId(actBooking);
        gatewayRepository.save(paymentGateway);
        return paymentGateway;
    }

    @Transactional
    public PaymentGateway addDataAndResponseJsonToPaymentGateway(PaymentGateway paymentGateway, String responseJson, PaymentLinkGeneratorDto plgdto) throws ParseException {
        paymentGateway.setResponseData(responseJson);
        paymentGateway.setAmount(BigDecimal.valueOf(Double.parseDouble(plgdto.getAmount())));
        paymentGateway.setCreationDate(plgdto.getCreated());
        paymentGateway.setLastUpdate(plgdto.getLastModified());
        paymentGateway.setPaymentLink(plgdto.getPaymentLinkUrl());
        paymentGateway.setPaymentLinkName(plgdto.getPaymentLinkName());
        paymentGateway.setPaymentLinkStatus(plgdto.getStatus());
        paymentGateway.setCurrency(plgdto.getCurrency());
        gatewayRepository.save(paymentGateway);
        return paymentGateway;
    }

    public Booking getBookingByIdFromUrl(PaymentLinkRequestToBackendDto plrbdto) {
        long id = Long.parseLong(plrbdto.getBookingId());
        Booking actBooking = bookingRepo.findById(id);
        return actBooking;
    }

    public HttpResponse getResponseFromPGW(String requestJsonToPGW) throws IOException {
        Gson gson = new Gson();
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = paymentGWUtilService.httpSetConnectionParameters();
        String encodedRequestJsonToPGW = "method=PaymentLinkCreate&json=" + URLEncoder.encode((requestJsonToPGW), "UTF-8");
        StringEntity postingString = new StringEntity(encodedRequestJsonToPGW);
        post.setEntity(postingString);
        HttpResponse execute = httpClient.execute(post);
        return execute;
    }

    private String createRequestBodyToPGW(PaymentLinkRequestToBackendDto plrbdto, String userId) throws UnsupportedEncodingException {
        PaymentLinkRequestToServerDto plrsdto = new PaymentLinkRequestToServerDto(plrbdto, userId);
        Gson gson = new Gson();
        return gson.toJson(plrsdto);
    }

    public PaymentLinkDetailsDto processResponseBodyForPaymentLinkDetails(HttpResponse response) throws IOException, ParseException {
        String line;
        String paymentLinkName = null;
        StringBuilder result = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        Gson gson = new Gson();
        String responseJson = result.toString();
        PaymentLinkDetailsDto linkDetails = gson.fromJson(responseJson, PaymentLinkDetailsDto.class);
        return linkDetails;
    }

    private String getPaymentLinkNameFromDetailsResponse(PaymentLinkDetailsDto PGWLinkDetailsResponse) {
        String paymentLinkName = null;
        String[] commonData = PGWLinkDetailsResponse.getCommonData().split(",");
        for (int i = 0; i < commonData.length; i++) {
            if (commonData[i].contains("PaymentLinkName")) {
//                \"PaymentLinkName\":\"pl_9871b283c2759d574b3f6108729e0054\"
                String[] beforeParsing = commonData[i].split(":");
                paymentLinkName = beforeParsing[1].replaceAll("\"", "");
                paymentLinkName = paymentLinkName.trim();
                break;
            }
        }
        return paymentLinkName;
    }

    private String createPaymentLinkDetailsBodyToPGW(PaymentLinkDetailRequestToBackendDto paymentLinkName) throws UnsupportedEncodingException {
        String linkName = "{\"PaymentLinkName\":" + "\"" + paymentLinkName.getPaymentLinkName() + "\"}";
        return "method=PaymentLinkDetails&json=" + URLEncoder.encode(linkName, "UTF-8");
    }

    public HttpResponse getPaymentLinkDetails(PaymentLinkDetailRequestToBackendDto paymentLinkName) throws UnsupportedEncodingException, IOException {
        Gson gson = new Gson();
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = paymentGWUtilService.httpSetConnectionParameters();
        String paymentLinkDetailsBodyToPGW = createPaymentLinkDetailsBodyToPGW(paymentLinkName);
        StringEntity postingString = new StringEntity(paymentLinkDetailsBodyToPGW);
        post.setEntity(postingString);
        HttpResponse execute = httpClient.execute(post);
        return execute;
    }
 
    public String getPaymentLinkFromDBByBookingId(Long id) {
        List<PaymentGateway> findByBookingIdOrderByCreationDateDesc = gatewayRepository.findByBookingIdOrderByCreationDateDesc(bookingRepo.findById(id));
        return findByBookingIdOrderByCreationDateDesc.get(0).getPaymentLink();
    }
    
    private void sendAutomaticEmailWithPaymentLink(PaymentGateway paymentGateway){
        Mail mail = new Mail();
        Guest guest = paymentGateway.getBookingId().getGuestId();
        LOG.info(guest.toString());
        List<GuestDto> allguests = guestService.allGuestsAndCompanies();
        GuestDto guestWithFullData = new GuestDto();
        for (GuestDto allguest : allguests) {
            if (guest.getId() == allguest.getId()){
                guestWithFullData = allguest;
            }
        }
        LOG.info("sending email to: "+guest.getEmailAddress());
        mail.setTo(guest.getEmailAddress());
//        mail.setTo("bookingproj123@gmail.com");
        EmailTemplate emailTemplate = emailTemplateRepository.findById(2);
        mail.setSubject(emailTemplate.getSubject());
        String html = emailTemplate.getHtml().replaceAll("%amount%", paymentGateway.getAmount().toString() + " " + paymentGateway.getCurrency());
        String name = (guestWithFullData.getFirstName() == null) ? 
                guestWithFullData.getCompanyName() : 
                guestWithFullData.getFirstName() + " " + guestWithFullData.getLastName();
        html = html.replaceAll("%nameOfUser%", name);
                
        html = html.replaceAll("%paymentLink%", paymentGateway.getPaymentLink());                
        mail.setText(html);
        emailService.sendMail(mail);
    }


    public void addTransactionDataToDB(PaymentLinkDetailsDto paymentLinkDetails) throws ParseException {
        String linkName = getPaymentLinkNameFromDetailsResponse(paymentLinkDetails);
        Long paymentGatewayId = null;
        paymentGatewayId = gatewayRepository.findByPaymentLinkName(linkName).getId();
        List<PaymentLinkRelatedTransactionDto> paymentTransactions = paymentLinkDetails.getRelatedTransactions();
        for (PaymentLinkRelatedTransactionDto paymentTransaction1 : paymentTransactions) {
            if (paymentGatewayId != null) {
                PaymentTransaction exist = null;
                exist = paymentTransactionRepository.findByTransactionId(paymentTransaction1.getTransactionId());
                if (exist == null) {
                    PaymentTransaction paymentTransaction = new PaymentTransaction();
                    paymentTransaction.setPaymentGatewayId(gatewayRepository.findByPaymentLinkName(linkName));
                    paymentTransaction.setTransactionId(paymentTransaction1.getTransactionId());
                    paymentTransaction.setProviderTransactionId(paymentTransaction1.getProviderTransactionId());
                    paymentTransaction.setCommitState(paymentTransaction1.getCommitState());
                    paymentTransaction.setAutoCommit(paymentTransaction1.getAutoCommit());
                    paymentTransaction.setCreationDate(paymentGWUtilService.convertStringToCalendar(paymentTransaction1.getCreated()));
                    paymentTransaction.setResultMessage(paymentTransaction1.getResultMessage());
                    paymentTransaction.setResultCode(paymentTransaction1.getResultCode());
                    paymentTransactionRepository.save(paymentTransaction);
                    if (paymentTransaction1.getResultCode().equals("SUCCESSFUL")) {
                        PaymentGateway linkStatusUpdate = gatewayRepository.findByPaymentLinkName(linkName);
                        linkStatusUpdate.setPaymentLinkStatus("SUCCESSFUL");
                        linkStatusUpdate.setLastUpdate(paymentTransaction1.getCreated());
                        gatewayRepository.save(linkStatusUpdate); 
                    }
                }  
            }
        } 
    }    

    

//    public String createPaymentLinkDetailsResponseToFrontend() {
//
//    }

}
