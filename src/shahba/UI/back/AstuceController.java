/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.UI.back;

import com.jfoenix.controls.JFXButton;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import shahba.Service.ServiceAstuce;
import shahba.entity.Astuce;

/**
 * FXML Controller class
 *
 * @author isslem
 */
public class AstuceController implements Initializable {

     @FXML
    private TextField txttitre;
     @FXML
    private TextField txtdescription;
    @FXML
    private TextField txtimage;
    @FXML
    private Button btnajouter;
    
    ServiceAstuce as = new ServiceAstuce();
    ObservableList<Astuce> data = FXCollections.observableArrayList(as.getAll());;
     
    
    
    @FXML
    private TableColumn<Astuce, String> astuce_name;
    @FXML
    private TableColumn<Astuce, String> astuce_description;
    @FXML
    private TableColumn<Astuce, String> astuce_image;
    @FXML
    private Button btnsupp;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnclear;
   
    
    @FXML
    private ImageView imview;
    @FXML
    private Button upload;
    @FXML
    private TableView<Astuce> table;
    @FXML
    private TextField recherche;
    @FXML
    private AnchorPane displayArea;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Button btnOverview1;
    @FXML
    private Button btnOverview11;
    @FXML
    private Button btnOverview111;
    @FXML
    private Button Video;
    @FXML
    private Button Astuce;
    

    
    
