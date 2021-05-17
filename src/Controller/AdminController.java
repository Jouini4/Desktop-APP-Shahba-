/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.SigninController.login;
import Utils.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import entity.User;
import entity.Userdao;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Connection;
import Utils.Connexion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;





/**
 * FXML Controller class
 *
 * @author user
 */
public class AdminController implements Initializable {

    @FXML
    private TableView<User> tableuser;
    @FXML
    private TableColumn<User, String> colid;
    @FXML
    private TableColumn<User, String> colnom;
    private TableColumn<User, String> colprenom;
    @FXML
    private TableColumn<User, String> colemail;
    @FXML
    private TableColumn<User, String> colemdp;
    private TableColumn<User, String> coldate;
    @FXML
    private TableColumn<User, String> colphoto;
    @FXML
    private TableColumn<User, String> coltele;

     ObservableList<User>  codeList = FXCollections.observableArrayList();
     ObservableList<User>  codeList1 = FXCollections.observableArrayList();
     
    private ListData listdata = new ListData();
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    User Code=null ;
    private TableColumn<User, String> colportf;
    private TableColumn<User, String> colfidal;
    private TableColumn<User, String> colrole;
    @FXML
    private TableColumn<User, String> coletat;
    private TableColumn<User, String> coldate2;
    @FXML
    private TextField text_id;
    @FXML
    private TextField text_nom;
    private TextField text_prenom;
    @FXML
    private TextField text_email;
    @FXML
    private TextField text_mdp;
    private DatePicker text_date;
    @FXML
    private TextField text_tel;
    private TextField text_port;
    private TextField text_fidal;
    @FXML
    private TextField text_role;
    @FXML
    private TextField text_etat;
    @FXML
    private Button btn_modify;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_delete;
    private DatePicker text_date2;
     @FXML
    private AnchorPane displayArea;
    public int y;
    public int m;
    public int d;
    public int y1;
    public int m1;
    public int d1;
    
