package com.dalhousie.moviecritic.service;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl implements IEmailSenderService {
	private static Logger logger = LogManager.getLogger(EmailSenderServiceImpl.class);
	@Value("${forgot.password.email.subject}")
	String subject;
	@Value("${forgot.password.email.emailBody}")
	String emailBody;
	@Value("${forgot.password.email.emailLabel}")
	String emailLabel;
	@Value("${forgot.password.email.fromEmail}")
	String fromEmail;
	@Value("${forgot.password.email.password}")
	String password;
	
	
	@Override
	public void sendPasswordChangeEmail(String emailId, String newPassword) throws SendFailedException {
		startSendingEmail(emailId,newPassword );
	}

	public String startSendingEmail(String toEmail, String newPassword) throws SendFailedException {
		
		logger.info("start sending email");
		emailBody = emailBody.replace("%newPassword%",newPassword);
				
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, auth);

		sendEmail(session, toEmail, subject, emailBody);
		return "success";
	}
	
	public void sendEmail(Session session, String toEmail, String subject, String body) throws SendFailedException {
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress(fromEmail, emailLabel));
			msg.setReplyTo(InternetAddress.parse(fromEmail, false));
			msg.setSubject(subject);
			msg.setContent(body, "text/html");
			msg.setSentDate(new Date());
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
			logger.info("Email Sending to " + toEmail);
			Transport.send(msg);

			logger.info("Email Sent Successfully!!");
		} catch (SendFailedException m) {
			throw m;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
