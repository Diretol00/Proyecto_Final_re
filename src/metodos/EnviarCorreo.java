package metodos;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import interfaz.ConfirmarCompra;


public interface EnviarCorreo {



	public default void enviar(String to, String mes) {
		
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
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			msg.setSubject("Compra");
			msg.setText(mes);
			
			
			Transport.send(msg);
			JOptionPane.showMessageDialog(null, "Ha recibido un correo con los detalles de su compra, Gracias!");
			System.out.println("Sent!");
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String url = "jdbc:mysql://localhost/proyectofinal";
				String usr = "root";
				String password = "";
				java.sql.Connection con = DriverManager.getConnection(url, usr, password);
				Statement stm = con.createStatement();
				
				stm.executeUpdate("Delete from carrito");
				
				con.close();
				
			}catch(ClassNotFoundException ex) {
			}catch (SQLException f) {
				f.printStackTrace();
			}
			
		}catch(MessagingException f) {
			f.printStackTrace();
		}
		
	}
}
