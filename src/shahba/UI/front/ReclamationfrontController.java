/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.UI.front;



import static Controller.ClientController1.cli;
import static Controller.SigninController.login;
import shahba.Service.ReclamationServices;
import shahba.entity.Reclamation;
import shahba.Service.TypereclamationServices;
import com.sun.javafx.font.directwrite.RECT;

import shahba.entity.Typereclamation;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ahmed
 */

public class ReclamationfrontController implements Initializable {

    @FXML
    private TextField tfnomc;
    @FXML
    private TextField tfpnomc;
    @FXML
    private TextField tfnumclient;
    @FXML
    private TextField tfmailc;
    @FXML
    private TextField tfobrc;
    @FXML
    private TextField tfdesrec;
    @FXML
    private ChoiceBox<Typereclamation> cbtypereclamation_id;
    @FXML
    private Label LAffiche;
    @FXML
    private ImageView imview;
    @FXML
    private Button upload;
    @FXML
    private TextField txtimage;
        private FileChooser fileChooser;

     private ObservableList<Reclamation> listrec ;
    private  Reclamation rec = new Reclamation(0, 0, 0, 0, null, null, null, 0, null, null, null, null, null, null);
    @FXML
    private AnchorPane displayArea;

    /**
     * 
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                ObservableList list = FXCollections.observableArrayList();
        TypereclamationServices trs = new TypereclamationServices();
        list.addAll(trs.read());
        cbtypereclamation_id.setItems(list);
        
    }   
     

    
   public void setreclamation(Reclamation r){
   this.rec = r;
    this.tfnomc.setText(rec.getNomc());
    this.tfnumclient.setText(String.valueOf(rec.getNumclient()));
            this.tfmailc.setText(rec.getMailc());
            this.tfobrc.setText(rec.getObrc());
            this.tfpnomc.setText(rec.getPnomc());
            this.tfdesrec.setText(rec.getDesrec());
           this.txtimage.setText(rec.getScreenshot());
    }


    private boolean validateFields() {

if (tfnomc.getText().isEmpty() | tfpnomc.getText().isEmpty() | tfpnomc.getText().isEmpty() | tfnumclient.getText().isEmpty() | tfmailc.getText().isEmpty() | tfobrc.getText().isEmpty() | tfdesrec.getText().isEmpty()) 
{
 Alert alert = new Alert(Alert.AlertType.WARNING);
alert.setTitle("Validate Fields");
alert.setHeaderText(null);
alert.setContentText("Entrée les données s'il vous plaît  ");
alert.showAndWait();
return false;
}
return true;
}
private boolean validateNumber(){

Pattern p = Pattern.compile("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]");
Matcher m = p.matcher(tfnumclient.getText());
if (m.find()&& m.group().equals(tfnumclient.getText())){
   return true; 
}else {
     Alert alert = new Alert(Alert.AlertType.WARNING);
alert.setTitle("Validate Number");
alert.setHeaderText("Exemple 12345678");
alert.setContentText("Entrée un numéro composer de 8 chiffres  s'il vous plaît!  ");
alert.showAndWait();
return false;
}
}
private boolean validateEmail(){

Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+[a-zA-Z0-9]+([.][a-zA-Z+]+)+");
Matcher m = p.matcher(tfmailc.getText());
if (m.find()&& m.group().equals(tfmailc.getText())){
   return true; 
}else {
     Alert alert = new Alert(Alert.AlertType.WARNING);
alert.setTitle("Validate Email");
alert.setHeaderText("Exemple shahba.shahba@shahba.com");
alert.setContentText("Entrer un email valide  s'il vous plaît!  ");
alert.showAndWait();
return false;
}
}
     @FXML
    private void AjouterPhoto(ActionEvent event) throws FileNotFoundException, IOException {
        

               FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pick a banner file!");
        fileChooser.setInitialDirectory(new File("\\"));
        Stage stage = new Stage();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("mp4", "*.MP4")
            );
        File file = fileChooser.showOpenDialog(stage);
        try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                txtimage.setText(file.getAbsolutePath());
                imview.setImage(image);
            } catch (IOException ex) {
                System.out.println("could not get the image");
            }

    }
    
 public void load_pic(String links) {

        Image img = new Image(links);
        imview.setImage(img);
     
    }

    
    
    
    @FXML
    private void modifierReclamation(ActionEvent event) throws IOException {
         if(validateFields()& validateNumber()& validateEmail() ){
        ReclamationServices sr =new ReclamationServices();
        int id = cbtypereclamation_id.getSelectionModel().getSelectedItem().getId();
        sr.update(new Reclamation(this.rec.getId(), id, 2, 1, tfnomc.getText(), tfpnomc.getText(), tfmailc.getText(), Integer.parseInt(tfnumclient.getText()),
                "en attente", tfobrc.getText(), tfdesrec.getText(), txtimage.getText(), null, null));
                JOptionPane.showMessageDialog(null, "reclametion ajoutée !");
                
                
fileChooser = new FileChooser();
fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("All files","*.*" ),
        new FileChooser.ExtensionFilter("Images","*.png","*.jpg","*.gif" ),
        new FileChooser.ExtensionFilter("Text File","*.txt" ));
             
    //Declare recipient's & sender's e-mail id.
      String destmailid = "ahmed.abdelkafi@esprit.tn";
      String sendrmailid = "cyrine.khezami@esprit.tn";	  
     //Mention user name and password as per your configuration
      final String uname = "cyrine.khezami@esprit.tn";
      final String pwd = "203JFT1493";
      //We are using relay.jangosmtp.net for sending emails
      String smtphost = "smtp.gmail.com";
     //Set properties and their values
      Properties propvls = new Properties();
      propvls.put("mail.smtp.auth", "true");
      propvls.put("mail.smtp.starttls.enable", "true");
      propvls.put("mail.smtp.host", smtphost);
      propvls.put("mail.smtp.port", "25");
      //Create a Session object & authenticate uid and pwd
      Session sessionobj = Session.getInstance(propvls,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(uname, pwd);
	   }
         });

      try {
	   //Create MimeMessage object & set 
	   Message messageobj = new MimeMessage(sessionobj);
	   messageobj.setFrom(new InternetAddress(sendrmailid));
	   messageobj.setRecipients(Message.RecipientType.TO,InternetAddress.parse(destmailid));
	   messageobj.setSubject("reclamation No-replay");
	   messageobj.setText("Votre réclamation est bien passé , merci d'attendre notre reponse au plus proche");
	  //Now send the message
	   Transport.send(messageobj);
	   System.out.println("Your email sent successfully....");
      } catch (MessagingException exp) {
         throw new RuntimeException(exp);
      }
        
; 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("test.fxml"));
        
        Parent root = loader.load();
        tfnomc.getScene().setRoot(root);
    }
    }

    

    private void consulterreclamation(ActionEvent event) throws IOException {
        {
       
         Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        //stage.setMaximized(true);
        stage.close();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("TestAhmed.fxml")));
        stage.setScene(scene);
        stage.show();
    }
    }
    @FXML
    void Reservation(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("reservation.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }
    @FXML
    void Event(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("Test.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }

    @FXML
    private void Home(ActionEvent event) throws IOException {
                Parent fxml = FXMLLoader.load(getClass().getResource("Home.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }

  @FXML
    private void profile(ActionEvent event) {
        cli = login;
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/View/profclient.fxml"));
                Scene scene = new Scene(page1);
                //Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                //stage.setTitle("Inscription");
                Stage stage=new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                 System.out.println("Erreur chh !!!");
            }
    }
    @FXML
    private void Deconecterclient(ActionEvent event) {
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = new Stage() ;
                stage.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(Color.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
                closeAcceuil2();
            } catch (IOException ex) {
                 System.out.println("Erreur 1 !!!");
            }
    }
     public void closeAcceuil2() {
        Stage Acceuil2Stage = (Stage) nom.getScene().getWindow();
        Acceuil2Stage.close();
    }
      private Label nom;
     
       @FXML
       private void Astuce(ActionEvent event) throws IOException {
              Parent fxml = FXMLLoader.load(getClass().getResource("Astuce.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }
        @FXML
    private void Video(ActionEvent event)throws IOException {
          Parent fxml = FXMLLoader.load(getClass().getResource("Video.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }

     @FXML
    private void commande(ActionEvent event) throws IOException {
         Parent fxml = FXMLLoader.load(getClass().getResource("Commande.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
        
    }


    @FXML
    private void Reclamation(ActionEvent event) throws IOException {
              Parent fxml = FXMLLoader.load(getClass().getResource("TestAhmed.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }
   @FXML
    private void Produit(ActionEvent event) throws IOException {
                  Parent fxml = FXMLLoader.load(getClass().getResource("test1.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }
    
}
