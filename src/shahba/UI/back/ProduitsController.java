/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.UI.back;

//import shahba.Main.MainGUI;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import shahba.entity.Article;
import shahba.Service.ServiceArticle;
import shahba.Service.ServiceCategorie;
import shahba.utils.MyConnexion;

/**
 * FXML Controller class
 *
 * @author Khaoula
 */
public class ProduitsController implements Initializable {
     int id_user;
    @FXML
    private TableView<Article> coltabab;
    @FXML
    private TableColumn<Article, String> ColTitre;
    @FXML
    private TableColumn<Article, String> ColDesc;
    @FXML
    private TableColumn<Article, String> ColCat;
    private TableColumn<Article, String> ColVue;
    @FXML
    private TableColumn<Article, String> Colimg;
    private TableColumn<Article, Integer> Colid;
    @FXML
    private TableColumn<Article, String> colprix;
 public void setid(int i)
 {
     id_user =i ;
 }

    ObservableList<Article> ArtList = FXCollections.observableArrayList();
    FilteredList<Article> filter = new FilteredList<Article>(ArtList, e -> true);
    SortedList<Article> sort = new SortedList<Article>(filter);
    Connection conn = null;
        PreparedStatement pre = null;
        ResultSet res = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         conn= MyConnexion.getInstance().getCnx();
        ArtList= FXCollections.observableArrayList();
          ServiceCategorie cat = new ServiceCategorie();
//        cat.afficher().forEach(e -> {
//            tcategorie.getItems().add(e.getTitre_cat());
//        });

        try {
            // TODO
            AfficherArt();
        } catch (SQLException ex) {
            Logger.getLogger(ListeArticlesController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            //click button droit pour supprimer
            ContextMenu ContArticle = new ContextMenu();
            MenuItem DeleteItem = new MenuItem("Supprimer Article");
            DeleteItem.setOnAction(new EventHandler<ActionEvent>() {
                
            @Override
            public void handle(ActionEvent event) {
                /*Publication pub_supp = (Publication) tableView_Publication.getSelectionModel().getSelectedItem();
                CellEditEvent eddited_cell;*/
                Integer item = ArtList.get(coltabab.getSelectionModel().getSelectedIndex()).getId();
               // Article art = (Article) item;
                ServiceArticle s = new ServiceArticle();
               // System.out.println(art.toString());
                s.supprimer(item);

                try {
                    AfficherArt();
                } catch (SQLException ex) {
                    Logger.getLogger(ListeArticlesController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }});
            EventHandler<WindowEvent> event = new EventHandler<WindowEvent>() {
            public void handle(WindowEvent e) {
                if (ContArticle.isShowing()) {
                    // System.out.println("Showing");
                } else {
                    //System.out.println("Hidden");
                }
            }
        };

        ContArticle.getItems().add(DeleteItem);

        ContArticle.setOnShowing(event);
        ContArticle.setOnHiding(event);
        coltabab.setContextMenu(ContArticle);
        }  
    
    private void AfficherArt() throws SQLException {
        ServiceArticle srec = new ServiceArticle();
        ArtList.clear();
        String query = "select a.nom_produit,a.description,cat.nom_categorie,a.image,a.prix from produit a, categorie cat where (a.categorie_id= cat.id)";
        pre = conn.prepareStatement(query);
        res = pre.executeQuery();
        while(res.next()){
            ArtList.add(new Article(
             res.getString("nom_produit"),
            res.getString("description"),
            res.getString("image"),
            res.getFloat("prix"),
            res.getString("nom_categorie")
            ));
            coltabab.setItems(ArtList);   
        } 
    
        ColTitre.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
        ColDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
         ColCat.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
        Colimg.setCellValueFactory(new PropertyValueFactory<>("image"));
        colprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        //Colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        
    }
     private void AfficherArt1(String cat) throws SQLException {
        ServiceArticle srec = new ServiceArticle();
        ArtList.clear();
        String query = "select a.nom_produit,a.description,a.nom_categorie,a.likes,a.image,a.prix from produit a where a.nom_categorie like '%"+cat+"%'";
        pre = conn.prepareStatement(query);
        res = pre.executeQuery();
        while(res.next()){
            ArtList.add(new Article(
            res.getString("nom_produit"),
            res.getString("description"),
            res.getString("image"),
            res.getFloat("prix"),
            res.getInt("likes"),
            res.getString("nom_categorie")));
            coltabab.setItems(ArtList);   
        }
        
         ColTitre.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
        ColDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
         ColCat.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
        ColVue.setCellValueFactory(new PropertyValueFactory<>("likes"));
        Colimg.setCellValueFactory(new PropertyValueFactory<>("image"));
        colprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    }
     
FXMLLoader loader = new FXMLLoader(getClass().getResource("PageArticle.fxml"));
private Stage primaryStage;
    private void descriprion(ActionEvent event) throws IOException {
            Stage window = primaryStage;
            Parent rootRec2 = FXMLLoader.load(getClass().getResource("PageArticle.fxml"));
            Scene rec2 = new Scene(rootRec2);
            Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app.setScene(rec2);
            app.show();
    }
    }
           

                   
    

  



