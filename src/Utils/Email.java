/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;



//import javax.servlet.http.HttpServletRequest;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ADMIN
 */
public class Email {
    	
	/**public static void sendEmail(String address, String subject, String message) throws Exception{
                        
            String from="triplay24@gmail.com";
            String pass="ahmed1997";
            String[] to = {address};
            String host="smtp.gmail.com";
            
            Properties prop = System.getProperties();
            prop.put("mail.smtp.starttls.enable","true");
            prop.put("mail.smtp.host",host);
            prop.put("mail.smtp.user",from);
            prop.put("mail.smtp.password",pass);
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth","true");
            
            Session session = Session.getDefaultInstance(prop);
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            
            InternetAddress[] toaddress = new InternetAddress[to.length];
            for(int i=0;i<to.length;i++){
                toaddress[i] = new InternetAddress(to[i]);
            }
            for(int i=0;i<toaddress.length;i++){
                msg.setRecipient(Message.RecipientType.TO, toaddress[i]);
            }
            msg.setSubject(subject);
            msg.setContent(message,"test/html ; charset=utf-8");
            msg.setText(message,"UTF-8");
            Transport transport=session.getTransport("smtp");
            transport.connect(host,from,pass);
            transport.sendMessage(msg,msg.getAllRecipients());
            transport.close()
     * @param toEmailAddress;*
     * @return */
                
           //  private static final Logger logger=LoggerFactory.getLogger(Forgetpassword.class);
            public String sendEmail(String toEmailAddress, String emailMessage) {
            String userName = "cyrine.khezami@esprit.tn";
            String password = "203JFT1493";
            String emailSubject = "Default Password";
            //String emailMessage = getPassword();
            try {
            // Use javamail api, set parameters from registration.properties file
            // set the session properties
            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");
            Session session = Session.getDefaultInstance(props, null);
            // Create email message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userName));
            String[] recipientList = toEmailAddress.split(",");
            InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
            int counter = 0;
            for (String recipient: recipientList) {
                recipientAddress[counter] = new InternetAddress(recipient.trim());
                counter++;
            }
            message.setRecipients(Message.RecipientType.TO, recipientAddress);
            message.setSubject(emailSubject);
            message.setContent(emailMessage, "text/html");
            // Send smtp message
            Transport tr = session.getTransport("smtp");
            tr.connect("smtp.gmail.com", 587, userName, password);
            message.saveChanges();
            tr.sendMessage(message, message.getAllRecipients());
            tr.close();
            return emailMessage;
            } catch (MessagingException e) {
                return "Error in method sendEmailNotification: " + e;
            }
            
            
            

        }
            public String sendEmail1(String toEmailAddress, String emailMessage) {
            String userName = "cyrine.khezami@esprit.tn";
            String password = "203JFT1493";
            String emailSubject = "Verify Your E-Mail";
            //String emailMessage = getPassword();
            try {
            // Use javamail api, set parameters from registration.properties file
            // set the session properties
            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");
            Session session = Session.getDefaultInstance(props, null);
            // Create email message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userName));
            String[] recipientList = toEmailAddress.split(",");
            InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
            int counter = 0;
            for (String recipient: recipientList) {
                recipientAddress[counter] = new InternetAddress(recipient.trim());
                counter++;
            }
            message.setRecipients(Message.RecipientType.TO, recipientAddress);
            message.setSubject(emailSubject);
            message.setContent(emailMessage, "text/html");
            // Send smtp message
            Transport tr = session.getTransport("smtp");
            tr.connect("smtp.gmail.com", 587, userName, password);
            message.saveChanges();
            tr.sendMessage(message, message.getAllRecipients());
            tr.close();
            return emailMessage;
            } catch (MessagingException e) {
                return "Error in method sendEmailNotification: " + e;
            }
            
            
            

        }

public String getPassword()
    {
        String temp = Long.toHexString(Double.doubleToLongBits(Math.random()));
        return temp;
    }

}

