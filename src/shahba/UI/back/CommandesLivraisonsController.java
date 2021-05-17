/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.UI.back;

import Controller.SigninController;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.IntegerStringConverter;
import shahba.Service.ServiceCommande;
import shahba.Service.ServiceLivraison;
import entity.User;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import shahba.entity.commande;
import shahba.entity.livraison;

/**
 * FXML Controller class
 *
 * @author Anis
 */
public class CommandesLivraisonsController implements Initializable {

 
    @FXML
    private AnchorPane displayArea;
    @FXML
    private Button stat;
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
    private Button btnOverview111;
    @FXML
    private Button btnSignout;
    

    public CommandesLivraisonsController() {
    }
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    @FXML
    private TableView<commande> Commandes;

    @FXML
    private TableColumn<commande, String> Nom;

    @FXML
    private TableColumn<commande, String> Prenom;

    @FXML
    private TableColumn<commande, String> Email;

    @FXML
    private TableColumn<commande, String> Adresse;

    @FXML
    private TableColumn<commande, String> description_adresse;

    @FXML
    private TableColumn<commande, String> gouvernorat;

    @FXML
    private TableColumn<commande, Integer> code_postal;

    @FXML
    private TableColumn<commande, Integer> numero_telephone;
    
    
    
    
    private ServiceCommande sco = new ServiceCommande();
    private ServiceLivraison sl = new ServiceLivraison();
    private User c = SigninController.login;
    
    private final ObservableList<commande> data = FXCollections.observableArrayList();
    private List<commande> commandes = new ArrayList<>();
    
    private final ObservableList<livraison> data1 = FXCollections.observableArrayList();
    private List<livraison> livraisons = new ArrayList<>();
    
        @FXML
    private Button supprimer;
        
        @FXML
    private TableView<livraison> Livraisons;
    
    @FXML
    private TableColumn<livraison, String> l_adresse;

    @FXML
    private TableColumn<livraison, String> l_numero_telephone;

    @FXML
    private TableColumn<livraison, String> statut;

    @FXML
    private Button l_supprimer;

    @FXML
    private Button livrer;
    
        @FXML
    private Label nbCommandes;

    @FXML
    private Label Revenue;
    
