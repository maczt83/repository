/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.services;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import javax.xml.bind.DatatypeConverter;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import static org.springframework.http.RequestEntity.post;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.tags.UrlTag;

/**
 *
 * @author chris
 */
@Service
public class PaymentGWUtilService {
    
    private final String storeName = "sdk_test";
    private final String apiKey = "86af3-80e4f-f8228-9498f-910ad";
    private final String url = "https://test.paymentgateway.hu/api/rest/";

    public String encodeBase64UserPass() throws UnsupportedEncodingException {
        String authStr = storeName + ":" + apiKey;
        String authStrEncoded = DatatypeConverter.printBase64Binary(authStr.getBytes("UTF-8"));
        return authStrEncoded;
    }

    public HttpPost httpSetConnectionParameters() throws UnsupportedEncodingException {
        HttpPost httppost = new HttpPost(url);
        httppost.setHeader("Authorization", "Basic " + encodeBase64UserPass());
        httppost.setHeader("Content-type", "application/x-www-form-urlencoded");
        return httppost;
    }
    
    public Calendar convertStringToCalendar(String dateInString ) throws ParseException {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        cal.setTime(sdf.parse(dateInString));// all done
        return cal;
    }

    public String getUrl() {
        return url;
    }
    
}
