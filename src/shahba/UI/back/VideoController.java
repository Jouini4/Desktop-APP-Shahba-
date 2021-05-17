/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.UI.back;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javax.swing.JOptionPane;
import shahba.Service.ServiceVideo;
import shahba.entity.Video;

/**
 * FXML Controller class
 *
 * @author isslem
 */
public class VideoController implements Initializable {

    @FXML
    private TableColumn<Video , String> video_name;
    @FXML
    private TableColumn<Video , String> video_description;
    
    @FXML
    private TextField txttitre;
    @FXML
    private TextField txtdescription;
    
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnclear;
    @FXML
    private Button btnsupp;
    
     ServiceVideo as = new ServiceVideo();
    ObservableList<Video> data = FXCollections.observableArrayList(as.getAll());;
    
    @FXML
    private TextField txtvideo;
    @FXML
    private TableView<Video> table;
    @FXML
    private TextField recherche;
    @FXML
    private TableColumn<Video , String> video;
    @FXML
    private Button file;
    @FXML
    private AnchorPane displayArea;
    @FXML
    private MediaView mediaView;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button Astuce;
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
    private Slider progressBar;
    private Slider volumeSlider;
    private MediaPlayer mediaPlayer;
    @FXML
    private Button Video;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         video_name.setCellValueFactory(new PropertyValueFactory("titre"));
         video_description.setCellValueFactory(new PropertyValueFactory("description"));
         video.setCellValueFactory(new PropertyValueFactory("url"));
         table.setItems(data);

      table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
           
