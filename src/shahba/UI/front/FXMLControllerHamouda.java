/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.UI.front;

import java.io.IOException;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import javax.mail.Session;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author isslem
 */
public class FXMLControllerHamouda implements Initializable {

    @FXML
    private Rating rate;
    @FXML
    private Label msg;
    @FXML
    private Button rating_but;
    @FXML
    private AnchorPane displayArea;
    @FXML
    private Button btnBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         rate.ratingProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            msg.setText("Your Rating About Our Event is : "+newValue);
        });
        
        // TODO
    }    

    @FXML
    private void Rate(ActionEvent event) {
          final String fromEmail = "jouini.mohamednourelhak@esprit.tn"; //requires valid gmail id
		final String password = "203JMT0242"; // correct password for gmail id
		final String toEmail = "jouini.mohamednourelhak@esprit.tn"; // can be any email id 
		
		System.out.println("TLSEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.port", "587"); //TLS Port
		props.put("mail.smtp.auth", "true"); //enable authentication
		props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
		
                //create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, auth);
		
		Service.sendEmail(session, toEmail,"Event Rated", msg.getText());
          
                Notifications notificationBuilder = Notifications.create()
                .title("You have added a new Rating")
                .text("Hope you Liked our service")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.showConfirm();
    
       
    }
      @FXML
    private void Back(ActionEvent event) throws IOException {
               Parent fxml = FXMLLoader.load(getClass().getResource("evenementfront.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }

    }
    

