/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.UI.back;

import shahba.entity.Categorie;
import shahba.Service.ServiceCategorie;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author rekik
 */
public class FXMLController implements Initializable {

    @FXML
    private TextField ttitre;
    @FXML
    private TableView<Categorie> tabv;
    @FXML
    private Button tbtn;
    @FXML
    private Button tbtn1;
    @FXML
    private Button Menu_Article;
    @FXML
    private TableColumn<Categorie, String> ColCat;
    private Stage primaryStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherArt();

        ContextMenu ContArticle = new ContextMenu();
        MenuItem DeleteItem = new MenuItem("Supprimer catégorie");

        DeleteItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /*Publication pub_supp = (Publication) tableView_Publication.getSelectionModel().getSelectedItem();
                CellEditEvent eddited_cell;*/
                Object item = tabv.getSelectionModel().getSelectedItem();
                Categorie art = (Categorie) item;
                ServiceCategorie s = new ServiceCategorie();
                System.out.println(art.toString());
                s.supprimer(art);

                AfficherArt();

            }
        }
        );

        EventHandler<WindowEvent> event = new EventHandler<WindowEvent>() {
            public void handle(WindowEvent e) {
                if (ContArticle.isShowing()) {
                    // System.out.println("Showing");
                } else {
                    //System.out.println("Hidden");
                }
            }
        };

        ContArticle.getItems().add(DeleteItem);

        ContArticle.setOnShowing(event);
        ContArticle.setOnHiding(event);
        tabv.setContextMenu(ContArticle);
        //Object item = tableView_Publication.getSelectionModel().getSelectedItem();

    }

    private void AfficherArt() {
        ObservableList<Categorie> ArtList = FXCollections.observableArrayList();
        ColCat.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
        ServiceCategorie srec = new ServiceCategorie();
        srec.afficher().forEach(e -> {
            ArtList.add(e);
        });
        tabv.setItems(ArtList);
        
        
//        NumberValidator num = new NumberValidator();
//        ttitre.getValidators().add(num);
//        num.setMessage("only numbers are supported!!");
//        ttitre.focusedProperty().addListener(new ChangeListener<Boolean>(){
//            @Override
//            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//              if(!newValue) {
//              ttitre.validate();
//              }
//            }
//        });
   
    }

    @FXML
    private void AjoutCat(ActionEvent event) throws IOException {
        if (ttitre.getText().isEmpty()) {
            // JOptionPane.showMessageDialog(null, "Remplir le champs vide");
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Veuillez remplir les champs vides ! ");
            al.showAndWait();

        } else if ( ttitre.getText().matches(".*[0-9].*")||ttitre.getText().matches(".*[%-@-.-/-!-;-,-_].*")) {
            Alert a2 = new Alert(Alert.AlertType.ERROR);
            a2.setHeaderText(null);
            a2.setContentText("Veuillez saisir uniquement des lettres ! ");
            a2.showAndWait();

        } else {

            ServiceCategorie sa = new ServiceCategorie();
            Categorie test = new Categorie(ttitre.getText());
            //AjouterPhoto(event);
            sa.ajouter(test);
            AfficherArt();
//        Stage window = primaryStage;
//        Parent rootRec2 = FXMLLoader.load(getClass().getResource("MenuArticle.fxml"));
//        Scene rec2 = new Scene(rootRec2);
//        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        app.setScene(rec2);
//        app.show();
        }
    }

    @FXML
    private void Annuler(ActionEvent event) {
        ttitre.setText("");
    }

    @FXML
    private void Menu(ActionEvent event) throws IOException {
        Stage window = primaryStage;
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("MenuArticle.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }
    
}