    @FXML
    private JFXTextField rech;
    @FXML
    private ImageView Ref2;
    private ImageView img;
    private Label textimage;
    @FXML
    private Label X;
    @FXML
    private TableView<User> tableuser1;
    @FXML
    private TableColumn<User, String> colid1;
    @FXML
    private TableColumn<User, String> colemail1;
    @FXML
    private TableColumn<User, String> colemdp1;
    @FXML
    private TableColumn<User, String> colnom1;
    @FXML
    private TableColumn<User, String> coltele1;
    @FXML
    private TableColumn<User, String> colphoto1;
    @FXML
    private TableColumn<User, String> coletat1;
    @FXML
    private JFXTextField rech1;
    @FXML
    private ComboBox<String> role;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button Astuce;
    @FXML
    private Button Video;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOverview1;
    @FXML
    private Button btnOverview11;
    @FXML
    private Button btnSignout;
    @FXML
    private Button btnOverview12;
     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        connection=Connexion.getInstance().getCnx();
        codeList= FXCollections.observableArrayList();
        codeList1= FXCollections.observableArrayList();
        loadData();
        loadData3();
        role.getItems().addAll(
        "Admin",
        "Client"
    );
    }    
        
    private void afficher() {
        //User user = new User();
       // Userdao crud = new Userdao();
        try {
            codeList.clear();
            
            query = "SELECT `id`,`username`,`email`,`password`,`userphone`,`usercin`,`useraddress` FROM `client`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                codeList.add(new  User(
                        
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        BCrypt.hashpw(resultSet.getString("password"), BCrypt.gensalt(12)),  
                        resultSet.getInt("userphone"),
                        resultSet.getInt("usercin"),
                        resultSet.getString("useraddress")));
                
                        tableuser.setItems(codeList);
                
               
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tableuser.setOnMouseClicked(event->{
             User user = new User();
             Userdao crud = new Userdao();
           
            int id;
            id = codeList.get(tableuser.getSelectionModel().getSelectedIndex()).getId();
            text_id.setText(String.valueOf(id));
            user = crud.UserById(id);
            text_nom.setText(user.getUsername());
        text_nom.setText(String.valueOf(codeList
                .get(tableuser.getSelectionModel().getSelectedIndex()).getUsername()));
        text_email.setText(String.valueOf(codeList
                .get(tableuser.getSelectionModel().getSelectedIndex()).getEmail()));
        text_mdp.setText(user.getPassword());
        text_tel.setText(String.valueOf(codeList
                .get(tableuser.getSelectionModel().getSelectedIndex()).getUserphone()));
        text_role.setText(String.valueOf(codeList
                .get(tableuser.getSelectionModel().getSelectedIndex()).getUseraddress()));
        text_etat.setText(String.valueOf(codeList
                .get(tableuser.getSelectionModel().getSelectedIndex()).getUsercin()));
        role.setValue("Client");
            }
        );
    }
    private void afficher3() {
        //User user = new User();
       // Userdao crud = new Userdao();
        try {
            codeList1.clear();
            
            query = "SELECT `id`,`username`,`email`,`password`,`userphone`,`usercin`,`useraddress` FROM `admin`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                codeList1.add(new  User(
                        
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        BCrypt.hashpw(resultSet.getString("password"), BCrypt.gensalt(12)),  
                        resultSet.getInt("userphone"),
                        resultSet.getInt("usercin"),
                        resultSet.getString("useraddress")));
                
                        tableuser1.setItems(codeList1);
                
               
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tableuser1.setOnMouseClicked(event->{
             User user = new User();
             Userdao crud = new Userdao();
           
            int id;
            id = codeList1.get(tableuser1.getSelectionModel().getSelectedIndex()).getId();
            text_id.setText(String.valueOf(id));
            user = crud.UserById1(id);
            text_nom.setText(user.getUsername());
        text_nom.setText(String.valueOf(codeList1
                .get(tableuser1.getSelectionModel().getSelectedIndex()).getUsername()));
        text_email.setText(String.valueOf(codeList1
                .get(tableuser1.getSelectionModel().getSelectedIndex()).getEmail()));
        text_mdp.setText(user.getPassword());
        text_tel.setText(String.valueOf(codeList1
                .get(tableuser1.getSelectionModel().getSelectedIndex()).getUserphone()));
        text_role.setText(String.valueOf(codeList1
                .get(tableuser1.getSelectionModel().getSelectedIndex()).getUseraddress()));
        text_etat.setText(String.valueOf(codeList1
                .get(tableuser1.getSelectionModel().getSelectedIndex()).getUsercin()));
         role.setValue("Admin");
            }
        );
    }
        private void afficher1(String id) {
        try {
            codeList.clear();
            
            query = "SELECT `id`,`username`,`email`,`password`,`userphone`,`usercin`,`useraddress` FROM `client` WHERE `username` LIKE '%"+id+"%' OR `email` LIKE '%"+id+"%'";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                codeList.add(new  User(
                        
                         resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        BCrypt.hashpw(resultSet.getString("password"), BCrypt.gensalt(12)),  
                        resultSet.getInt("userphone"),
                        resultSet.getInt("usercin"),
                        resultSet.getString("useraddress")));
                
                        tableuser.setItems(codeList);
                
               
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tableuser.setOnMouseClicked(event->{
            User user = new User();
            Userdao crud = new Userdao();
            int id1;
            id1 = codeList.get(tableuser.getSelectionModel().getSelectedIndex()).getId();
            text_id.setText(String.valueOf(id1));
            user = crud.UserById(id1);
            text_nom.setText(user.getUsername());
        text_nom.setText(String.valueOf(codeList
                .get(tableuser.getSelectionModel().getSelectedIndex()).getUsername()));
        text_email.setText(String.valueOf(codeList
                .get(tableuser.getSelectionModel().getSelectedIndex()).getEmail()));
        text_mdp.setText(user.getPassword());
        text_tel.setText(String.valueOf(codeList
                .get(tableuser.getSelectionModel().getSelectedIndex()).getUserphone()));
        text_role.setText(String.valueOf(codeList
                .get(tableuser.getSelectionModel().getSelectedIndex()).getUseraddress()));
        text_etat.setText(String.valueOf(codeList
                .get(tableuser.getSelectionModel().getSelectedIndex()).getUsercin()));
         role.setValue("Client");
        
            }
        );
    }
         private void afficher4(String id) {
        try {
            codeList1.clear();
            
            query = "SELECT `id`,`username`,`email`,`password`,`userphone`,`usercin`,`useraddress` FROM `admin` WHERE `username` LIKE '%"+id+"%' OR `email` LIKE '%"+id+"%'";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                codeList1.add(new  User(
                        
                         resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        BCrypt.hashpw(resultSet.getString("password"), BCrypt.gensalt(12)),  
                        resultSet.getInt("userphone"),
                        resultSet.getInt("usercin"),
                        resultSet.getString("useraddress")));
                
                        tableuser1.setItems(codeList1);
                
               
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tableuser1.setOnMouseClicked(event->{
            User user = new User();
            Userdao crud = new Userdao();
            int id1;
            id1 = codeList1.get(tableuser1.getSelectionModel().getSelectedIndex()).getId();
            text_id.setText(String.valueOf(id1));
            user = crud.UserById(id1);
            text_nom.setText(user.getUsername());
        text_nom.setText(String.valueOf(codeList1
                .get(tableuser1.getSelectionModel().getSelectedIndex()).getUsername()));
        text_email.setText(String.valueOf(codeList1
                .get(tableuser1.getSelectionModel().getSelectedIndex()).getEmail()));
        text_mdp.setText(user.getPassword());
        text_tel.setText(String.valueOf(codeList1
                .get(tableuser1.getSelectionModel().getSelectedIndex()).getUserphone()));
        text_role.setText(String.valueOf(codeList1
                .get(tableuser1.getSelectionModel().getSelectedIndex()).getUseraddress()));
        text_etat.setText(String.valueOf(codeList1
                .get(tableuser1.getSelectionModel().getSelectedIndex()).getUsercin()));
         role.setValue("Admin");
        
            }
        );
    }

    private void loadData() {
          afficher();
         
       colid.setCellValueFactory(new PropertyValueFactory<>("id"));
       colnom.setCellValueFactory(new PropertyValueFactory<>("username"));
       colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
       colemdp.setCellValueFactory(new PropertyValueFactory<>("password"));
       colphoto.setCellValueFactory(new PropertyValueFactory<>("useraddress"));
       coltele.setCellValueFactory(new PropertyValueFactory<>("userphone"));
       coletat.setCellValueFactory(new PropertyValueFactory<>("usercin"));
       
    }
     private void loadData3() {
          afficher3();
         
       colid1.setCellValueFactory(new PropertyValueFactory<>("id"));
       colnom1.setCellValueFactory(new PropertyValueFactory<>("username"));
       colemail1.setCellValueFactory(new PropertyValueFactory<>("email"));
       colemdp1.setCellValueFactory(new PropertyValueFactory<>("password"));
       colphoto1.setCellValueFactory(new PropertyValueFactory<>("useraddress"));
       coltele1.setCellValueFactory(new PropertyValueFactory<>("userphone"));
       coletat1.setCellValueFactory(new PropertyValueFactory<>("usercin"));
       
    }
     private void loadData1(String ii) {
         afficher1(ii);
         
       colid.setCellValueFactory(new PropertyValueFactory<>("id"));
       colnom.setCellValueFactory(new PropertyValueFactory<>("username"));
       colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
       colemdp.setCellValueFactory(new PropertyValueFactory<>("password"));
       colphoto.setCellValueFactory(new PropertyValueFactory<>("useraddress"));
       coltele.setCellValueFactory(new PropertyValueFactory<>("userphone"));
       coletat.setCellValueFactory(new PropertyValueFactory<>("usercin"));
       
    }
      private void loadData4(String ii) {
         afficher4(ii);
         
       colid1.setCellValueFactory(new PropertyValueFactory<>("id"));
       colnom1.setCellValueFactory(new PropertyValueFactory<>("username"));
       colemail1.setCellValueFactory(new PropertyValueFactory<>("email"));
       colemdp1.setCellValueFactory(new PropertyValueFactory<>("password"));
       colphoto1.setCellValueFactory(new PropertyValueFactory<>("useraddress"));
       coltele1.setCellValueFactory(new PropertyValueFactory<>("userphone"));
       coletat1.setCellValueFactory(new PropertyValueFactory<>("usercin"));
       
    }

    @FXML
    private void addclient(ActionEvent event) {
         if (role.getValue().equalsIgnoreCase("Client")){
       
           User u = new User(text_nom.getText(),text_email.getText(),text_mdp.getText(),Integer.parseInt(text_tel.getText()),Integer.parseInt(text_etat.getText()),text_role.getText(),"[]");
            Userdao udao = Userdao.getInstance();
            udao.insert(u);
         
        JOptionPane.showMessageDialog(null, "ajout termine");
        text_id.clear();
        text_nom.clear();
        text_email.clear();
        text_mdp.clear();
        text_tel.clear();
        text_role.clear();
        text_etat.clear();
        role.getSelectionModel().clearSelection();
        afficher();
         
    }else{
              User u = new User(text_nom.getText(),text_email.getText(),text_mdp.getText(),Integer.parseInt(text_tel.getText()),Integer.parseInt(text_etat.getText()),text_role.getText(),"[]");
            Userdao udao = Userdao.getInstance();
            udao.insert3(u);
         
        JOptionPane.showMessageDialog(null, "ajout termine");
        text_id.clear();
        text_nom.clear();
        text_email.clear();
        text_mdp.clear();
        text_tel.clear();
        text_role.clear();
        text_etat.clear();
        role.getSelectionModel().clearSelection();
        afficher3();
         }
    }

    @FXML
    private void modifyclient(ActionEvent event) {
        if (role.getValue().equalsIgnoreCase("Client")){
         String query = "UPDATE client SET  username = '" + text_nom.getText()+
             "', email = '"+text_email.getText()+ 
             "', password = '"+text_mdp.getText()+
             "', userphone = '"+text_tel.getText()+ 
             "', useraddress = '"+text_role.getText()+
             "', usercin = '"+Integer.parseInt(text_etat.getText())+
             "' WHERE id = '" + Integer.parseInt(text_id.getText())
             +
             "'";
       executeQuery(query);
             JOptionPane.showMessageDialog(null, "Modification termine");
        text_id.clear();
        text_nom.clear();
        text_email.clear();
        text_mdp.clear();
        text_tel.clear();
        text_role.clear();
        text_etat.clear();
        afficher();
            
    }else{
           String query = "UPDATE admin SET  username = '" + text_nom.getText()+
             "', email = '"+text_email.getText()+ 
             "', password = '"+text_mdp.getText()+
             "', userphone = '"+text_tel.getText()+ 
             "', useraddress = '"+text_role.getText()+
             "', usercin = '"+Integer.parseInt(text_etat.getText())+
             "' WHERE id = '" + Integer.parseInt(text_id.getText())
             +
             "'";
       executeQuery(query);
             JOptionPane.showMessageDialog(null, "Modification termine");
        text_id.clear();
        text_nom.clear();
        text_email.clear();
        text_mdp.clear();
        text_tel.clear();
        text_role.clear();
        text_etat.clear();
        afficher3(); 
        }
    }

    @FXML
    private void deleteclient(ActionEvent event) {
         if (role.getValue().equalsIgnoreCase("Client")){
        Userdao cc=new Userdao();
         
      cc.delete(tableuser.getSelectionModel().getSelectedItem());
        JOptionPane.showMessageDialog(null, "suppression termine");  
       text_id.clear();
        text_nom.clear();
        text_email.clear();
        text_mdp.clear();
        text_tel.clear();
        text_role.clear();
        text_etat.clear();
         afficher();

    }else{
             Userdao cc=new Userdao();
         
      cc.delete2(tableuser1.getSelectionModel().getSelectedItem());
        JOptionPane.showMessageDialog(null, "suppression termine");  
     
       text_id.clear();
        text_nom.clear();
        text_email.clear();
        text_mdp.clear();
        text_tel.clear();
        text_role.clear();
        text_etat.clear(); 
        afficher3();
         }
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
    private void exit(MouseEvent event) {
        System.exit(0);
         
    }

    private void findclient(ActionEvent event) {
        String rch = rech.getText();
        loadData1(rch);
        rech.clear();
        
    }


    @FXML
    private void Refresh2(MouseEvent event) {
        
        text_id.clear();
        text_nom.clear();
        text_email.clear();
        text_mdp.clear();
        text_tel.clear();
        text_role.clear();
        text_etat.clear();
    }

    @FXML
    private void retour(MouseEvent event) {
        
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/View/padmin.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                //stage.setTitle("Acceuil");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                 System.out.println("Erreur 1 !!!");
        }
    }

    @FXML
    private void recherchenom(MouseEvent event) {
         rech.textProperty().addListener((observable,oldValue,newValue)-> {
             loadData1(rech.getText());
         });
    }

    @FXML
    private void recherchenom1(MouseEvent event) {
        rech1.textProperty().addListener((observable,oldValue,newValue)-> {
             loadData4(rech1.getText());
         });
    }

    @FXML
    private void Astuce(ActionEvent event) throws IOException {
         Parent fxml = FXMLLoader.load(getClass().getResource("/shahba/UI/back/Astuce.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }

    @FXML
    private void Video(ActionEvent event) throws IOException {
         Parent fxml = FXMLLoader.load(getClass().getResource("/shahba/UI/back/Video.fxml"));
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
 

    private void closeAcceuil2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void Evenement(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/shahba/UI/back/evenement.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }
    @FXML
    private void Reservation(ActionEvent event) throws IOException {
        Parent fxml;
        fxml = FXMLLoader.load(getClass().getResource("/shahba/UI/back/reservation.fxml"));
      displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
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

    @FXML
    private void Produit(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/shahba/UI/back/MenuArticle.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }


    @FXML
    private void Commande(ActionEvent event) throws IOException {
            Parent fxml = FXMLLoader.load(getClass().getResource("/shahba/UI/back/CommandesLivraisons.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
        
    }

         @FXML
    private void Reclamation(ActionEvent event) throws IOException {
                 Parent fxml = FXMLLoader.load(getClass().getResource("/shahba/UI/back/back_ui.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
        
    }

}