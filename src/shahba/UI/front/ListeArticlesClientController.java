/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.UI.front;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import shahba.entity.Article;
import shahba.Service.ServiceArticle;
//import shahba.Main.MainGUI;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ListeArticlesClientController implements Initializable {

   @FXML
    private TextField tfrech;
    @FXML
    private Button btnback;
    @FXML
    private TableView<Article> coltabab;

    @FXML
    private TableColumn<Article, String> ColTitre;
    @FXML
    private TableColumn<Article, String> ColDesc;
    @FXML
    private TableColumn<Article, Integer> ColVue;
    @FXML
    private TableColumn<Article, String> ColCat;
    

    @FXML
    private TableColumn<Article, String> Colimg;
    private Stage primaryStage;

    ObservableList<Article> ArtList = FXCollections.observableArrayList();
    FilteredList<Article> filter = new FilteredList<Article>(ArtList, e -> true);
    SortedList<Article> sort = new SortedList<Article>(filter);
    FXMLLoader loader = new FXMLLoader(getClass().getResource("PageArticle.fxml"));
    @FXML
    private Button btnback1;
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    public void aff() {
        try {
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            Object item = coltabab.getSelectionModel().getSelectedItem();
            Article art = (Article) item;
            stage.setScene(new Scene(root));

            PageArticleController dpc = loader.getController();
            dpc.setLab_tit(art.getNom_produit());
            dpc.setLab_desc(art.getDescription());
            dpc.setLab_cat(art.getNom_categorie());
            dpc.load_pic(art.getImage());
            
            
            
            String str = Integer.toString(art.getLikes());
            dpc.setLab_like(str);
            dpc.setLab_img(art.getImage());

            stage.show();
           // MainGUI.stg.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherArt();

        ContextMenu ContArticle = new ContextMenu();
        MenuItem LikeItem = new MenuItem("Like ☺ ");
        MenuItem AfficheArte = new MenuItem("Afficher Article");

        AfficheArte.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                aff();
            }
        }
        );
        
        LikeItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               
                Object item = coltabab.getSelectionModel().getSelectedItem();
                Article art = (Article) item;
                ServiceArticle s = new ServiceArticle();
                System.out.println(art.toString());
                s.Likes(art);

                AfficherArt();

            }
        }
        );
        EventHandler<WindowEvent> event = new EventHandler<WindowEvent>() {
            public void handle(WindowEvent e) {
                if (ContArticle.isShowing()) {
                    // System.out.println("Showing");
                } else {
                    //System.out.println("Hidden");
                }
            }
        };

        ContArticle.getItems().add(AfficheArte);
        ContArticle.getItems().add(LikeItem);

        ContArticle.setOnShowing(event);
        ContArticle.setOnHiding(event);
        coltabab.setContextMenu(ContArticle);

    }


    private void AfficherArt() {
        ColTitre.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
        ColDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        ColCat.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
        Colimg.setCellValueFactory(new PropertyValueFactory<>("image"));

        ServiceArticle srec = new ServiceArticle();
        ArtList.removeAll();
        ArtList.clear();
        srec.afficher().forEach(e -> {
            ArtList.add(e);
        });
        coltabab.setItems(ArtList);
    }
    
   

    @FXML
    private void description(ActionEvent event) throws IOException {
            Stage window = primaryStage;
            Parent rootRec2 = FXMLLoader.load(getClass().getResource("PageArticle.fxml"));
            Scene rec2 = new Scene(rootRec2);
            Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app.setScene(rec2);
            app.show();
    }
    

    @FXML
    private void backtomenu(ActionEvent event) throws IOException {
        Parent rootRec2 = FXMLLoader.load(getClass().getResource("MenuArticleClient.fxml"));
        Scene rec2 = new Scene(rootRec2);
        Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app.setScene(rec2);
        app.show();
    }

    private void triDate(ActionEvent event) {
        try {
            ObservableList<Article> ArtList = FXCollections.observableArrayList();
            //ColArt.setCellValueFactory(new PropertyValueFactory<>("id_art"));
        ColTitre.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
        ColDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        ColCat.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
        Colimg.setCellValueFactory(new PropertyValueFactory<>("image"));
            

            coltabab.setItems(ArtList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void search_released(KeyEvent event) {
if (tfrech.getText().isEmpty()) {
            // JOptionPane.showMessageDialog(null, "Remplir le champs vide");
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Veuillez remplir le champs vide ! ");
            al.showAndWait();

        } else if ( tfrech.getText().matches(".*[0-9].*")||tfrech.getText().matches(".*[%-@-.-/-!-;-,-_].*")) {
            Alert a2 = new Alert(Alert.AlertType.ERROR);
            a2.setHeaderText(null);
            a2.setContentText("Veuillez saisir uniquement des lettres ! ");
            a2.showAndWait();

        } else {
        
        tfrech.setOnKeyReleased(e -> {
            tfrech.textProperty().addListener((observable, oldValue, newValue) -> {
                filter.setPredicate(Article -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (Article.getNom_categorie().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else {
                        return false;
                    }
                });

            });
            sort.comparatorProperty().bind(coltabab.comparatorProperty());
            coltabab.setItems(sort);
        });
        
    }
    } 
  
}
