/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.UI.front;


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
import javafx.scene.Parent;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import shahba.Service.EvenementService;
import shahba.Service.ReservationService;
import shahba.entity.Maptet;
import shahba.entity.reservation;

/**
 * FXML Controller class
 *
 * @author HAMMOUDA
 */
public class EvenementControllerfront implements Initializable {

    private TextField ev_nom;
    private TextField ev_desc;
    private TextField ev_prix;
    private TextField ev_nombr;
    private Button btnajouter;
     
    
    EvenementService cr = new EvenementService();
    ReservationService crR = new ReservationService();
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
    private Button btnsupp;
    private Button btnmodif;
    @FXML
    private TextField recherche;
    @FXML
    private ImageView imgv5;
    private TextField ev_image;
    @FXML
    private TableColumn<evenement , String> event_image;
    private ImageView imview;
    @FXML
    private AnchorPane displayArea;
    @FXML
    private Button btnRATE;
    @FXML
    private TableColumn<evenement, String> ev_adresse;

    @FXML
    private Button btnBack;
    @FXML
    private TextField r_nbrplace;
    @FXML
    private TextField r_utilisateur;
    private TextField r_eventname;
    @FXML
    private Button btnmap;
    @FXML
    private TextField evt_adresse;
    @FXML
    private TableColumn<evenement, Double> tableau_longitude;
    @FXML
    private TableColumn<evenement, Double> tableau_latitude;
    private TextField txt_longitude;
    private TextField txt_latitude;
   
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         // TODO
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

        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (table.getSelectionModel().getSelectedItem() != null) {
                    shahba.entity.evenement e = (shahba.entity.evenement) table.getSelectionModel().getSelectedItem();
                     System.out.println();
                    
              txt_longitude.setText(Double.toString (e.getLongitude()));
                      txt_latitude.setText(Double.toString (e.getLatitude()));
             
                   
                    
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

  

    private void clear() {
        table.getSelectionModel().clearSelection();
        ev_nom.clear();
        ev_desc.clear();
        ev_prix.clear();
        ev_nombr.clear();
         btnajouter.setDisable(false);
        btnsupp.setDisable(false);
        btnmodif.setDisable(false);
        btnajouter.setDisable(false);
        
    }
   

    private void Clear(ActionEvent event) {
        clear();
       
    }
    
    private void AjouterPhoto(ActionEvent event) throws FileNotFoundException, IOException {
        

        FileChooser fileChooser = new FileChooser();
       

        fileChooser.setTitle("Open Resource File");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif" , "*.mp4"));
        File selectedFile = fileChooser.showOpenDialog(null);
        
        if (selectedFile != null) {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            
            //this.image.setText(selectedFile.getName());
            this.ev_image.setText(selectedFile.toURI().toURL().toString());
            imview.setImage(image);
        }

    }
    
 public void load_pic(String links) {

        Image img = new Image(links);
        imview.setImage(img);

    }

    @FXML
    private void RATE(ActionEvent event) throws IOException {
        Parent fxml;
        fxml = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }


   @FXML
    private void SeeLocation(ActionEvent event) {
           
int index = table.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }

 // evt_adresse.setText(ev_adresse.getCellData(index).toString());
          txt_longitude.setText(tableau_longitude.getCellData(index).toString());
          txt_latitude.setText(tableau_latitude.getCellData(index).toString());
           //String S = evt_adresse.getText();
           //String[] splitString = S.split(",");
                 // tableau_longitude.getCellData(index);
                //tableau_latitude.getCellData(index);
                Double d = Double.valueOf (txt_longitude.getText());
                Double d1 = Double.valueOf (txt_latitude.getText());
              //  Double d = Double.parseDouble(splitString[0]);
               // Double d1 = Double.parseDouble(splitString[1]);
                Maptet test = new Maptet(d, d1);
    }
  @FXML
    private void Back(ActionEvent event) throws IOException {
               Parent fxml = FXMLLoader.load(getClass().getResource("Home.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }

    @FXML
    private void AjoutReservation(ActionEvent event) {
        
          shahba.entity.evenement e = (shahba.entity.evenement) table.getSelectionModel().getSelectedItem();
          
        
               
                  reservation r = new shahba.entity.reservation(Integer.valueOf(r_nbrplace.getText()),false,Integer.valueOf(r_utilisateur.getText()));
       crR.createReservation(r, e.getId(), Integer.valueOf(r_nbrplace.getText()),Integer.valueOf(r_utilisateur.getText()));
              
           
   
        

        }
        
        

        
    }
           
  
  
    

