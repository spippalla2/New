
import java.util.Properties;
import java.util.StringTokenizer;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMail 
    
   {
    
    public static void send(String to, String sub, 
                         String msg, final String user,final String pass)
    {
     
     //create an instance of Properties Class     
     Properties props = new Properties();
     System.out.println("====================="+user);
     System.out.println("====================="+pass);
     
     /* Specifies the IP address of your default mail server
     for e.g if you are using gmail server as an email sever
     you will pass smtp.gmail.com as value of mail.smtp host. As shown here in the 
    coding. Change accordingly, if your email id is not an gmail id*/
   //  props.put("mail.smtp.host", "smtp.gmail.com");
     props.put("mail.smtp.host", "relay.abcxyz.com");
     
     
     props.put("mail.smtp.port", "465");	//this is optional	
     props.put("mail.smtp.auth", "true");
     props.put("mail.smtp.starttls.enable", "true");
     

     
     /*Pass Properties object(props) and Authenticator object   
           for authentication to Session instance */

     /*Session session = Session.getInstance(props,
   		  new javax.mail.Authenticator() {
   			protected PasswordAuthentication getPasswordAuthentication() {
   				return new PasswordAuthentication(user, pass);
   			}
   		  });
*/
     long startTime = System.currentTimeMillis();
     long startTime1 = System.nanoTime();
     Session session = Session.getInstance(props, new GmailAuthenticator(user, pass));
     
     long endTime = System.currentTimeMillis();
     long endTime1 = System.nanoTime();
     System.out.println("That took " + (endTime - startTime) + " milliseconds");
     System.out.println("That took " + (endTime1 - startTime1) + " nano time");
     
 try
 {
 
 /* Create an instance of MimeMessage, 
 it accept MIME types and headers */
 
 /*MimeMessage message = new MimeMessage(session);
 message.setFrom(new InternetAddress(user));
 message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
 message.setSubject(sub);
 message.setText(msg);
 System.out.println("====================="+message);
 */
 Message message = new MimeMessage(session);
 InternetAddress[] address = {new InternetAddress("meetsrinu@gmail.com")};
	message.setFrom(new InternetAddress("meetsrinu@gmail.com"));
	message.setRecipients(Message.RecipientType.TO,
		InternetAddress.parse("spippalla2@csc.com"));
	message.setSubject("Testing Subject");
	message.setText("Dear Mail Crawler,"
		+ "\n\n No spam to my email, please!");
	//System.out.println("value of k " + message.get);
    
String stri1="abc";
StringTokenizer tokenizer = new StringTokenizer(stri1,",");
while (tokenizer.hasMoreTokens()) {
    System.out.println(tokenizer.nextToken());
}




 /* Transport class is used to deliver the message to the recipients */
	
	//long startTime = System.currentTimeMillis();

	Transport bus = session.getTransport("smtp");

	bus.connect();
	bus.sendMessage(message, address);
 //Transport.send(message);
 
// long endTime = System.currentTimeMillis();
 //System.out.println("That took " + (endTime - startTime) + " milliseconds");
 
 
 }catch(Exception e)
 {
     e.printStackTrace();
 }
    }
    
}