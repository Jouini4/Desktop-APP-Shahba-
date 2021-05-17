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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import shahba.entity.Article;


/**
 * FXML Controller class
 *
 * @author skander
 */
public class itemController_1 implements Initializable {

    @FXML
    private Label nomlab;
    @FXML
    private Label prixlab;
    @FXML
    private ImageView img;
    private Article art;
    private MyListener myListener;
    @FXML
    private AnchorPane nh;
    @FXML
    private Button Description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void setData(Article art) {
       
        this.art = art;
        //this.myListener = myListener;       
        nomlab.setText(art.getNom_produit());
        prixlab.setText(""+art.getPrix());
        File file = new File(art.getImage());
        System.out.println(file);
        
        Image im = null;
        if(file.exists()){ 
                 im = new Image(file.getName());
        }else{
            //im = new Image("resources/default-article.jpg"); // this is the defualt photo of the product
        }
         this.img.setImage(im);
//        Image image = new Image(getClass().getResourceAsStream(art.getPhoto()
//        ));
//        img.setImage(image);
  //  getClass().getResourceAsStream
}

    @FXML
    private void clck(MouseEvent event) {
        //myListener.onClickListener(art);
    }

     private Stage primaryStage;
    @FXML
    private void Description(ActionEvent event) throws IOException {
            Stage window = primaryStage;
            Parent rootRec2 = FXMLLoader.load(getClass().getResource("PageArticle.fxml"));
            Scene rec2 = new Scene(rootRec2);
            Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app.setScene(rec2);
            app.show();
    }
}

