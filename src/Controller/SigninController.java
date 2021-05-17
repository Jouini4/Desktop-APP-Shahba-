/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import entity.User;
import entity.Userdao;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class SigninController implements Initializable {

    @FXML
    private TextField mail;
    @FXML
    private TextField pass;
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    private  Userdao crud;
    public static User us;
    public User u;
   // private Parent fxml;
    @FXML
    private VBox vbox;
    public static User login;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         crud = new Userdao();

        u = new User();
        // TODO
    }        
     public Boolean AuthenticateUser(String email, String mdp) {
        u = crud.VerifyUser(email, mdp);
        //User u = crud.VerifyUser(email, mdp);
        if ((email.equals(u.getEmail())) && (mdp.equals(u.getPassword())) || (email.equals(u.getUsername())) && (mdp.equals(u.getPassword()))) {
            return true;
        } else {
            return false;
        }
    }
      public Boolean AuthenticateUser1(String email, String mdp) {
        u = crud.VerifyUser1(email, mdp);
        //User u = crud.VerifyUser(email, mdp);
        if ((email.equals(u.getEmail())) && (mdp.equals(u.getPassword())) || (email.equals(u.getUsername())) && (mdp.equals(u.getPassword()))) {
            return true;
        } else {
            return false;
        }
    }
  public void Login() {
        Stage appStage;
        Parent root;

        if (AuthenticateUser(mail.getText(), pass.getText())) {
            u = crud.VerifyUser(mail.getText(), pass.getText());
            int iduser = u.getId();
            login = crud.UserById(iduser);
            
          
            
                 try {

                Parent page1 = FXMLLoader.load(getClass().getResource("/shahba/UI/front/Home.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = new Stage();
                stage.setTitle("Acceuil");
                stage.setScene(scene);
                stage.show();
                closeAcceuil2();

            } catch (IOException ex) {
                System.out.println("Erreur 44 !!!");
            }
            
            
        } else  if (AuthenticateUser1(mail.getText(), pass.getText())) {
            u = crud.VerifyUser1(mail.getText(), pass.getText());
            int iduser = u.getId();
            login = crud.UserById(iduser);
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/View/padmin.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = new Stage();
                stage.setTitle("Acceuil");
                stage.setScene(scene);
                stage.show();
                closeAcceuil2();

            } catch (IOException ex) {
                System.out.println("Erreur 44 !!!");
            }

        }else{
            JOptionPane.showMessageDialog(null, "Invalid E_Mail / Password !");
            mail.clear();
            pass.clear();
        }
    }
     public void closeAcceuil2() {
        Stage Acceuil2Stage = (Stage) mail.getScene().getWindow();
        Acceuil2Stage.hide();
    }
     
    @FXML
    private void cnx(ActionEvent event) {
         Login();
       

    }

    @FXML
    private void pass_obl(ActionEvent event) throws IOException {
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/View/forgetpwd.fxml"));
                Scene scene = new Scene(page1);
                 Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                 System.out.println("Erreur mdp ob !!!");
            }
        
    };

    @FXML
    private void EntrKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            Login();    
        }
    }

   
    }

        
 
    
