package com.kumar.gamesstore.services;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

	private final JavaMailSender javaMailSender;
	
	public void sendVerificationOtpEmail(String userEmail,String otp,String subject,String text) throws MessagingException {
		
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"utf-8");
			
			helper.setSubject(subject);
			helper.setText(text);
			helper.setTo(userEmail);
			
			javaMailSender.send(mimeMessage);
			
			
		}
		
		catch(MailException e) {
			System.out.println("errrrrrrror"+e);
			throw new MailSendException("failed to send mail");
		}
		
		
	}
}
