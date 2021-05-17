/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.UI.front;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import shahba.entity.Astuce;

/**
 * FXML Controller class
 *
 * @author isslem
 */
public class ShowController implements Initializable {

    @FXML
    private Label nomlab;
    @FXML
    private ImageView img;
    @FXML
    private Label labDescription;
    private Astuce astuce;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
     public void setData(Astuce astuce) {
       
        this.astuce = astuce;
        this.nomlab.setText(astuce.getTitre());
        this.labDescription.setText(astuce.getDescription());
        File file = new File(astuce.getImage().replace('/' , '\\'));
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

    @FXML
    private void clck(MouseEvent event) {
        //myListener.onClickListener(art);
    }

  
}
