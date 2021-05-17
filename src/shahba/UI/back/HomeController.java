/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.UI.back;

import com.jfoenix.controls.JFXButton;
import java.awt.event.MouseEvent;
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
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author isslem
 */
public class HomeController implements Initializable {

    @FXML
    private AnchorPane displayArea;
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
    private Button btnSignout;
    @FXML
    private Button btnOverview11;
    @FXML
    private Button btnOverview111;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
     
    private void exit(javafx.scene.input.MouseEvent event) {
        
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
            Parent fxml = FXMLLoader.load(getClass().getResource("CommandesLivraisons.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
        
    }

       @FXML
    private void Reclamation(ActionEvent event) throws IOException {
                 Parent fxml = FXMLLoader.load(getClass().getResource("back_ui.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
        
    }
 
  


    
}
