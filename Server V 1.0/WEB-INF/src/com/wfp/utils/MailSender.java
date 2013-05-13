/**
 * 
 */
package com.wfp.utils;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeBodyPart;

/**
 * @author kaleem.mohammed
 * 
 */
public class MailSender implements IEPICConstants {
	public static void main(String args[]) {
		List<String> toAddressList = new ArrayList<String>();
		toAddressList.add("dmr-1349@globalepic.lu");
		// toAddressList.add("kaleem6110@gmail.com"); //
		sendEmailToRadio(toAddressList, "",
				":9080 testing from Java Mailprogram-sent multipart mimetype sent");
	}

	public static void sendEmailToRadio(List<String> toAddressList,
			String subject, String messageBody) {
		System.out
				.println("@@@@@@@@@  START MailSender.sendEmailToRadio @@@@@@@@@@@@@@@@@@@@ ");

		Properties props = System.getProperties();
		props.put("mail.smtp.host", MAIL_HOST);
		props.put("mail.smtp.user", MAIL_FROM);
		props.put("mail.smtp.password", MAIL_PWD);
		props.put("mail.smtp.port", "25");
		props.put("mail.smtp.auth", "true");

		try {
			String[] toEmailAddress = new String[toAddressList.size()];
			toEmailAddress = toAddressList.toArray(toEmailAddress);
			Session session = Session.getDefaultInstance(props, null);
			MimeMultipart content = new MimeMultipart("alternative");
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(EMAIL_FROM_));

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(messageBody);
			content.addBodyPart(messageBodyPart);

			InternetAddress[] toAddress = new InternetAddress[toEmailAddress.length];

			// To get the array of addresses
			for (int i = 0; i < toEmailAddress.length; i++) { // changed from
				// a while loop
				toAddress[i] = new InternetAddress(toEmailAddress[i]);
			}
			System.out.println(Message.RecipientType.TO);

			for (int i = 0; i < toAddress.length; i++) { // changed from a
				// while loop
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}
			message.setSubject(subject);
			message.setContent(content);
			Transport transport = session.getTransport("smtp");
			transport.connect(MAIL_HOST, MAIL_FROM, MAIL_PWD);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			System.out.println(" Failed to send email to radio : "
					+ messageBody);
			e.printStackTrace();
		}

		System.out
				.println("@@@@@@@@@  END MailSender.sendEmailToRadio @@@@@@@@@@@@@@@@@@@@ ");
	}

}
