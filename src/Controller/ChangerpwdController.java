/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXPasswordField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import Controller.forgetpwdController;
import com.jfoenix.controls.JFXTextField;
import static Controller.forgetpwdController.cc;
import entity.User;
import entity.Userdao;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ChangerpwdController implements Initializable {
     

    @FXML
    private JFXPasswordField pass1;
    @FXML
    private JFXPasswordField pass2;
    @FXML
    private Button cha;
    @FXML
    private Label err;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //err.setText(cc);
    }    
      public void closeChangerpwd() {
        Stage Acceuil2Stage = (Stage) pass1.getScene().getWindow();
        Acceuil2Stage.hide();
    }

    @FXML
    private void changer(ActionEvent event) {
        
        String p1 = pass1.getText();
        String p2 = pass2.getText();
        
        Userdao crud = new Userdao();
        

        
        if(p1.equals(p2)){
            
            crud.updateByemail(cc, p1);
            closeChangerpwd();
            Notifications.create()
              .title("Welcome To Shahba")
              .text("Password Was Changed Successfully")
              .hideAfter(Duration.seconds(6))
              .position(Pos.TOP_RIGHT)
                    .showInformation();
            //closeChangerpwd;
            
            /** try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/View/Client.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                //stage.setTitle("Inscription");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                 System.out.println("Erreur chh !!!");
            }**/
            
        }else{
            err.setText("Password False !!");
        }
    
    
}
}