             @Override
             public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                 if (table.getSelectionModel().getSelectedItem() != null) {
                    shahba.entity.Video v = (shahba.entity.Video) table.getSelectionModel().getSelectedItem();
                     System.out.println();
                      txttitre.setText(v.getTitre());
                      txtdescription.setText(v.getDescription());
                      txtvideo.setText(v.getUrl());
                     
                 
                    btnajouter.setDisable(true);
                              
                }
             }


        }        ); 
             
        
       recherche.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue ) {
               filtrerVideoList((String) oldValue, (String) newValue );
            }
           

        });
    }     

   void filtrerVideoList(String oldValue, String newValue) {
        ServiceVideo evs = new ServiceVideo();
        ObservableList<Video> filteredList = FXCollections.observableArrayList();
        if (recherche.getText() == null || newValue == null) {
            table.setItems((ObservableList<Video>) evs.FindVideo());
        } else {
            table.setItems((ObservableList<Video>) evs.FindVideo());
            newValue = newValue.toUpperCase();

            for (Video v : table.getItems()) {

                String filterVideoName = v.getTitre();
               

                if (filterVideoName.toUpperCase().contains(newValue)) {
                    filteredList.add(v);
                }
            }
            table.setItems(filteredList);
        }
    }
 @FXML
    private void Ajout(ActionEvent event) {
   
           if (txttitre.getText() == null || txttitre.getText().trim().isEmpty()) {
             Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir les champs s'il vous plait!");
            dialogW.showAndWait();
            } 
           else if (txtdescription.getText() == null || txtdescription.getText().trim().isEmpty()) {
             Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir les champs s'il vous plait!");
            dialogW.showAndWait();
            } 
            else if (txtvideo.getText() == null || txtvideo.getText().trim().isEmpty()) {
             Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir les champs s'il vous plait!");
            dialogW.showAndWait();
            } 
            
             else {
      Video v = new shahba.entity.Video(txttitre.getText(), txtdescription.getText(),txtvideo.getText());
       as.createVideo(v);
       }
     data.removeAll(data);
         for (Video ev : FXCollections.observableArrayList(as.getAll())) {
            data.add(ev);

        }
         clear();   
    }

    private void clear() {
        table.getSelectionModel().clearSelection();
       txttitre.clear();
        txtdescription.clear();
        txtvideo.clear();
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
         dialog.setContentText("Voulez vous supprimé cette video !!!");
        dialog.getDialogPane().getButtonTypes().add(okButtonType);
        dialog.getDialogPane().getButtonTypes().add(cancelButtonType);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            if (table.getSelectionModel().getSelectedItem() != null) {
                JOptionPane.showMessageDialog(null, "Video Supprimee");
                as.delete(table.getSelectionModel().getSelectedItem().getId());
                data.removeAll(data);
                for (Video v : FXCollections.observableArrayList(as.getAll())) {
                    data.add(v);
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
            as.update(new shahba.entity.Video(txttitre.getText(), txtdescription.getText(),txtvideo.getText()), table.getSelectionModel().getSelectedItem().getId());
            data.removeAll(data);
            for (Video v : FXCollections.observableArrayList(as.getAll())) {
                data.add(v);
            }
            clear();
            

        }
    }

    @FXML
    private void Clear(ActionEvent event) {
        clear();
       
    }

   @FXML
    private void file(ActionEvent event) throws IOException {
     /*     FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pick a banner file!");
        fileChooser.setInitialDirectory(new File("\\"));
        Stage stage = new Stage();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("mp4", "*.MP4")
            );
        File file = fileChooser.showOpenDialog(stage);
        try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                txtvideo.setText(file.getAbsolutePath());
                mediaView.setMediaPlayer(image);
            } catch (IOException ex) {
                System.out.println("could not get the image");
            }
*/
      FileChooser chooser = new FileChooser();
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("select your media(*.mp4)", "*.mp4");
            chooser.getExtensionFilters().add(filter);
        Window primaryStage = null;
            File file = chooser.showOpenDialog(primaryStage);
            if ( file !=null){
                 txtvideo.setText(file.getAbsolutePath());
                 
          
              Media source = new Media(file.toURI().toString());
          Media media = null;
              mediaPlayer = new MediaPlayer (media);
mediaView.setMediaPlayer(mediaPlayer);
mediaPlayer.setAutoPlay(true);
            //    MediaPlayer player = new MediaPlayer(source);
            //    MediaView view = new MediaView(player);
            //    MediaView.getClassCssMetaData();
            //    displayArea.getChildren().add(view);
               // player.play();


            }
             else {
                txtvideo.setText("vide ");
            }
    }
    

    private void back(ActionEvent event) throws IOException {
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

    private void OpenFileMethod(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
//        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select a .mp4 file", ".mp4");
//        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(null);
        String path = file.toURI().toString();

        if(path != null){
            Media media = new Media(path);
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            
            //creating bindings
            DoubleProperty widthProp = mediaView.fitWidthProperty();
            DoubleProperty heightProp = mediaView.fitHeightProperty();
            
            widthProp.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
            heightProp.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
            
            volumeSlider.setValue(mediaPlayer.getVolume()*100);
            volumeSlider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(volumeSlider.getValue()/100);
                }

              
            });
            
            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<javafx.util.Duration>() {
                @Override
                public void changed(ObservableValue<? extends javafx.util.Duration> observable, javafx.util.Duration oldValue, javafx.util.Duration newValue) {
                    progressBar.setValue(newValue.toSeconds());
                }
            }
            );
            
            progressBar.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(javafx.util.Duration.seconds(progressBar.getValue()));
                }
            });
            
            progressBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(javafx.util.Duration.seconds(progressBar.getValue()));
                }
            });
            
            mediaPlayer.setOnReady(new Runnable() {
                @Override
                public void run() {
                    javafx.util.Duration total = media.getDuration();
                    progressBar.setMax(total.toSeconds());
                }
            });
            
            mediaPlayer.play();
        }
    }
    
   /* @Override
    public void initialize(URL url, ResourceBundle rb) {
/*String path = new File("Source Packagaes/img/recette-hydratant-a-l-aloe-vera-et-noix-de-coco-auybec9z_fwsRiFWO.mp4").getAbsolutePath();
media = new Media ( new File(path).toURI().toString());
mediaPlayer = new MediaPlayer (media);
mediaView.setMediaPlayer(mediaPlayer);
mediaPlayer.setAutoPlay(true);


   }    
*/
    
    public void pauseVideo(ActionEvent event){
        mediaPlayer.pause();
    }
    
    public void stopVideo(ActionEvent event){
        mediaPlayer.stop();
    }
    
    public void playVideo(ActionEvent event){
        mediaPlayer.play();
        mediaPlayer.setRate(1);
    }
    
    public void skip5(ActionEvent event){
        mediaPlayer.seek(mediaPlayer.getCurrentTime().add(javafx.util.Duration.seconds(5)));
    }
    
    public void furtherSpeedUpVideo(ActionEvent event){
        mediaPlayer.setRate(2);
    }
    
    public void back5(ActionEvent event){
        mediaPlayer.seek(mediaPlayer.getCurrentTime().add(javafx.util.Duration.seconds(-5)));
    }
    
    public void furtherSlowDownVideo(ActionEvent event){
        mediaPlayer.setRate(0.5);

    }

    private void Home(ActionEvent event) throws IOException {

               Parent fxml = FXMLLoader.load(getClass().getResource("Homeback.fxml"));
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

  
        
    


