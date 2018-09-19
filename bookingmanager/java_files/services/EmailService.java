/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progmatic.bookingmanager.services;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import progmatic.bookingmanager.configuration.JavaMailConfig;
import progmatic.bookingmanager.repositories.UserRepository;

/**
 *
 * @author chris
 */
@Service
public class EmailService {
    
    @Autowired
    UserRepository uR;

    @Autowired
    JavaMailConfig mailConfig;

    @Autowired
    public JavaMailSender mailSender;

    public void setMailSenderImpl(JavaMailSenderImpl mailConfig) {
        this.mailSender = mailConfig;
    }

    public void sendMail(Mail mail) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setFrom(mail.getFrom());
                mimeMessage.setRecipients(Message.RecipientType.TO, mail.getTo());
                mimeMessage.setSubject(mail.getSubject());
                mimeMessage.setContent(mail.getText(), "text/html");
                //mimeMessage.setText(mail.getText());
            }
        };
        try {
            mailSender.send(preparator);
        } catch (MailException ex) {
            
            System.err.println(ex.getMessage());
        }
                
    }
    
    public void sendEmail(Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(mail.getTo()); 
        message.setSubject(mail.getSubject()); 
        message.setText(mail.getText());
        mailSender.send(message);
    }
}
