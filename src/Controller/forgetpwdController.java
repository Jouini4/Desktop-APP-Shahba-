/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Utils.Email;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entity.Userdao;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class forgetpwdController implements Initializable {
    Email email = new Email();
    String code;
    public static String c;
    public static String cc;
    @FXML
    public JFXTextField codeclient;
    @FXML
    private JFXButton verify;
    @FXML
    private JFXTextField em;
    Userdao crud = new Userdao();
    @FXML
    private JFXButton envoyer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //code = email.getPassword();
        //email.sendEmail("melek.hentati@esprit.tn",code);

        // TODO
    }    

    @FXML
    private void getpass(ActionEvent event) {
         cc=em.getText();
        c=codeclient.getText();
        if(code.equals(c)){
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/View/Changerpwd.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                //stage.setTitle("Inscription");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                 System.out.println("Erreur chh !!!");
            }
            
        }else{
            
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Enter Correct Code !!!");
        alert.show();
        }

    }

    @FXML
    private void envouyercode(ActionEvent event) {
        cc=em.getText();
        code = email.getPassword();
        if (crud.VerifyUserByEmail(cc)){
            
             email.sendEmail(cc,code);
             
        }else{
            
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("You Don't Have Account !!!");
        alert.show();
            
        }
       
    }

    private void exit(MouseEvent event) {
        System.exit(0);
    }

    private void retour(MouseEvent event) {
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/View/Acceuil2.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                //stage.setTitle("Inscription");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                 System.out.println("Erreur chh !!!");
            }
        
    }
    
}