       @Override
    public void initialize(URL url, ResourceBundle rb) {
       astuce_name.setCellValueFactory(new PropertyValueFactory("titre"));
         astuce_description.setCellValueFactory(new PropertyValueFactory("description"));
         astuce_image.setCellValueFactory(new PropertyValueFactory("image"));
        table.setItems(data);

      table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
           
             @Override
             public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                 if (table.getSelectionModel().getSelectedItem() != null) {
                    shahba.entity.Astuce a = (shahba.entity.Astuce) table.getSelectionModel().getSelectedItem();
                     System.out.println();
                      txttitre.setText(a.getTitre());
                      txtdescription.setText(a.getDescription());
                      txtimage.setText(a.getImage());
                     
                 
                    btnajouter.setDisable(true);
                              
                }
            }
        }        ); 
             
        
        recherche.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue ) {
               filtrerAstuceList((String) oldValue, (String) newValue );
               }

        });
    } 
       
   
  void filtrerAstuceList(String oldValue, String newValue) {
        ServiceAstuce evs = new ServiceAstuce();
        ObservableList<Astuce> filteredList = FXCollections.observableArrayList();
        if (recherche.getText() == null || newValue == null) {
            table.setItems((ObservableList<Astuce>) evs.FindAstuce());
        } else {
            table.setItems((ObservableList<Astuce>) evs.FindAstuce());
            newValue = newValue.toUpperCase();

            for (Astuce a : table.getItems()) {

                String filterAstuceName = a.getTitre();
               

                if (filterAstuceName.toUpperCase().contains(newValue)) {
                    filteredList.add(a);
                }
            }
            table.setItems(filteredList);
        }
    }
 @FXML
    private void Ajout(ActionEvent event) {
   
           if (txttitre.getText() == null || txttitre.getText().trim().isEmpty()) {
             Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir les champs s'il vous plait!");
            dialogW.showAndWait();
            } 
           else if (txtdescription.getText() == null || txtdescription.getText().trim().isEmpty()) {
             Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir les champs s'il vous plait!");
            dialogW.showAndWait();
            } 
            else if (txtimage.getText() == null || txtimage.getText().trim().isEmpty()) {
             Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("A warning dialog-box");
            dialogW.setHeaderText(null); // No header
            dialogW.setContentText("veuillez remplir les champs s'il vous plait!");
            dialogW.showAndWait();
            } 
            
             else {
      Astuce a = new shahba.entity.Astuce(txttitre.getText(), txtdescription.getText(),txtimage.getText());
       as.createAstuce(a);
       }
     data.removeAll(data);
         for (Astuce ev : FXCollections.observableArrayList(as.getAll())) {
            data.add(ev);
            

        }
         clear();   
    }

    private void clear() {
        table.getSelectionModel().clearSelection();
       txttitre.clear();
        txtdescription.clear();
        txtimage.clear();
         btnajouter.setDisable(false);
        btnsupp.setDisable(false);
        btnmodif.setDisable(false);
        btnajouter.setDisable(false);
    }
     @FXML
    private void Delete(ActionEvent event) {
         ButtonType okButtonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        Dialog<ButtonType> dialog = new Dialog<>();
         dialog.setContentText("Voulez vous supprimé cette astuce !!!");
        dialog.getDialogPane().getButtonTypes().add(okButtonType);
        dialog.getDialogPane().getButtonTypes().add(cancelButtonType);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            if (table.getSelectionModel().getSelectedItem() != null) {
                JOptionPane.showMessageDialog(null, "Astuce Supprimee");
                as.delete(table.getSelectionModel().getSelectedItem().getId());
                data.removeAll(data);
                for (Astuce e : FXCollections.observableArrayList(as.getAll())) {
                    data.add(e);
                }

            }
            clear();
        } else {
            System.out.println("Cancel");
        }
    }

    @FXML
    private void Modifier(ActionEvent event) {
         if (table.getSelectionModel().getSelectedItem() != null) {
            as.update(new shahba.entity.Astuce(txttitre.getText(), txtdescription.getText(),txtimage.getText()), table.getSelectionModel().getSelectedItem().getId());
            data.removeAll(data);
            for (Astuce e : FXCollections.observableArrayList(as.getAll())) {
                data.add(e);
            }
            clear();
            

        }
    }

    @FXML
    private void Clear(ActionEvent event) {
        clear();
       
    }
    
  /*   @FXML
    private void pickscreen(MouseEvent event) {

        FileChooser fileChooser = new FileChooser();
        
        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG);
        
        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
        
        try {
            name=generatename()+".jpg";
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            File child = new File(Paths.get("").toString()+"./src/barmejha/img/"+name);
            System.out.println(child.getAbsolutePath());
            bufferedImage = resize(bufferedImage,159,159);
            Astuce.setImage(image);
            ImageIO.write(SwingFXUtils.fromFXImage(Astuce.getImmage()),
                              null), "JPG", child);
            
            System.out.println(titre);
        } catch (Exception ex) {
            titre="noavailable.png";
        }
        
    }
    
    
    
    private static BufferedImage resize(BufferedImage img, int height, int width) {
        java.awt.Image tmp = img.getScaledInstance(width, height, 0);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
    
    void initFT() {   
        titre.setTitre("");
        titre.setStyle(styledefault);
        adr.setText("");
        adr.setStyle(styledefault);
        gov.setText("");
        gov.setStyle(styledefault);
        imageview.setImage(new Image(new File(Paths.get("").toString()+"./src/barmejha/img/noavailable.png").toURI().toString(),159,159,false,false));    
        imageview1.setImage(new Image(new File(Paths.get("").toString()+"./src/barmejha/img/noavailable.png").toURI().toString(),159,159,false,false)); 
        imageview2.setImage(new Image(new File(Paths.get("").toString()+"./src/barmejha/img/noavailable.png").toURI().toString(),159,159,false,false)); 
    }

 */

   @FXML
    private void AjouterPhoto(ActionEvent event) throws IOException {
        

         FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pick a banner file!");
        fileChooser.setInitialDirectory(new File("C:/xampp/htdocs/me/DEV-master/Desktop/Integration/public/uploads"));
        Stage stage = new Stage();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("mp4", "*.MP4")
            );
        File file = fileChooser.showOpenDialog(stage);
        try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                txtimage.setText(file.getName());
                imview.setImage(image);
            } catch (IOException ex) {
                System.out.println("could not get the image");
            }

    }
    
 public void load_pic(String links) {

        Image img = new Image(links);
        imview.setImage(img);

    }

 
  /*@FXML
    private void addImage(ActionEvent event) {
            FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            upload.setVisible(false);
            imview.setImage(image);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
public static String saveToFileImageNormal(Image image) throws SQLException, IOException {

        String ext = "jpg";
        File dir = new File("C:\\wamp64\\www\\DEV-main (1)\\DEV-main\\public\\uploads");
        String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        File outputFile = new File(dir, name);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        ImageIO.write(bImage, "png", outputFile);
        return name;
    }
*/
    private void back(ActionEvent event) throws IOException {
         Parent fxml = FXMLLoader.load(getClass().getResource("Homeback.fxml"));
        displayArea.getChildren().clear();
        displayArea.getChildren().add(fxml);
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

   
  
        
    
