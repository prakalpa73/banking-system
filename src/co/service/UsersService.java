package co.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import co.db.Users;
import co.model.UsersDao;

public class UsersService {

	public static void sendPassword(Users u) 
	{
		String db_username=UsersDao.getUsername(u);
		String db_password= UsersDao.getPassword(u);
		String to=u.getEmail();
		String subject="Login Info. Odisha Bank";
		String message="Your login information for Odisha Bank is mentioned here. "+
						"User Name: "+db_username+" And Password: "+db_password +"";
		
		String from="pkp.prince96@gmail.com";
		String password="barikprakalpa";
		
		try {
			//Authentication with Gmail server
			Properties props=new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			
			
			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, password);
				}
			};
			
			Session session = Session.getInstance(props, auth);
			
			//Composing the message
			MimeMessage msg=new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			msg.setSubject(subject);
			msg.setText(message);
			
			//Sending message
			Transport.send(msg);
			System.out.println("Password already send to your email...plz check...");
			//System.out.println("Mail delivered successfully...........");
			//response.sendRedirect("mail.jsp?msg=mail delivered");
		} catch (MessagingException e3) {
			// TODO: handle exception
			//throw new RuntimeException(e);
			e3.printStackTrace();
		}
	
	}

}
