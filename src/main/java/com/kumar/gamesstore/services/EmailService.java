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
	
	public void sendVerificationOtpEmail(String userEmail, String otp, String subject, String text, boolean isHtml) throws MessagingException {
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
	
			// Set the subject of the email
			helper.setSubject(subject);
			// Use the 'isHtml' flag to indicate whether the email body is HTML
			helper.setText(text, isHtml);  // Set 'isHtml' flag to true if content is HTML
			helper.setTo(userEmail);
	
			// Send the email
			javaMailSender.send(mimeMessage);
	
		} catch (MailException e) {
			// Log the error for debugging
			System.out.println("Error sending email: " + e.getMessage());
			// Throw a custom exception if necessary
			throw new MailSendException("Failed to send email");
		}
	}
}
	
