/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.UI.front;

import static Controller.ClientController1.cli;
import Controller.SigninController;
import static Controller.SigninController.login;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import entity.User;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import shahba.Service.ServiceCommande;

import shahba.entity.commande;

/**
 * FXML Controller class
 *
 * @author Anis
 */
public class CommandeController implements Initializable {

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField email;

    @FXML
    private TextField adresse;

    @FXML
    private TextField description_adresse;

    @FXML
    private ComboBox gouvernorat;

    @FXML
    private TextField code_postal;

    @FXML
    private TextField numero_telephone;

    @FXML
    private Button passer_commande;
    
       @FXML
    private AnchorPane displayArea;
    
  
    private ServiceCommande sco = new ServiceCommande();
    private User c = SigninController.login;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gouvernorat.getItems().clear();
        
        gouvernorat.getItems().addAll(
                "",
                "Tunis",
                "Ariana",
                "Ben Arous",
                "Mennouba",
                "Bizerte",
                "Nabeul",
                "Zaghouan",
                "Jendouba",
                "Béja",
                "Kasserine",
                "Seliana",
                "Sidi Bouzid",
                "Kairouan",
                "Sousse",
                "Monastir",
                "Mahdia",
                "Sfax",
                "Gabés",
                "Medenine",
                "Tataouine",
                "Gafsa",
                "Kébili",
                "Tozeur",
                "Le Kef");
        nom.setText(c.getUsername());
        prenom.setText(c.getUsername());
        email.setText(c.getEmail());
        numero_telephone.setText(String.valueOf(c.getUserphone()));
        
    }   
    
    
    @FXML
    void commander(ActionEvent event) throws SQLException, IOException {
        
        commande co;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(adresse.getText().equals("") || description_adresse.getText().equals("") || gouvernorat.getSelectionModel().getSelectedItem().toString().equals("") || numero_telephone.getText().equals("") || code_postal.getText().equals(""))
        {
          
            alert.setTitle("Commande");
            alert.setHeaderText("");
            alert.setContentText("Veuillez renseigner tous les champs!");
            alert.showAndWait();  
        }else{
            if(!numero_telephone.getText().matches("\\d+"))
            {
             alert.setTitle("Commande");
            alert.setHeaderText("");
            alert.setContentText("Numero Telephone doit etre composé par des chiffres !");
            alert.showAndWait(); 
            }
           else if(!code_postal.getText().matches("\\d+"))
            {
             alert.setTitle("Commande");
            alert.setHeaderText("");
            alert.setContentText("Code Postal doit etre composé par des chiffres !");
            alert.showAndWait(); 
            }
           else if(code_postal.getText().length()!=4)
            {
                alert.setTitle("Commande");
            alert.setHeaderText("");
            alert.setContentText("Code Postal doit etre composé par 4 chiffres !");
            alert.showAndWait();  
            }
           else if(numero_telephone.getText().length() != 8)
            {
                alert.setTitle("Commande");
            alert.setHeaderText("");
            alert.setContentText("Numéro téléphone doit etre composé par 8 chiffres !");
            alert.showAndWait();  
            }
            else
           {
              co = new commande(c, 15f, adresse.getText(), description_adresse.getText(), gouvernorat.getSelectionModel().getSelectedItem().toString(), Integer.valueOf(code_postal.getText()), Integer.valueOf(numero_telephone.getText()));
        sco.ajouter(co);
        Parent fxml = FXMLLoader.load(getClass().getResource("Livraison.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
           }
        }
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
    private void commande(ActionEvent event) throws IOException {
         Parent fxml = FXMLLoader.load(getClass().getResource("Commande.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
        
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
    private void Produit(ActionEvent event) throws IOException {
                  Parent fxml = FXMLLoader.load(getClass().getResource("test1.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }

   @FXML
    private void Reclamation(ActionEvent event) throws IOException {
              Parent fxml = FXMLLoader.load(getClass().getResource("TestAhmed.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }

    
  
    
}
