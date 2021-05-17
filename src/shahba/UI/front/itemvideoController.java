/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package shahba.UI.front;

import java.io.File;
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
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import shahba.entity.Video;


/**
 * FXML Controller class
 *
 * @author skander
 */
public class itemvideoController implements Initializable {

    @FXML
    private Label nomlab;
    private Label prixlab;
    private ImageView img;
   
    private MyListener myListener;
    @FXML
    private AnchorPane nh;
    private Video video;
    @FXML
    private Label labDescription;
    @FXML
    private StackPane pane;
    @FXML
    private MediaView mediaView;
    @FXML
    private Slider progressBar;
    @FXML
    private Slider volumeSlider;
    private Media media ; 
private MediaPlayer mediaPlayer ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }
    public void setData(Video video) {
       
        this.video = video;
        this.nomlab.setText(video.getTitre());
        this.labDescription.setText(video.getDescription());
        File file = new File(video.getUrl().replace('/' , '\\'));
        System.out.println(file);
        
        Media media = null;
        if(file.exists()){ 
                 media = new Media(file.toURI().toString());
                 mediaPlayer = new MediaPlayer(media);
                 mediaView.setMediaPlayer(mediaPlayer);
                // mediaPlayer.setAutoPlay(true);
        }else{
            //im = new Image("resources/default-article.jpg"); // this is the defualt photo of the product
       }
      //   this.img.setImage(im);
        //this.myListener = myListener;       
        //nomlab.setText(astuce.getTitre());
        //Image image = new Image(getClass().getResourceAsStream(Astuce.getImage()));
        //img.setImage(image);
  //  getClass().getResourceAsStream
        }

   // @FXML
  //  private void clck(MouseEvent event) {
       // myListener.onClickListener();
  //  }

    private void show(ActionEvent event) throws IOException {
             Parent fxml = FXMLLoader.load(getClass().getResource("show.fxml"));
       nh.getChildren().clear();
        nh.getChildren().add(fxml);
    
 
      /*  FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("show.fxml"));
        Parent p = Loader.load();
        ShowController cont = Loader.getController();
        cont.setData(Astuce);
        

        AnchorPane pane = (AnchorPane) img.getParent().getParent().getParent();
        pane.getChildren().clear();
        pane.getChildren().setAll(p);*/
}

    @FXML
    private void clck(MouseEvent event) {
        myListener.onClickListener(video , event);
    }

    private void avis(ActionEvent event) throws IOException {
         /*Parent fxml = FXMLLoader.load(getClass().getResource("FXML.fxml"));
       nh.getChildren().clear();
        nh.getChildren().add(fxml);
*/
          Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        //stage.setMaximized(true);
        stage.close();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("FXML.fxml")));
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void playVideo(ActionEvent event) {
            mediaPlayer.play();
        mediaPlayer.setRate(1);
    }

    @FXML
    private void pauseVideo(ActionEvent event) {
         mediaPlayer.pause();
    }

    @FXML
    private void stopVideo(ActionEvent event) {
        mediaPlayer.stop();
    }
    

    @FXML
    private void furtherSlowDownVideo(ActionEvent event) {
         mediaPlayer.setRate(0.5);
    }

    @FXML
    private void back5(ActionEvent event) {
         mediaPlayer.seek(mediaPlayer.getCurrentTime().add(javafx.util.Duration.seconds(-5)));
    }

    @FXML
    private void skip5(ActionEvent event) {
        mediaPlayer.seek(mediaPlayer.getCurrentTime().add(javafx.util.Duration.seconds(5)));
    }

    @FXML
    private void furtherSpeedUpVideo(ActionEvent event) {
         mediaPlayer.setRate(2);
    }

}


   

