/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.UI.front;

import static Controller.ClientController1.cli;
import static Controller.SigninController.login;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import shahba.Service.ReservationService;

import shahba.entity.reservation;
import shahba.utils.MyConnexion;

/**
 * FXML Controller class
 *
 * @author HAMMOUDA
 */
public class ReservationController implements Initializable {
    private Label nom;
    private Button btnajouter;
    @FXML
    private TableView<reservation> table;
    private TextField r_nbrplace;
    private TextField r_approuve;
    private TextField r_utilisateur;
    @FXML
    private TableColumn<reservation, Integer> rt_nbrplace;
    @FXML
    private TableColumn<reservation, Boolean> rt_approuve;
    @FXML
    private TableColumn<reservation, String> rt_utilisateur;
        shahba.Service.ReservationService cr = new ReservationService();
    ObservableList<reservation> data = FXCollections.observableArrayList(cr.getAll());
    @FXML
    private AnchorPane displayArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
                 // TODO
            rt_nbrplace.setCellValueFactory(new PropertyValueFactory("nbrplace"));
            rt_approuve.setCellValueFactory(new PropertyValueFactory("status"));
            rt_utilisateur.setCellValueFactory(new PropertyValueFactory("nom_event"));
      
            table.setItems(data);

        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (table.getSelectionModel().getSelectedItem() != null) {
                    shahba.entity.reservation r = (shahba.entity.reservation) table.getSelectionModel().getSelectedItem();
                     System.out.println();
                      r_nbrplace.setText(Integer.toString(r.getNbrplace()));
                      r_approuve.setText(String.valueOf (r.getApprouve()));
                    //  r_utilisateur.setText(String.valueOf(r.getUser_id()));
                    
                 
                    btnajouter.setDisable(true);
                    
                }
            }
            
        });
        
    }    
      
       private void clear() {
        table.getSelectionModel().clearSelection();
        r_nbrplace.clear();
        r_approuve.clear();
        r_utilisateur.clear();
         btnajouter.setDisable(false);
   
    }


   
     @FXML
    void Reservation(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("reservation.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }
    @FXML
    void Event(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("Test.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }

    @FXML
    private void Home(ActionEvent event) throws IOException {
                Parent fxml = FXMLLoader.load(getClass().getResource("Home.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
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
     public void closeAcceuil2() {
        Stage Acceuil2Stage = (Stage) nom.getScene().getWindow();
        Acceuil2Stage.close();
    }
     
       @FXML
       private void Astuce(ActionEvent event) throws IOException {
              Parent fxml = FXMLLoader.load(getClass().getResource("Astuce.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }
        @FXML
    private void Video(ActionEvent event)throws IOException {
          Parent fxml = FXMLLoader.load(getClass().getResource("Video.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }

     @FXML
    private void commande(ActionEvent event) throws IOException {
         Parent fxml = FXMLLoader.load(getClass().getResource("Commande.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
        
    }


    @FXML
    private void Reclamation(ActionEvent event) throws IOException {
              Parent fxml = FXMLLoader.load(getClass().getResource("TestAhmed.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }
   @FXML
    private void Produit(ActionEvent event) throws IOException {
                  Parent fxml = FXMLLoader.load(getClass().getResource("test1.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }
   
  
    
}
