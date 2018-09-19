package progmatic.bookingmanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import progmatic.bookingmanager.databaseEntity.EmailTemplate;
import progmatic.bookingmanager.services.EmailTemplateService;

/**
 *
 * @author Attila
 */
@Controller
public class EmailTemplateController {
    
    @Autowired
    EmailTemplateService emailTemplateService;
    
    @RequestMapping( value = "/emailTemplates", method = RequestMethod.GET)
    public String emailTemplates(Model model){
        List<EmailTemplate> emailTemplateList = emailTemplateService.getEmailTemplates();
        model.addAttribute("emailTempList", emailTemplateList);
        return "emailtemp_editor";
    }
    
    @RequestMapping( value = "/emailTemplate", method = RequestMethod.GET)
    public @ResponseBody String emailTemplates(@RequestParam( value = "id") long id){        
        return emailTemplateService.getEmailTemplate(id).getHtml();
    }
    
    @RequestMapping( value = "/emailTemplate", method = RequestMethod.POST)
    public @ResponseBody String saveEmailTemplate(@RequestParam( value = "html", required = true) String html, 
                                    @RequestParam( value = "emailTemplateId", required = true)String id){
        if (emailTemplateService.isIdValid(html, id)){
            emailTemplateService.saveEmailTemplate(html, Long.parseLong(id));    
            return "ok";
        }else{
            return "error";
        }
        
    }
}
