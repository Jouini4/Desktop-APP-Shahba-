/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package shahba.UI.front;

import shahba.entity.Reclamation;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author skander
 */
public class ItemAhmedController implements Initializable {

    @FXML
    private Label nomlab;
    private Label prixlab;
    @FXML
    private ImageView img;
   
    private MyListener myListener;
    @FXML
    private AnchorPane nh;
    private Reclamation reclamation;
    @FXML
    private Label labDescription;
    @FXML
    private Button consulterrec;
    @FXML
    private Label status;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        
        // TODO
    }
    public void setData(Reclamation reclamation) {
        
        this.reclamation =  reclamation;
        this.nomlab.setText(reclamation.getCreated_at());
        this.status.setText(reclamation.getEtrc());
        this.labDescription.setText(reclamation.getObrc());
        
        File file = new File(reclamation.getScreenshot().replace('/' , '\\'));
        System.out.println(file);
        
        Image im = null;
        if(file.exists()){ 
                 im = new Image(file.toURI().toString());
        }else{
            //im = new Image("resources/default-article.jpg"); // this is the defualt photo of the product
        }
         this.img.setImage(im);
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

   
    
 
      /*  FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("show.fxml"));
        Parent p = Loader.load();
        ShowController cont = Loader.getController();
        cont.setData(Astuce);
        

        AnchorPane pane = (AnchorPane) img.getParent().getParent().getParent();
        pane.getChildren().clear();
        pane.getChildren().setAll(p);*/


    @FXML
    private void clck(MouseEvent event) {
        myListener.onClickListener(reclamation , event);
        
    }

    @FXML
    private void selectitem(ActionEvent event) throws IOException {
        Reclamation rec = reclamation ;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Reclamationfront.fxml"));
        
        Parent root = loader.load();
        status.getScene().setRoot(root);
        
        ReclamationfrontController dpc = loader.getController();
        dpc.setreclamation(rec);
       
    }

  
}


   

