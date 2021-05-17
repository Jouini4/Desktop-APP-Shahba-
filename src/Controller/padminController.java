/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage; 
import Utils.Email;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author user
 */
public class padminController implements Initializable {

@FXML
    private AnchorPane displayArea;
    private AnchorPane rootLayout;
    private Stage primaryStage;
    @FXML
    private Label X;
    private JFXButton btn_decnx;
    Email email = new Email();
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
    private Button btnOverview12;
    @FXML
    private Button btnSignout;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
      
    }    

    
    @FXML
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

   @FXML
    private void Astuce(ActionEvent event) throws IOException {
         Parent fxml = FXMLLoader.load(getClass().getResource("/shahba/UI/back/Astuce.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }

    @FXML
    private void Video(ActionEvent event) throws IOException {
         Parent fxml = FXMLLoader.load(getClass().getResource("/shahba/UI/back/Video.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }

  
    @FXML
    private void Evenement(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/shahba/UI/back/evenement.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }
    @FXML
    private void Reservation(ActionEvent event) throws IOException {
        Parent fxml;
        fxml = FXMLLoader.load(getClass().getResource("/shahba/UI/back/reservation.fxml"));
      displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }

   @FXML
    private void Produit(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/shahba/UI/back/MenuArticle.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }


    @FXML
    private void Commande(ActionEvent event) throws IOException {
            Parent fxml = FXMLLoader.load(getClass().getResource("/shahba/UI/back/CommandesLivraisons.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
        
    }

         @FXML
    private void Reclamation(ActionEvent event) throws IOException {
                 Parent fxml = FXMLLoader.load(getClass().getResource("/shahba/UI/back/back_ui.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
        
    }

    }


