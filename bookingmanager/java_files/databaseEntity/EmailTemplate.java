package progmatic.bookingmanager.databaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 *
 * @author Attila
 */
@Entity
public class EmailTemplate {
    
    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO )
    private long id;
    
    @Column( length = 100 )
    private String subject;
    
    @Lob 
    private String html;

    public EmailTemplate() {
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public long getId() {
        return id;
    }
    
    
}
