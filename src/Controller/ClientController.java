/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import entity.User;
import entity.Userdao;
import java.io.IOException;
import java.net.URL;
import static java.time.Clock.system;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
/**
 * FXML Controller class
 *
 * @author user
 */
public class ClientController implements Initializable {
    public Userdao crud;

    @FXML
    private Label nom;
    Userdao dao;
    User User;
    @FXML
    private Label X;
    @FXML
    private JFXButton btn_decnx;
    @FXML
    private ImageView im;
    @FXML
    private Label pren;
    @FXML
    private Label port;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     //   if (insc==false){
      //  nom.setText(loguser.getNom());
      //  pren.setText(loguser.getPrenom());
       // port.setText(String.valueOf(loguser.getPortfeuille()));
      //  }else{ 
         //   loguser=crud.UserByemail(insuser);
            //loguser=insuser;
       //     nom.setText(loguser.getNom());
      //  pren.setText(loguser.getPrenom());
       // port.setText(String.valueOf(loguser.getPortfeuille()));
     //   }
      //       nom.setText(insuser.getNom());
        //pren.setText(insuser.getPrenom());
        //port.setText(String.valueOf(insuser.getPortfeuille()));   
       
      //  }
       //Stage stage =(Stage)(nom.getScene().getWindow());
       //User.setId(Integer.parseInt(stage.getTitle()));
       //User = dao.UserById(User.getId());
       //nom.setText(User.getNom());
       //stage.setTitle("Hay");
      
      // prenomm.setText(User.getPrenom());
       //potff.setText(String.valueOf(User.getPortfeuille()));
        /**int i = iduser;
        User user = new User();
        Userdao crud = new Userdao();
        user= crud.UserById(i);
        nomm.setText(user.getNom());
        potff.setText(String.valueOf(user.getPortfeuille()));**/
       
    }    


    /**private void afficher(ActionEvent event) {
        User User = new User();
       Stage stage =(Stage)(nom.getScene().getWindow());
      String t =stage.getTitle();
      int i =Integer.parseInt(t);
     User = dao.UserById(i);
     // User.setId(Integer.parseInt());
      //User.toString();
     String nomm= User.getNom();
      nom.setText(nomm);
       //prenom.setText(User.getPrenom());
       System.out.println(i);
        
    }**/

    @FXML
    private void exit(MouseEvent event) {
        System.exit(0);
       
         // String n =User.getNom();
          //nomm.setText(n);
    }

    @FXML
    private void Deconecterclient(ActionEvent event) {
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.initStyle(StageStyle.TRANSPARENT);
               //stage.setTitle("Inscription");
               scene.setFill(Color.TRANSPARENT);
                stage.setScene(scene);
                
                stage.show();
            } catch (IOException ex) {
                 System.out.println("Erreur 1 !!!");
            }
    }
    
}
