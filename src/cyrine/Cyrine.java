/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cyrine;

import entity.User;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author user
 */
public class Cyrine extends Application {
    
    private Stage primaryStage;
   // public static User connectedUser ;
    
    
    private Parent parentPage;
    @Override
    public void start(Stage primaryStage) throws IOException {
       this.primaryStage = primaryStage;
       // this.primaryStage.setTitle("Acceuil");
        //this.primaryStage.initStyle(StageStyle.UNDECORATED);
        parentPage = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
        Scene scene = new Scene(parentPage);
        scene.setFill(Color.TRANSPARENT);
        this.primaryStage.setScene(scene);
        this.primaryStage.initStyle(StageStyle.TRANSPARENT);
        this.primaryStage.show();
        /**
         Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));        
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
         **/
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
