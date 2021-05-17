/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package shahba.UI.front;

import java.io.File;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;
import shahba.entity.Astuce;


/**
 * FXML Controller class
 *
 * @author skander
 */
public class itemController implements Initializable {

    @FXML
    private Label nomlab;
    private Label prixlab;
    @FXML
    private ImageView img;
   
    private MyListener myListener;
    @FXML
    private AnchorPane nh;
    private Astuce astuce;
    @FXML
    private Label labDescription;
    @FXML
    private Button avis;
    @FXML
    private Rating rate;
    @FXML
    private Button rating_but;
    @FXML
    private Label msg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         rate.ratingProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            msg.setText("Avis : "+newValue);
        });
        
        // TODO
    }
    public void setData(Astuce astuce) {
       
        this.astuce = astuce;
        this.nomlab.setText(astuce.getTitre());
        this.labDescription.setText(astuce.getDescription());
        File file = new File(astuce.getImage().replace('/' , '\\'));
        System.out.println(file);
        
        Image im = null;
        if(file.exists()){ 
                 im = new Image(file.toURI().toString());
        }else{
            //im = new Image("resources/default-article.jpg"); // this is the defualt photo of the product
        }
         this.img.setImage(im);
        //this.myListener = myListener;       
        //nomlab.setText(astuce.getTitre());
        //Image image = new Image(getClass().getResourceAsStream(Astuce.getImage()));
        //img.setImage(image);
  //  getClass().getResourceAsStream
}

   // @FXML
  //  private void clck(MouseEvent event) {
       // myListener.onClickListener();
  //  }

    private void show(ActionEvent event) throws IOException {
             Parent fxml = FXMLLoader.load(getClass().getResource("show.fxml"));
       nh.getChildren().clear();
        nh.getChildren().add(fxml);
    
 
      /*  FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("show.fxml"));
        Parent p = Loader.load();
        ShowController cont = Loader.getController();
        cont.setData(Astuce);
        

        AnchorPane pane = (AnchorPane) img.getParent().getParent().getParent();
        pane.getChildren().clear();
        pane.getChildren().setAll(p);*/
}

    @FXML
    private void clck(MouseEvent event) {
        myListener.onClickListener(astuce , event);
    }

    @FXML
    private void avis(ActionEvent event) throws IOException {
         /*Parent fxml = FXMLLoader.load(getClass().getResource("FXML.fxml"));
       nh.getChildren().clear();
        nh.getChildren().add(fxml);
*/
          Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        //stage.setMaximized(true);
        //stage.close();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("FXML.fxml")));
        stage.setScene(scene);
        stage.show();
    }
      @FXML
    private void Rate(ActionEvent event) {
          final String fromEmail = "jouini.mohamednourelhak@esprit.tn"; //requires valid gmail id
		final String password = "203JMT0242"; // correct password for gmail id
		final String toEmail = "islem.maamer@esprit.tn"; // can be any email id 
		
		System.out.println("TLSEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.port", "587"); //TLS Port
		props.put("mail.smtp.auth", "true"); //enable authentication
		props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
		
                //create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
                        @Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, auth);
		
		Service.sendEmail(session, toEmail,"Astuce évaluée", msg.getText());
          
                Notifications notificationBuilder = Notifications.create()
                .title("Vous avez ajouté une nouvelle note")
                .text("J'espère que vous avez aimé notre astuce")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.showConfirm();
    
       
    }
}


   

