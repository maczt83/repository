package progmatic.bookingmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import progmatic.bookingmanager.databaseEntity.EmailTemplate;

/**
 *
 * @author Attila
 */
public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Long>{
    EmailTemplate findBySubject(String subject);
    EmailTemplate findById(long id);
}

