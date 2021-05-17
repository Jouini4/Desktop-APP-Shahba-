/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.UI.back;

import com.jfoenix.controls.JFXButton;
import shahba.Service.ReclamationServices;
import shahba.Service.TypereclamationServices;
import shahba.entity.Reclamation;
import shahba.entity.Typereclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static java.util.Locale.filter;
import static java.util.Locale.filter;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.sort;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.IntegerStringConverter;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class BackUiController implements Initializable {

  // pour le tableau reclamation
     @FXML
    private TableView<Reclamation> Reclamations;
    @FXML
    private TableColumn<Reclamation, Integer> tnumreclam;
    @FXML
    private TableColumn<Reclamation, String> tnomclient;
    @FXML
    private TableColumn<Reclamation, String> tprenom;
    @FXML
    private TableColumn<Reclamation, String> tmail;
    @FXML
    private TableColumn<Reclamation, Integer> tnumclient;
    @FXML
    private TableColumn<Reclamation, String> tadresse;
   
    @FXML
    private TableColumn<Reclamation, String> tetatrec;
    @FXML
    private TableColumn<Reclamation, String> tobjet;
    @FXML
    private TableColumn<Reclamation, String> tdesc;
    @FXML
    private TableColumn<Reclamation, String> timagereclam;
    @FXML
    private TableColumn<Reclamation, Date> tdatereclam;
    @FXML
    private TableColumn<Reclamation, Date> tdernieremodif;
    
    
    private ObservableList<Typereclamation> listtype ;
     private ObservableList<Reclamation> listrec ;
    @FXML
    private Button recherche;
    @FXML
    private TableColumn<Reclamation, String> rectyperec;
    @FXML
    private ComboBox<String> liststatut;
    @FXML
    private TextField tfstatus;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button Astuce;
    @FXML
    private Button Video;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOverview1;
    @FXML
    private Button btnOverview11;
    @FXML
    private Button btnOverview111;
    @FXML
    private Button btnSignout;
    @FXML
    private Button updatestatatus;
    @FXML
    private AnchorPane displayArea;

    
    @FXML
    public void changeEtatreclamationEvent(TableColumn.CellEditEvent editedCell){
    Reclamation reclamationSelected = Reclamations.getSelectionModel().getSelectedItem();
    reclamationSelected.setEtrc(editedCell.getNewValue().toString());
    rs.updateetat(reclamationSelected);
              
    //Declare recipient's & sender's e-mail id.
    
      String destmailid = reclamationSelected.getMailc();
      String sendrmailid = "shahbaaservices@gmail.com";	  
     //Mention user name and password as per your configuration
      final String uname = "shahbaaservices@gmail.com";
      final String pwd = "66abhahs11";
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
	   //Create MimeMessage object & set values
	   Message messageobj = new MimeMessage(sessionobj);
	   messageobj.setFrom(new InternetAddress(sendrmailid));
	   messageobj.setRecipients(Message.RecipientType.TO,InternetAddress.parse(destmailid));
	   messageobj.setSubject("Reclamation No-replay");
	   messageobj.setText("Cher client,Merci de verifier l'avancement de votre reclamation!");
	  //Now send the message
	   Transport.send(messageobj);
	   System.out.println("Your email sent successfully....");
      } catch (MessagingException exp) {
         throw new RuntimeException(exp);
      }
        
;
              }
   
     
    
    
    // pour le tableau typereclamation
        @FXML
    private TableView<Typereclamation> Typereclamations;
     @FXML
    private TableColumn<Typereclamation, String> ttypereclam;
      @FXML
    private TableColumn<Typereclamation, String> tcommantaireadmin;
    @FXML
    private TableColumn<Typereclamation, String> tcouleurs;
    
    // pour le ajouter typereclamation
    
      @FXML
    private TextField tfTypereclamations;
      
    @FXML
    private TextField tfCmnadmin;



    @FXML
    private Button AjouterTypereclamation;
    @FXML
    private ColorPicker cpColor;
    @FXML
    private TextField colorref;
    @FXML
    private Button supptype;
    @FXML
    private TextField typenom;
    @FXML
    private TextField cherchetab;
   
     // hawelha ba3d il init
    ReclamationServices rs = new ReclamationServices();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList status = FXCollections.observableArrayList("envoyé","en cours de traitement","cloturé");
        liststatut.setItems(status);
        
        Reclamations.setEditable(true);
        tetatrec.setCellFactory(TextFieldTableCell.forTableColumn());
               loadData();
               
    }
        @FXML
    private void cherechenomreclamation(ActionEvent event) {
        listrec.clear();
        listrec.addAll(rs.RechercheNom(cherchetab.getText()));
    }
    
    TypereclamationServices trs = new TypereclamationServices();
    
     public void loadData (){
        listtype = FXCollections.observableArrayList();
      listtype.addAll(trs.read());
      ttypereclam.setCellValueFactory(new PropertyValueFactory<Typereclamation,String>("tyrc"));
      tcommantaireadmin.setCellValueFactory(new PropertyValueFactory<Typereclamation,String>("comrc"));
      tcouleurs.setCellValueFactory(new PropertyValueFactory<Typereclamation,String>("color"));

      
      Typereclamations.setItems(listtype);
      listrec = FXCollections.observableArrayList();
      refreshTable();
            tnumreclam.setCellValueFactory(new PropertyValueFactory<Reclamation,Integer>("id"));
      tnomclient.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("nomc"));
      tprenom.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("pnomc"));
     tmail.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("mailc"));
     tnumclient.setCellValueFactory(new PropertyValueFactory<Reclamation,Integer>("numclient"));
     tadresse.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("commande_id"));
     tetatrec.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("etrc")); 
     tobjet.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("obrc"));
     tdesc.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("desrec"));
      timagereclam.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("screenshot"));
     tdatereclam.setCellValueFactory(new PropertyValueFactory<Reclamation,Date>("created_at"));
     tdernieremodif.setCellValueFactory(new PropertyValueFactory<Reclamation,Date>("updated_at"));
          rectyperec.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("Typereclamation"));

     Reclamations.setItems(listrec);

    }

      private void refreshTable() {

        listrec.clear();
      
        for (Reclamation u : (rs.read())) {
            listrec.add(u);
        }

    }
    @FXML
    private void AjouterTypereclamation(ActionEvent event) {
        try {
        
            trs.add(new Typereclamation(1, tfTypereclamations.getText(), tfCmnadmin.getText(), 1,colorref.getText() ));
            
            JOptionPane.showMessageDialog(null, "typereclametion ajouté !");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("back ui.fxml"));
            
            Parent root = loader.load(); 
            Typereclamations.getScene().setRoot(root);
            BackUiController dcc = loader.getController();
        } catch (IOException ex) {
            Logger.getLogger(BackUiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }


    @FXML
    private void selectcolor(MouseEvent event) {
             String col = cpColor.getValue().toString();
        String color = "#"+ col.substring(2, col.lastIndexOf("ff"));
        
        colorref.setText(color);
        
    }

    @FXML
    private void supptype(ActionEvent event) {
         try {
             Typereclamation tr = new Typereclamation(1, null, null, 1,null);
             int id = Typereclamations.getSelectionModel().getSelectedItems().get(0).getId();
             String nom =  Typereclamations.getSelectionModel().getSelectedItems().get(0).getTyrc();
             tr.setId(id);
             trs.delete(tr);
             JOptionPane.showMessageDialog(null, "typereclametion supprimé !");
             FXMLLoader loader = new FXMLLoader(getClass().getResource("back ui.fxml"));
             
             Parent root = loader.load();
             Typereclamations.getScene().setRoot(root);
             BackUiController dcc = loader.getController();
         } catch (IOException ex) {
             Logger.getLogger(BackUiController.class.getName()).log(Level.SEVERE, null, ex);
         }
      
    }

    @FXML
    private void selecttype(MouseEvent event) {

        String nom  = Typereclamations.getSelectionModel().getSelectedItems().get(0).getTyrc();
        if (nom == null || nom.isEmpty()){
        typenom.setText("");
        }
        else  {
        typenom.setText(nom);
    }
    }

    @FXML
    private void selectcolor(ActionEvent event) {
    }

    private void cherchetab() {
        ObservableList<Reclamation> activs = FXCollections.observableArrayList();
    FilteredList <Reclamation> filter = new FilteredList <> (activs, e -> true);
    SortedList <Reclamation> sort = new SortedList<>(filter);
        cherchetab.setOnKeyReleased(e -> {
               cherchetab.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(Reclamation -> {
                if (newValue == null || newValue.isEmpty()) {
return true;
}
                String lowerCaseFilter = newValue.toLowerCase();
                if (Reclamation.getNomc().toLowerCase().contains(lowerCaseFilter) ) {
return true;}
                else  
    return false;
        });
       
        });
        sort.comparatorProperty().bind(Reclamations.comparatorProperty());
        Reclamations.setItems(sort);
    });
    }

    @FXML
    private void selectstatut(ActionEvent event) {
        tfstatus.setText(liststatut.getSelectionModel().getSelectedItem());
    }


    @FXML
    private void selectreclamation(MouseEvent event) {
        tfstatus.setText(Reclamations.getSelectionModel().getSelectedItem().getEtrc());
    }

    @FXML
    private void updatestatus(ActionEvent event) {
        Reclamation rec = Reclamations.getSelectionModel().getSelectedItem();
        rec.setEtrc(tfstatus.getText());
        rs.updateetat(rec);
        refreshTable();
    }

    @FXML
    private void Astuce(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("Astuce.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }

    @FXML
    private void Video(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("Video.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }

     @FXML
    private void Evenement(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("evenement.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }

    @FXML
    private void Reservation(ActionEvent event) throws IOException {
          Parent fxml;
        fxml = FXMLLoader.load(getClass().getResource("reservation.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }
    
    

 @FXML
    private void DeconecterAdmin(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = new Stage() ;
                stage.initStyle(StageStyle.TRANSPARENT);
                scene.setFill(Color.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
                closeAcceuil2();
            }    catch (IOException ex) {
                 System.out.println("Erreur 1 !!!");
            }

  }
     
    private void exit(MouseEvent event) {
        
       System.exit(0);
    }
  
   
    @FXML
    private void InspecterClient(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/View/Admin.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                //stage.setTitle("Inscription");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                 System.out.println("Erreur 1 !!!");
            }
    }
    public void closeAcceuil2() {
        Stage Acceuil2Stage = (Stage) btn_decnx.getScene().getWindow();
        Acceuil2Stage.hide();
    }
     private JFXButton btn_decnx;

    @FXML
    private void Produit(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("MenuArticle.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }


    @FXML
    private void Commande(ActionEvent event) throws IOException {
            Parent fxml = FXMLLoader.load(getClass().getResource("CommandesLivraison.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
        
    }

    @FXML
    private void Reclamation(ActionEvent event) {
    }

        
        
    }





