/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import static Controller.ClientController1.cli;
import static Controller.SigninController.login;
import static Controller.SignupController.isValidEmailAddress;
import Utils.Connexion;
import Utils.Email;
import entity.User;
import entity.Userdao;
import java.io.File;
import java.io.IOException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ProfclientController extends ClientController1 implements Initializable {
    @FXML
    private TextField text_nom;
    private TextField text_prenom;
    @FXML
    private TextField text_mail;
    @FXML
    private PasswordField text_mdp;
    private JFXDatePicker text_date;
    @FXML
    private TextField text_tel;
    private Circle tof;
    Calendar calendar = Calendar.getInstance();
    @FXML
    private Label vmail;
    @FXML
    private Label tsh;
    @FXML
    private Label sh;
    @FXML
    private Label m;
    @FXML
    private Label f;
    String photo = "";
    private Label textimage;
     Email email = new Email();
     public static User cli1 = null;
    @FXML
    private AnchorPane par;
    public static AnchorPane svbox;
    public static String  code;
     Userdao crud1 = new Userdao();
    @FXML
    private TextField text_cin;
    @FXML
    private TextField text_address;
    private AnchorPane displayArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        svbox=par;
        text_nom.setText(cli.getUsername());
        text_mail.setText(cli.getEmail());
        text_mdp.setText(cli.getPassword());
        text_tel.setText(String.valueOf(cli.getUserphone()));
        text_cin.setText(String.valueOf(cli.getUsercin()));
        text_address.setText(cli.getUseraddress());
        // TODO
    }  
     public String PasswordStrengthValidation(String psw){
        int uppercase = 0;
        int lowercase = 0;
        int specialcharacters = 0;
        int digits = 0;
        char[] Password = psw.toCharArray();
        for (int index = 0; index < psw.length(); index++)
        {
            if (Character.isUpperCase(Password[index]))
            {
                uppercase = 1;
            }
            if (Character.isLowerCase(Password[index]))
            {
                lowercase = 1;
            }
            if (Character.isDigit(Password[index]))
            {
                digits = 1;
            }
        }
        if (psw.contains("~") || psw.contains("!") || psw.contains("@")
            || psw.contains("#") || psw.contains("$") || psw.contains("%")
            || psw.contains("^") || psw.contains("&") || psw.contains("*")) ;
        {
            specialcharacters = 1;
        }
        if (psw.length() >= 8 && (uppercase == 1) && (lowercase == 1) && (digits == 1) && (specialcharacters == 1)){
            return"Strong";
        } 
        if ((psw.length() >= 8 && (((uppercase == 1) && (lowercase == 1) && (digits == 1)) || ((specialcharacters == 1)
            && (uppercase == 1) && (lowercase == 1)) || ((uppercase == 1) && (digits == 1) && (specialcharacters == 1)) || 
                ((lowercase == 1) && (digits == 1) && (specialcharacters == 1))))){

             return"Medium";
        }
        
        if (psw.length() >= 8 && ((uppercase == 1) || (lowercase == 1) || (digits == 1) || (specialcharacters == 1))){

            return "Weak";
        }
            return "Too Short";
    }

    @FXML
    private void pass(MouseEvent event) {
        text_mdp.textProperty().addListener((observable,oldValue,newValue)-> {
           if(PasswordStrengthValidation(text_mdp.getText()).equalsIgnoreCase("Too Short")){
            tsh.setText("Too Short");
            f.setText("");
            m.setText("");
            sh.setText("");
          //  tsh.setText("");
        }else if(PasswordStrengthValidation(text_mdp.getText()).equalsIgnoreCase("Weak")){
            sh.setText("Weak");
            tsh.setText("");
            f.setText("");
            m.setText("");
        }else if(PasswordStrengthValidation(text_mdp.getText()).equalsIgnoreCase("Medium")){
            m.setText("Medium");
            sh.setText("");
            tsh.setText("");
            f.setText("");
        }else{
            f.setText("Strong");
            m.setText("");
            sh.setText("");
            tsh.setText("");
        }
        });
    }


    @FXML
    private void modify(ActionEvent event) {
        /**
         *   if(isValidEmailAddress(text_mail.getText())){
            if(crud.VerifyUserByEmail(text_mail.getText())){
                vmail.setText("Already Registred");
            }else{
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date date = new Date();
                Calendar cal = Calendar.getInstance();
        User1 = new User(text_nom.getText(), text_prenom.getText(), text_mail.getText(), text_mdp.getText(),java.sql.Date.valueOf(text_date.getValue()),text_tel.getText(), img.getText(), java.sql.Date.valueOf(asLocalDate(date)));
        cc=text_mail.getText();
        code = email.getPassword();
        email.sendEmail1(cc,code);
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/View/verifmail.fxml"));
                Scene scene = new Scene(page1);
                //Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                //stage.setTitle("Inscription");
                Stage stage=new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                 System.out.println("Erreur chh !!!");
            }
         
        } }else{
            vmail.setText("E-Mail Invalid");
        }
         * 
         */
        if(cli.getEmail().equals(text_mail.getText())){
            cli.setUsername(text_nom.getText());
        cli.setEmail(text_mail.getText());
        cli.setPassword(text_mdp.getText());
        cli.setUserphone(Integer.parseInt(text_tel.getText()));
        cli.setUsercin(Integer.parseInt(text_cin.getText()));
        cli.setUseraddress(text_address.getText());
        String query = "UPDATE user SET  username = '" + text_nom.getText()+
             "', email = '"+text_mail.getText()+ 
             "', password = '"+text_mdp.getText()+
             "', userphone = '"+text_tel.getText()+
             "', usercin = '"+Integer.parseInt(text_cin.getText())+
             "', useraddress = '"+text_address.getText()+ 
             "' WHERE id = '" + cli.getId()
             +
             "'";
       executeQuery(query);
             JOptionPane.showMessageDialog(null, "Modification termine");
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
        snom.setText(cli.getUsername());
        }else{if(isValidEmailAddress(text_mail.getText())){
             if(crud1.VerifyUserByEmail(text_mail.getText())){
                vmail.setText("Already Registred");
             }else{
                cli.setUsername(text_nom.getText());
        cli.setEmail(text_mail.getText());
        cli.setPassword(text_mdp.getText());
        cli.setUserphone(Integer.parseInt(text_tel.getText()));
        cli.setUsercin(Integer.parseInt(text_cin.getText()));
        cli.setUseraddress(text_address.getText());
                 cli1=cli;
                 String cc = text_mail.getText();
                 code = email.getPassword();
                 email.sendEmail1(cc,code);
                 try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/View/verifmail_1.fxml"));
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
                }else{
            vmail.setText("E-Mail Invalid");
        }
        
    }
    }

     private void executeQuery(String query) {
         connection=Connexion.getInstance().getCnx();
      try {
          Statement st = connection.createStatement();
          st.executeUpdate(query);
  
      }catch (Exception ex){
          ex.printStackTrace();   
      }
      
     
    }


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
     
    

   void Reservation(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/shahba/UI/front/reservation.fxml"));
        par.getChildren().clear();
        par.getChildren().add(fxml);
    }
    void Event(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/shahba/UI/front/Test.fxml"));
        par.getChildren().clear();
        par.getChildren().add(fxml);
    }    
  @FXML
    private void Home(ActionEvent event) throws IOException  {
                       
 Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        //stage.setMaximized(true);
        stage.close();
        
    }

       private void Astuce(ActionEvent event) throws IOException {
              Parent fxml = FXMLLoader.load(getClass().getResource("/shahba/UI/front/Astuce.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }
    private void Video(ActionEvent event)throws IOException {
          Parent fxml = FXMLLoader.load(getClass().getResource("/shahba/UI/front/Video.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }


    
}
