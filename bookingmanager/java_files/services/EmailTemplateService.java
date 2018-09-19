package progmatic.bookingmanager.services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progmatic.bookingmanager.databaseEntity.EmailTemplate;
import progmatic.bookingmanager.repositories.EmailTemplateRepository;

/**
 *
 * @author Attila
 */
@Service
public class EmailTemplateService {
    
    @Autowired
    EmailTemplateRepository emailTemplateRepository;
    
    @PersistenceContext
    EntityManager em;
    
    public List<EmailTemplate> getEmailTemplates() {
        List<EmailTemplate>templateList = em.createQuery(
                "SELECT t FROM EmailTemplate t")
                .getResultList();
        return templateList;
    }
    
    public EmailTemplate getEmailTemplate(long id){
        return emailTemplateRepository.findById(id);
    }
    
    public void saveEmailTemplate(String html, long id){
        EmailTemplate emailTemplate =  emailTemplateRepository.findById(id);
        emailTemplate.setHtml(html);
        emailTemplateRepository.save(emailTemplate);
    }
    
    public boolean isIdValid(String html, String id){
        if (id != null && id.matches("\\d+")){
            if (emailTemplateRepository.findById(Long.parseLong(id)) != null){
                return true;
            }
        }
        return false;
    }
}
