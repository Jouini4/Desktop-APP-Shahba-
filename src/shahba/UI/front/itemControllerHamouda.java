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
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import shahba.Service.ReservationService;
import shahba.entity.Maptet;
import shahba.entity.evenement;
import shahba.entity.reservation;




/**
 * FXML Controller class
 *
 * @author skander
 */
public class itemControllerHamouda implements Initializable {
   ReservationService crR = new ReservationService();
    @FXML
    private Label nomlab;
    private Label prixlab;
    @FXML
    private ImageView img;
    private evenement evenement;
    private MyListener myListener;
    @FXML
    private AnchorPane nh;
     @FXML
    private AnchorPane displayArea;
    @FXML
    private Label Prix;
    @FXML
    private Label Description;
    @FXML
    private Label Adresse;
    @FXML
    private Label Place;
    @FXML
    private Label Date;
    @FXML
    private TextField r_nbrplace;
    @FXML
    private TextField r_utilisateur;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void setData(evenement evenement) {
       
        this.evenement  = evenement;
        this.nomlab.setText(evenement.getNom_event());
        this.Prix.setText(evenement.getPrix_event());
        this.Description.setText(evenement.getDescription_event());
        this.Adresse.setText(evenement.getAdresse());
        this.Place.setText(Integer.toString(evenement.getNbr_place()));
       // this.Date.setText(evenement.getDate());
        File file = new File(evenement.getImage().replace('/' , '\\'));
        System.out.println(file);
        
        Image im = null;
        if(file.exists()){ 
                 im = new Image(file.getName());
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

    @FXML
    private void clck(MouseEvent event) {
        //myListener.onClickListener(art);
    }

    @FXML
    private void SeeLocation(ActionEvent event) {
              Double d = evenement.getLongitude();
                Double d1 = evenement.getLatitude();
              
                Maptet test = new Maptet(d, d1);
    }

    @FXML
    private void AjoutReservation(ActionEvent event) throws IOException {
        
        
          
        
               
                  reservation r = new shahba.entity.reservation(Integer.valueOf(r_nbrplace.getText()),false,Integer.valueOf(r_utilisateur.getText()));
       crR.createReservation(r, evenement.getId(), Integer.valueOf(r_nbrplace.getText()),Integer.valueOf(r_utilisateur.getText()));
              
          
      
        
    }


}

   

