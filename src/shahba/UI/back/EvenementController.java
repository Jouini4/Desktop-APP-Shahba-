/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.UI.back;



import com.jfoenix.controls.JFXButton;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import shahba.entity.evenement;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import shahba.Service.EvenementService;

/**
 * FXML Controller class
 *
 * @author HAMMOUDA
 */
public class EvenementController implements Initializable {

    @FXML
    private TextField ev_nom;
    @FXML
    private TextField ev_desc;
    @FXML
    private TextField ev_prix;
    @FXML
    private TextField ev_nombr;
    @FXML
    private Button btnajouter;
    @FXML
    private DatePicker ev_date;
     
    
    EvenementService cr = new EvenementService();
    ObservableList<evenement> data = FXCollections.observableArrayList(cr.getAll());;
    @FXML
    private TableColumn<evenement, String> ev_name;
    @FXML
    private TableColumn<evenement, String> ev_descr;
    @FXML
    private TableColumn<evenement, Date> event_date;
    @FXML
    private TableColumn<evenement, String> event_prix;
    @FXML
    private TableColumn<evenement, Integer> event_amount;
    @FXML
    private TableView<evenement> table;
    @FXML
    private Button btnsupp;
    @FXML
    private Button btnmodif;
    @FXML
    private TextField recherche;
    @FXML
    private TextField ev_image;
    @FXML
    private TableColumn<evenement , String> event_image;
    @FXML
    private ImageView imview;
    @FXML
    private Button btnclear;
    @FXML
    private Button upload;
    @FXML
    private TableColumn<evenement, String> ev_adresse;
    @FXML
    private TextField evt_adresse;
    @FXML
    private AnchorPane displayArea;
    @FXML
    private TableColumn<evenement, Double> tableau_longitude;
    @FXML
    private TableColumn<evenement, Double> tableau_latitude;
    @FXML
    private TextField txt_longitude;
    @FXML
    private TextField txt_latitude;
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
    private Button btnOverview111;
    @FXML
    private Button btnSignout;
   
  
public void updatetable (){
          ev_name.setCellValueFactory(new PropertyValueFactory("nom_event"));
         ev_descr.setCellValueFactory(new PropertyValueFactory("description_event"));
        event_date.setCellValueFactory(new PropertyValueFactory("date"));
          event_prix.setCellValueFactory(new PropertyValueFactory("prix_event"));
         event_amount.setCellValueFactory(new PropertyValueFactory("nbr_place"));
         event_image.setCellValueFactory(new PropertyValueFactory("image"));
          ev_adresse.setCellValueFactory(new PropertyValueFactory("adresse"));
          tableau_longitude.setCellValueFactory(new PropertyValueFactory("longitude"));
          tableau_latitude.setCellValueFactory(new PropertyValueFactory("latitude"));
        table.setItems(data);
}
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         // TODO
    
