/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.UI.front;
import entity.User;
import Controller.SigninController;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import shahba.Service.ServiceCommande;
import shahba.Service.ServiceLivraison;
import entity.User;
import shahba.entity.commande;
import shahba.entity.livraison;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 * FXML Controller class
 *
 * @author Anis
 */
public class LivraisonController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label nom;

    @FXML
    private Label prenom;

    @FXML
    private Label adresse;

    @FXML
    private Label numero_telehphone;

    @FXML
    private Button paiement;
    
    @FXML
    private AnchorPane displayArea;
    private ServiceLivraison ls = new ServiceLivraison();
    private ServiceCommande sco = new ServiceCommande();
    private User c = SigninController.login;
    private commande co = null;
    @FXML
    private Button pdf;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
           
            co=sco.recherche_client(c.getId());
            nom.setText(co.getClient().getUsername());
            prenom.setText(co.getClient().getUsername());
            adresse.setText(co.getAdresse());
            numero_telehphone.setText(String.valueOf(co.getTel()));

        } catch (SQLException ex) {
            Logger.getLogger(LivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    @FXML
    void Paiement(ActionEvent event) throws IOException, SQLException, MessagingException {
        livraison l;
        l = new livraison(co, "En Cours");
        ls.ajouter(l);
        
        
        /* Mail */
        
         System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");


        //Your gmail address
        String myAccountEmail = "anis.hajali@esprit.tn";
        //Your gmail password
        String password = "171JMT1403";

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, co.getClient().getEmail(),co.getClient());

        //Send mail
        Transport.send(message);
        System.out.println("Message sent successfully");
        
        sendSMS(co);
        
        
        /***************************/
          
        
        
        Parent fxml = FXMLLoader.load(getClass().getResource("Home.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }
    
 
    private static Message prepareMessage(Session session, String myAccountEmail, String recepient,User c) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Confirmation de la commande");
            String htmlCode = "<p>Bonjour Mr/Mme "+c.getUsername()+"</p> <br/> <p> Votre commande a été bien passée.</p> <br/> <p> Merci pour votre commande.</p>";
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(LivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @FXML
    private void pdflivraison(ActionEvent event) throws IOException {
        
        String filename="livraison.pdf";
        try (PDDocument doc = new PDDocument())
	        {
	            PDPage page = new PDPage();
	            doc.addPage(page);
	            
	            PDFont font = PDType1Font.HELVETICA_BOLD;
	
	            try (PDPageContentStream contents = new PDPageContentStream(doc, page))
	            {
                        contents.setFont(font, 12);
                        contents.beginText();
                        contents.newLineAtOffset(150, 600);
                         contents.showText("Produits:");
	                contents.endText();
                        
                        contents.beginText();
                        contents.newLineAtOffset(150, 620);
                        contents.showText("Numero de telephone :"+co.getClient().getUserphone());
                        contents.endText();
                        
                         
                        contents.beginText();
                        contents.newLineAtOffset(150, 640);
                        contents.showText("Email :"+co.getClient().getEmail());
                        contents.endText();
                        
                         contents.beginText();
                         contents.newLineAtOffset(150, 660);
                        contents.showText("Prenom :"+co.getClient().getUsername());
                        contents.endText();
                        
                        contents.beginText();
                        contents.newLineAtOffset(150, 680);
                        contents.showText("Nom :"+co.getClient().getUsername());
                        contents.endText();
                        
	                contents.beginText();
	                contents.newLineAtOffset(100, 700);
                        
	                contents.showText("Les détails de la commande");
                        
                        contents.endText();

	            }
            
	            
	            doc.save(filename);
	        }
    }
    
    private void sendSMS(commande c)
    {
        try {
            // Construct data
            String data = "";
            /*
             * Note the suggested encoding for certain parameters, notably
             * the username, password and especially the message.  ISO-8859-1
             * is essentially the character set that we use for message bodies,
             * with a few exceptions for e.g. Greek characters.  For a full list,
             * see:  https://www.bulksms.com/developer/eapi/submission/character-encoding/
             */
            data += "username=" + URLEncoder.encode("hajalianis", "ISO-8859-1");
            data += "&password=" + URLEncoder.encode("*3gH@$2vM8.r*mU", "ISO-8859-1");
            data += "&message=" + URLEncoder.encode("Votre commande a été bien passé \n ", "ISO-8859-1");
            data += "&want_report=1";
            data += "&msisdn=+216"+c.getTel();

            // Send data
            // Please see the FAQ regarding HTTPS (port 443) and HTTP (port 80/5567)
            URL url = new URL("https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            BufferedReader rd;
            try (OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream())) {
                wr.write(data);
                wr.flush();
                // Get the response
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    // Print the response output...
                    System.out.println(line);
                }
            }
            rd.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
