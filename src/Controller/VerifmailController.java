/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.MainController.svbox;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.util.Duration;
import static Controller.SignupController.code;
import static Controller.SignupController.User1;
import static Controller.forgetpwdController.c;
import entity.User;
import entity.Userdao;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author user
 */
public class VerifmailController extends SignupController implements Initializable {

    @FXML
    private JFXButton verify;
    @FXML
    private JFXTextField code1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void getpass(ActionEvent event) {
        if(code.equals(code1.getText())){
            //User User1 = new User(text_nom.getText(), text_prenom.getText(), text_mail.getText(), text_mdp.getText(),java.sql.Date.valueOf(text_date.getValue()),text_tel.getText(), img.getText(), java.sql.Date.valueOf(asLocalDate(date)));
            Userdao udao = Userdao.getInstance();
            udao.insert(User1);
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            Notifications.create()
              .title("Welcome To Shahba")
              .text("Account Created Successfully")
              .hideAfter(Duration.seconds(6))
              .position(Pos.TOP_RIGHT)
                    .showInformation();
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), svbox);
        t.setToX(svbox.getLayoutX() * 20);
       t.play();
        t.setOnFinished((e) ->{
           try{
                Parent fxml = FXMLLoader.load(getClass().getResource("/View/Signin.fxml"));
                svbox.getChildren().removeAll();
                svbox.getChildren().setAll(fxml);
           }catch(IOException ex){
                
            }
        });}else{
            JOptionPane.showMessageDialog(null, "Invalid Code !!");
            code1.clear();
        }
    }
    
}
