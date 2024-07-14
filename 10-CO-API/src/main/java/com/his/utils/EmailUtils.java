package com.his.utils;

import java.io.File;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {

	private JavaMailSender sender;

	public EmailUtils(JavaMailSender sender) {
		super();
		this.sender = sender;
	}

	public boolean sendEmail(String sub,String body, String to,  File file) {
		boolean isSent = false;
		try {
			MimeMessage mimeMessage = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setSubject(sub);
			helper.setText(body);
			helper.setTo(to);
			helper.addAttachment("Eig-Notice", file);
			sender.send(mimeMessage);
			isSent = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSent;
	}
}