         updatetable ();

        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (table.getSelectionModel().getSelectedItem() != null) {
                    shahba.entity.evenement e = (shahba.entity.evenement) table.getSelectionModel().getSelectedItem();
                     System.out.println();
                      ev_nom.setText(e.getNom_event());
                      ev_desc.setText(e.getDescription_event());
                     ev_date.setValue(e.getDate().toLocalDate());
                      ev_prix.setText(e.getPrix_event());
                       ev_nombr.setText(Integer.toString (e.getNbr_place()));
                     ev_image.setText(e.getImage());
                     evt_adresse.setText(e.getAdresse());
                     txt_longitude.setText(Double.toString (e.getLongitude()));
                      txt_latitude.setText(Double.toString (e.getLatitude()));
                 
                    btnajouter.setDisable(true);
                    
                }
            }
        });
        recherche.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
               filtrerEventList((String) oldValue, (String) newValue);
            }

        });
    }    
   
  void filtrerEventList(String oldValue, String newValue) {
        EvenementService evs = new EvenementService();
        ObservableList<evenement> filteredList = FXCollections.observableArrayList();
        if (recherche.getText() == null || newValue == null) {
            table.setItems((ObservableList<evenement>) evs.FindEvent());
        } else {
            table.setItems((ObservableList<evenement>) evs.FindEvent());
            newValue = newValue.toUpperCase();

            for (evenement e : table.getItems()) {

                String filterEventName = e.getNom_event();
               

                if (filterEventName.toUpperCase().contains(newValue)) {
                    filteredList.add(e);
                }
            }
            table.setItems(filteredList);
        }
    }

    @FXML
    private void Ajout(ActionEvent event) {
   
           if (ev_nom.getText() == null || ev_nom.getText().trim().isEmpty()) {
             Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir le champ de nom s'il vous plait!");
            dialogW.showAndWait();
            } 
           else if (ev_desc.getText() == null || ev_desc.getText().trim().isEmpty()) {
             Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir le champ de discription s'il vous plait!");
            dialogW.showAndWait();
            } 
            else if (ev_date.getValue()== null ){
		Alert dialogW = new Alert(Alert.AlertType.WARNING);
		dialogW.setTitle("A warning");
 		dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez inserer la date  s'il vous plait!");
            dialogW.showAndWait();
        }     
        
             else if (ev_prix.getText() == null || ev_prix.getText().trim().isEmpty()) {
             Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir le champ de prix s'il vous plait!");
            dialogW.showAndWait();
            } 
   
              
              else {
                   evenement e = new shahba.entity.evenement(ev_nom.getText(), ev_desc.getText(),Date.valueOf(ev_date.getValue()),ev_prix.getText(),Integer.valueOf(ev_nombr.getText()),ev_image.getText(),evt_adresse.getText(),Double.valueOf (txt_longitude.getText()),Double.valueOf (txt_latitude.getText()));
       cr.createEvenement(e);
              }
           
     data.removeAll(data);
         for (evenement ev : FXCollections.observableArrayList(cr.getAll())) {
            data.add(ev);

        }
          updatetable ();
         clear();
        

        
    }

    private void clear() {
        table.getSelectionModel().clearSelection();
        ev_nom.clear();
        ev_desc.clear();
        ev_date.setValue(null);
        ev_prix.clear();
        ev_nombr.clear();
         btnajouter.setDisable(false);
        btnsupp.setDisable(false);
        btnmodif.setDisable(false);
        btnajouter.setDisable(false);
    }
    @FXML
    private void Delete(ActionEvent event) {
         ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        Dialog<ButtonType> dialog = new Dialog<>();
         dialog.setContentText("Voulez vous supprimé cet evenement !!!");
        dialog.getDialogPane().getButtonTypes().add(okButtonType);
        dialog.getDialogPane().getButtonTypes().add(cancelButtonType);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            if (table.getSelectionModel().getSelectedItem() != null) {
                JOptionPane.showMessageDialog(null, "Evenement Supprimee");
                cr.delete(table.getSelectionModel().getSelectedItem().getId());
                data.removeAll(data);
                for (evenement e : FXCollections.observableArrayList(cr.getAll())) {
                    data.add(e);
                }

            }
            clear();
        } else {
            System.out.println("Cancel");
        }
    }

    @FXML
    private void Modifier(ActionEvent event) {
         if (table.getSelectionModel().getSelectedItem() != null) {
            cr.update(new shahba.entity.evenement(ev_nom.getText(), ev_desc.getText(),Date.valueOf(ev_date.getValue()),ev_prix.getText(),Integer.valueOf(ev_nombr.getText()),ev_image.getText(),evt_adresse.getText(),Double.valueOf (txt_longitude.getText()),Double.valueOf (txt_latitude.getText())), table.getSelectionModel().getSelectedItem().getId());
            data.removeAll(data);
            for (evenement e : FXCollections.observableArrayList(cr.getAll())) {
                data.add(e);
            }
             updatetable ();
            clear();
            

        }
    }

    @FXML
    private void Clear(ActionEvent event) {
        clear();
       
    }
    
    @FXML
    private void AjouterPhoto(ActionEvent event) throws FileNotFoundException, IOException {
            FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pick a banner file!");
        fileChooser.setInitialDirectory(new File("C:/xampp/htdocs/me/DEV-master/Desktop/Integration/public/uploads"));
        Stage stage = new Stage();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
        File file = fileChooser.showOpenDialog(stage);
        try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                ev_image.setText(file.getName());
                imview.setImage(image);
            } catch (IOException ex) {
                System.out.println("could not get the image");
            }

    }
    
 public void load_pic(String links) {

        Image img = new Image(links);
        imview.setImage(img);

    }

    private void Back(ActionEvent event) throws IOException {
               Parent fxml = FXMLLoader.load(getClass().getResource("Home.fxml"));
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
  
    

