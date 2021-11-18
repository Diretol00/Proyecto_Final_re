package metodos;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public interface EnviarCorreo {
	public default void enviar() {
		String para = "tefira9783@elastit.com";
		String de = "mobile.paradise.prueba@gmail.com";
		String host = "smtp.gmail.com";
		
		Properties props = System.getProperties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", host);
		props.put("from", de);
		props.put("username", de);
		props.put("password", "MobileParadise123");
		
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(props.getProperty("username"), props.getProperty("password"));
			}
		});
		
		try {
			MimeMessage msg = new MimeMessage(session);
			
			msg.setFrom(new InternetAddress(de));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
			msg.setSubject("Compra");
			msg.setText("123 Prueba");
			
			Transport.send(msg);
			System.out.println("Sent!");
			
		}catch(MessagingException f) {
			f.printStackTrace();
		}
	}
}
