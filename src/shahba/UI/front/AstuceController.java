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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import shahba.Service.ServiceAstuce;
import shahba.entity.Astuce;

/**
 * FXML Controller class
 *
 * @author selmi
 */
public class AstuceController implements Initializable {

    @FXML
    private AnchorPane displayArea;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
          ServiceAstuce sp = new ServiceAstuce();
        List<Astuce> l = new ArrayList<>();
       l.addAll(sp.getAll());
        l.forEach(System.out::println);
        
        /*if (l.size() > 0) {
            setChosenProduct(l.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Article art) {
                    setChosenProduct(art); //To change body of generated methods, choose Tools | Templates.
                }
            };
        }*/
        grid.getChildren().clear();
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < l.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                itemController item = fxmlLoader.getController();
                item.setData(l.get(i));
                
              
                
                if (column == 1) {
                    column = 0;
                    row++;
                }

                this.grid.add(anchorPane, column++, row); //(child,column,row)
                
            
        
        //System.out.println("items " + l.toString());
        //GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        
//        int row = 1, cl =0;
//            try{
//                for(Article art : l){
//                    FXMLLoader loader = new FXMLLoader();
//                    loader.setLocation(getClass().getResource("/zerobug/gui/item.fxml"));
//                    Node postbox = loader.load();
//                    ItemController pc = loader.getController();
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
    
    
    
        
}
    
    private void Avis(ActionEvent event) throws IOException {
            Parent fxml = FXMLLoader.load(getClass().getResource("FXML.fxml"));
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
    private void Home(ActionEvent event) throws IOException {
                Parent fxml = FXMLLoader.load(getClass().getResource("Home.fxml"));
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
    private void Produit(ActionEvent event) throws IOException {
                  Parent fxml = FXMLLoader.load(getClass().getResource("test1.fxml"));
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