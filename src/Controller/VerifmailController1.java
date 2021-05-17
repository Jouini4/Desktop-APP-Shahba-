/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.ClientController1.cli;
import static Controller.ClientController1.scirc;
import static Controller.ClientController1.snom;
import static Controller.ClientController1.sport;
import static Controller.ClientController1.spren;
import static Controller.MainController.svbox;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.util.Duration;
import static Controller.SignupController.code;
import static Controller.SignupController.User1;
import static Controller.forgetpwdController.c;
import entity.User;
import entity.Userdao;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import static Controller.ProfclientController.cli1;
import static Controller.ProfclientController.code;
import static Controller.ProfclientController.svbox;
import Utils.Connexion;
import java.sql.Statement;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

/**
 * FXML Controller class
 *
 * @author user
 */
public class VerifmailController1 extends ProfclientController implements Initializable {

    @FXML
    private JFXButton verify;
    @FXML
    private JFXTextField code1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     private void executeQuery(String query) {
         connection=Connexion.getInstance().getCnx();
      try {
          Statement st = connection.createStatement();
          st.executeUpdate(query);
  
      }catch (Exception ex){
          ex.printStackTrace();   
      }
      
     
    }

    @FXML
    private void getpass(ActionEvent event) {
        if(code.equals(code1.getText())){
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Userdao udao = Userdao.getInstance();
            String query = "UPDATE user SET  username = '" + cli1.getUsername()+
             "', email = '"+cli1.getEmail()+ 
             "', password = '"+cli1.getPassword()+
             "', userphone = '"+cli1.getUserphone()+
             "', usercin = '"+cli1.getUsercin()+
             "', useraddress = '"+cli.getUseraddress()+
             "' WHERE id = '" + cli.getId()
             +
             "'";
            executeQuery(query);
             JOptionPane.showMessageDialog(null, "Modification termine");
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
             Stage stage1 = (Stage) svbox.getScene().getWindow();
             stage1.hide();
             snom.setText(cli1.getUsername());
        }else{
            JOptionPane.showMessageDialog(null, "Invalid Code !!");
            code1.clear();
        }
    }
    
}
