/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.forgetpwdController.cc;
import Utils.Email;
import com.jfoenix.controls.JFXDatePicker;
import entity.User;
import entity.Userdao;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author user
 */
public class SignupController extends MainController implements Initializable{
    @FXML
    private VBox vbox1;
    @FXML
    public TextField text_nom;
    public TextField text_prenom;
    @FXML
    public TextField text_mail;
    @FXML
    public PasswordField text_mdp;
    
    @FXML
    public TextField text_tel;
   
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
    Userdao crud = new Userdao();
    Email email = new Email();
    public static String  code;
    public static User User1 = null;
    @FXML
    private TextField text_cin;
    @FXML
    private TextField text_address;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public static boolean isValidEmailAddress(String email) {
   boolean result = true;
   try {
      InternetAddress emailAddr = new InternetAddress(email);
      emailAddr.validate();
   } catch (AddressException ex) {
      result = false;
   }
   return result;
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
    public void ins(ActionEvent event) throws IOException {
        if(isValidEmailAddress(text_mail.getText())){
            if(crud.VerifyUserByEmail(text_mail.getText())){
                vmail.setText("Already Registred");
            }else{
                
        User1 = new User(text_nom.getText(), text_mail.getText(), text_mdp.getText(),+Integer.parseInt(text_tel.getText()), +Integer.parseInt(text_cin.getText()), text_address.getText(),"[]");
        cc=text_mail.getText();
        code = email.getPassword();
        email.sendEmail1(cc,code);
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/View/verifmail.fxml"));
                Scene scene = new Scene(page1);
               
                Stage stage=new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                 System.out.println("Erreur chh !!!");
            }
         
        } }else{
            vmail.setText("E-Mail Invalid");
        }
    }

    @FXML
    private void passs(MouseEvent event) {
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
    private void passs2(KeyEvent event) {
        if (event.getCode() == KeyCode.TAB) {
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
    }}
}

   
    