     @FXML
    private TextField recherche;
     
     
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            commandes();
            livraisons();
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
    private void livraisons() throws SQLException{
        Livraisons.setItems(null);
        data1.clear();
        livraisons = sl.afficher();
        for(livraison livraison:livraisons)
        {
            data1.add(livraison);
        }
        l_adresse.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getCommande().getAdresse()));
        l_numero_telephone.setCellValueFactory(e-> new SimpleStringProperty(String.valueOf(e.getValue().getCommande().getTel())));
        statut.setCellValueFactory(new PropertyValueFactory<>("statut"));
        
        Livraisons.setItems(data1);
        
        l_adresse.setCellFactory(TextFieldTableCell.forTableColumn());
        l_numero_telephone.setCellFactory(TextFieldTableCell.forTableColumn());
        statut.setCellFactory(TextFieldTableCell.forTableColumn());
        
    }
    
    @FXML
    void supprimer_livraison(ActionEvent event) throws SQLException{
        if(Livraisons.getSelectionModel().getSelectedItems().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Supprimer Livraison");
            alert.setHeaderText("");
            alert.setContentText("Veuillez choisir une livraison a supprimer!");
            alert.showAndWait();
        }else{
            ObservableList<livraison> all,single;
        all=Livraisons.getItems();
        single=Livraisons.getSelectionModel().getSelectedItems();
        sl.supprimer(single.get(0));
        single.forEach(all::remove);
        }
    }
    
    @FXML
    void modifier_livraison(ActionEvent e)throws SQLException{
        
        livraison selected = Livraisons.getSelectionModel().getSelectedItem();
        if(selected.getStatut().equals("En Cours"))
        {
             selected.setStatut("Livree");
        sl.modifier(selected);
        livraisons();
        }else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modifier Livraison");
            alert.setHeaderText("");
            alert.setContentText("La livraison est deja livree!");
            alert.showAndWait();
        }
       
    }
    
    private void commandes() throws SQLException{
        Commandes.setItems(null);
        data.clear();
        commandes = sco.afficher();
        nbCommandes.setText(nbCommandes.getText()+" "+String.valueOf(sco.totalCommandes()));
        Revenue.setText(Revenue.getText()+" "+String.valueOf(sco.Revenue()));
        
        for(commande commande:commandes)
        {
            data.add(commande);
        }
        
        Nom.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getClient().getUsername()));
        Prenom.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getClient().getUsername()));
        Email.setCellValueFactory(e-> new SimpleStringProperty(e.getValue().getClient().getEmail()));
        Adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        description_adresse.setCellValueFactory(new PropertyValueFactory<>("description_adresse"));
        gouvernorat.setCellValueFactory(new PropertyValueFactory<>("gouvernorat"));
        code_postal.setCellValueFactory(new PropertyValueFactory<>("codeP"));
        numero_telephone.setCellValueFactory(new PropertyValueFactory<>("tel"));
        
        
        Commandes.setItems(data);
        Commandes.setEditable(true);
        
         Nom.setCellFactory(TextFieldTableCell.forTableColumn());
        Prenom.setCellFactory(TextFieldTableCell.forTableColumn());
        Email.setCellFactory(TextFieldTableCell.forTableColumn());
        Adresse.setCellFactory(TextFieldTableCell.forTableColumn());
        description_adresse.setCellFactory(TextFieldTableCell.forTableColumn());
        gouvernorat.setCellFactory(TextFieldTableCell.forTableColumn());
        code_postal.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        numero_telephone.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
           
    }
    
    @FXML
    void supprimerCommande(ActionEvent event) throws SQLException
    {
        if(Commandes.getSelectionModel().getSelectedItems().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Supprimer Commande");
            alert.setHeaderText("");
            alert.setContentText("Veuillez choisir une commande a supprimer!");
            alert.showAndWait();
        }else{
            ObservableList<commande> all,single;
        all=Commandes.getItems();
        single=Commandes.getSelectionModel().getSelectedItems();
        sco.supprimer(single.get(0));
        single.forEach(all::remove);
        }
        
    }
    
    @FXML
    void modifier_adresse(TableColumn.CellEditEvent e) throws SQLException{
         
         commande selected = Commandes.getSelectionModel().getSelectedItem();
        selected.setAdresse(e.getNewValue().toString());
         if(e.getNewValue().toString().equals(""))
        {
          
            alert.setTitle("Commande");
            alert.setHeaderText("");
            alert.setContentText("Veuillez renseigner le champ!");
            alert.showAndWait();  
        }
         else{
        
        sco.modifier(selected);
         }
    }
    
    
    @FXML
    void modifier_description_adresse(TableColumn.CellEditEvent e) throws SQLException{
        commande selected = Commandes.getSelectionModel().getSelectedItem();
        selected.setDescription_adresse(e.getNewValue().toString());
                 if(e.getNewValue().toString().equals(""))
        {
          
            alert.setTitle("Commande");
            alert.setHeaderText("");
            alert.setContentText("Veuillez renseigner le champ!");
            alert.showAndWait();  
        }
         else{
        
        sco.modifier(selected);
         }
        
    }
    
    @FXML
    void modifier_gouvernorat(TableColumn.CellEditEvent e) throws SQLException{
        commande selected = Commandes.getSelectionModel().getSelectedItem();
        selected.setGouvernorat(e.getNewValue().toString());
                    if(e.getNewValue().toString().equals(""))
        {
          
            alert.setTitle("Commande");
            alert.setHeaderText("");
            alert.setContentText("Veuillez renseigner le champ!");
            alert.showAndWait();  
        }
         else{
        
        sco.modifier(selected);
         }
    }
    
    @FXML
    void modifier_codePostal(TableColumn.CellEditEvent e) throws SQLException{
        commande selected = Commandes.getSelectionModel().getSelectedItem();
        selected.setCodeP(Integer.valueOf(e.getNewValue().toString()));
                    if(e.getNewValue().toString().equals(""))
        {
          
            alert.setTitle("Commande");
            alert.setHeaderText("");
            alert.setContentText("Veuillez renseigner le champ!");
            alert.showAndWait();  
        }
         else{
        
          if(!e.getNewValue().toString().matches("\\d+"))
            {
            alert.setTitle("Commande");
            alert.setHeaderText("");
            alert.setContentText("Code Postal doit etre composé par des chiffres !");
            alert.showAndWait(); 
            }

          else{
         if(e.getNewValue().toString().length()!=4)
            {
            alert.setTitle("Commande");
            alert.setHeaderText("");
            alert.setContentText("Code Postal doit etre composé par 4 chiffres !");
            alert.showAndWait();  
            }
                  else
                  {
        sco.modifier(selected);
                  }
         }
         }
    }
    
    @FXML
    void modifier_numero_telephone(TableColumn.CellEditEvent e) throws SQLException{
        commande selected = Commandes.getSelectionModel().getSelectedItem();
        selected.setTel(Integer.valueOf(e.getNewValue().toString()));
         if(e.getNewValue().toString().equals(""))
        {
          
            alert.setTitle("Commande");
            alert.setHeaderText("");
            alert.setContentText("Veuillez renseigner le champ!");
            alert.showAndWait();  
        }
         
         else{
             if(!e.getNewValue().toString().matches("\\d+"))
            {
             alert.setTitle("Commande");
            alert.setHeaderText("");
            alert.setContentText("Numero Telephone doit etre composé par des chiffres !");
            alert.showAndWait(); 
            }

          else{
                  if(e.getNewValue().toString().length()!=8)
            {
                alert.setTitle("Commande");
            alert.setHeaderText("");
            alert.setContentText("Nuemro Telephone doit etre composé par 8 chiffres !");
            alert.showAndWait();  
            }
                  else
                  {
        sco.modifier(selected);
                  }
         }
         }
    }
    
    @FXML
    public void Recherche(){

         FilteredList<commande> filteredData = new FilteredList<>(data, b -> true);
         
         recherche.textProperty().addListener((observable, oldValue, newValue) -> {
             filteredData.setPredicate(commande ->{
                 commande c = commande;
                 
                   if (newValue == null || newValue.isEmpty()) {
                    return true;
                    }
                    
                   String lowerCaseFilter = newValue.toLowerCase();
                    
                    if(c.getClient().getUsername().toLowerCase().indexOf(lowerCaseFilter)!= -1)
                    {
                       return true; 
                    }
                    else if(c.getClient().getEmail().toLowerCase().indexOf(lowerCaseFilter)!= -1)
                    {
                        return true;
                    }
                    else if(c.getGouvernorat().toLowerCase().indexOf(lowerCaseFilter)!= -1)
                    {
                        return true;
                    }
                    else if(String.valueOf(c.getCodeP()).toLowerCase().indexOf(lowerCaseFilter)!= -1)
                    {
                        return true;
                    }
                    else if(String.valueOf(c.getTel()).toLowerCase().indexOf(lowerCaseFilter)!= -1)
                    {
                        return true;
                    }else
                   
                   return false;
             }); 
         });
               
         SortedList<commande> sortedData = new SortedList<>(filteredData);
         
          sortedData.comparatorProperty().bind(Commandes.comparatorProperty());
          
          Commandes.setItems(sortedData);
    }

    @FXML
    private void stat(ActionEvent event) throws IOException, SQLException {
        final CategoryAxis xAxis = new CategoryAxis();
        
        final NumberAxis yAxis = new NumberAxis();
        
        final BarChart<String,Number> bc = 
            new BarChart<String,Number>(xAxis,yAxis);
        
        
         bc.setTitle("Nombre de commandes par client");
        xAxis.setLabel("Client");       
        yAxis.setLabel("Nombre de commandes");
        xAxis.setAnimated(true);
        ResultSet rs = sco.totalCommandesParClient();
        
        while(rs.next()){
            String nom = rs.getString("username");
            int total = rs.getInt("Totalcommandes");
            
            XYChart.Series series = new XYChart.Series();
            //series.setName(nom+" "+prenom);
            series.getData().add(new XYChart.Data(nom,total));
            bc.getData().add(series);
            
            
        }

        
        displayArea.getChildren().clear();
        displayArea.getChildren().add(bc);
    }

     @FXML
    private void Astuce(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("Astuce.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }

    @FXML
    private void Video(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("Video.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }

     @FXML
    private void Evenement(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("evenement.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }

    @FXML
    private void Reservation(ActionEvent event) throws IOException {
          Parent fxml;
        fxml = FXMLLoader.load(getClass().getResource("reservation.fxml"));
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
     
    private void exit(MouseEvent event) {
        
       System.exit(0);
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
    public void closeAcceuil2() {
        Stage Acceuil2Stage = (Stage) btn_decnx.getScene().getWindow();
        Acceuil2Stage.hide();
    }
     private JFXButton btn_decnx;

    @FXML
    private void Produit(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("MenuArticle.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
    }


    @FXML
    private void Commande(ActionEvent event) throws IOException {
            Parent fxml = FXMLLoader.load(getClass().getResource("CommandesLivraisons.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
        
    }

        @FXML
    private void Reclamation(ActionEvent event) throws IOException {
                 Parent fxml = FXMLLoader.load(getClass().getResource("back_ui.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
        
    }
    
}
