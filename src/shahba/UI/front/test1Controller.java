/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.UI.front;

import static Controller.ClientController1.cli;
import static Controller.SigninController.login;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import shahba.entity.Article;
import shahba.Service.ServiceArticle;



/**
 * FXML Controller class
 *
 * @author isslem
 */
public class test1Controller implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
     private Image image;
    //private List<Article> art = new ArrayList<>();
    private MyListener myListener;
    private Article prod;
    @FXML
    private AnchorPane displayArea;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ServiceArticle sp = new ServiceArticle();
        List<Article> l = new ArrayList<>();
        l.addAll(sp.afficher());
        l.forEach(System.out::println);
        System.out.println(sp.afficher());
        grid.getChildren().clear();
        int row = 1, cl =0;
            
                for(Article product : l){
              try {
                  FXMLLoader loader = new FXMLLoader();
                  loader.setLocation(getClass().getResource("item_1.fxml"));
                  Node postbox = loader.load();
                  itemController_1 pc = loader.getController();
                  
                  pc.setData(product);
                  if(cl== 3){
                      cl= 0;
                      row++;
                  }
                  this.grid.add(postbox, cl++, row);
//        int column = 0;
//        int row = 1;
//        try {
//            for (int i = 0; i < l.size(); i++) {
//                FXMLLoader fxmlLoader = new FXMLLoader();
//                fxmlLoader.setLocation(getClass().getResource("/com/spirity/gui.item.fxml"));
//                AnchorPane anchorPane = fxmlLoader.load();
//                itemController item = fxmlLoader.getController();
//                item.setData(l.get(i));
//                
//                if (column == 3) {
//                    column = 0;
//                    row++;
//                }
//
//                this.grid.add(anchorPane, column++, row); //(child,column,row)



//System.out.println("items " + l.toString());
//GridPane.setMargin(anchorPane, new Insets(10));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
        
//        int row = 1, cl =0;
//            try{
//                for(Article art : l){
//                    FXMLLoader loader = new FXMLLoader();
//                    loader.setLocation(getClass().getResource("/zerobug/gui/item.fxml"));
//                    Node postbox = loader.load();
//                    itemController pc = loader.getController();
//                    pc.setData(art, myListener, this.chosenArticleCard);
//                    if(cl== 3){
//                         cl= 0;
//                         row++;
//                    }
//                    this.grid.add(postbox, cl++, row);
//                }
//            }catch(IOException e){
//                System.out.println("no load for product in client");
//                   e.printStackTrace();
//            }



// TODO
              } catch (IOException ex) {
                  Logger.getLogger(test1Controller.class.getName()).log(Level.SEVERE, null, ex);
              }
    }    
    
}

    

    @FXML
    private void Produit(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("test1.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
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


    private Label nom;

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
    private void commande(ActionEvent event) throws IOException {
         Parent fxml = FXMLLoader.load(getClass().getResource("Commande.fxml"));
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







