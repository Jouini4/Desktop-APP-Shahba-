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
import static Controller.SigninController.login;
import Utils.Connexion;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
/**
 * FXML Controller class
 *
 * @author user
 */
public class ClientController1 implements Initializable {
    public Userdao crud;
 @FXML
    private AnchorPane displayArea;
    @FXML
    private Label nom;
    Userdao dao;
    User User;
    @FXML
    private Label X;
    @FXML
    private Button btn_decnx;
    private ImageView im;
    Connection connection = null ;
    public static User cli;
    public static Circle scirc;
    public static Label snom;
    public static Label spren;
    public static Label sport;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nom.setText(login.getUsername());
        snom=nom;
       
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
     public void closeAcceuil2() {
        Stage Acceuil2Stage = (Stage) nom.getScene().getWindow();
        Acceuil2Stage.hide();
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
     private void executeQuery(String query) {
         connection=Connexion.getInstance().getCnx();
      try {
          Statement st = connection.createStatement();
          st.executeUpdate(query);
  
      }catch (Exception ex){
          ex.printStackTrace();   
      }
      
     
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
    void Reservation(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/shahba/UI/front/reservation.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }
    @FXML
    void Event(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/shahba/UI/front/Test.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }    

   @FXML
    private void Home(ActionEvent event) throws IOException {
                Parent fxml = FXMLLoader.load(getClass().getResource("/shahba/UI/front/Home.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }

      @FXML
       private void Astuce(ActionEvent event) throws IOException {
              Parent fxml = FXMLLoader.load(getClass().getResource("/shahba/UI/front/Astuce.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }
        @FXML
    private void Video(ActionEvent event)throws IOException {
          Parent fxml = FXMLLoader.load(getClass().getResource("/shahba/UI/front/Video.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }
}
