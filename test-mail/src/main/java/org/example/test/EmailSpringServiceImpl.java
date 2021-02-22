package org.example.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author 
 *
 */
@Service
@Slf4j
public class EmailSpringServiceImpl implements EmailService {

	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendMail() {
		        
        try {
        	log.info("Sending mail with Spring...");
            SimpleMailMessage message = new SimpleMailMessage();
            
            message.setFrom("noreply@baeldung.com");
            message.setTo("to_1@gmail.com", "to_2@gmail.com", "to_3@yahoo.com"); 
            message.setSubject("Testing from Spring Boot"); 
            message.setText("Hello World \n Spring Boot Email");
            
            mailSender.send(message);
            log.info("...Sending end.");

	    } catch (Exception e) {
	    	log.error("Fail sending mail with Spring: " + e.getMessage(), e);
	    }

	}
}
