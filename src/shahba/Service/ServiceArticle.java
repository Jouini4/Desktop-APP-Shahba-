/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.Service;

import shahba.entity.Article;
import shahba.utils.MyConnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shahba.IService.IService;

/**
 *
 * @author ASUS
 */
public class ServiceArticle implements IService<Article>{
    Connection cnx = MyConnexion.getInstance().getCnx();

    @Override
    //CRUD AJOUTER ARTICLE
    public void ajouter(Article t) {

        try {
            //String req = "INSERT INTO article (titre_art,auteur_art,date_art,description_art,vues,id_cat) VALUES  (?,?,?,?,?,?)";
            String req = "INSERT INTO produit VALUES  (?,(select id from categorie where nom_categorie=?),?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, t.getId());
            pst.setString(3, t.getNom_produit());
            pst.setString(4, t.getDescription());
            pst.setInt(7, t.getLikes());
            pst.setString(2, t.getNom_categorie());
            pst.setString(5, t.getImage());
            //pst.setString(8, t.getNom_categorie());
            pst.setFloat(6, t.getPrix());
            
            pst.executeUpdate();
            System.out.println("Article ajouté !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public int Getid(String Nom){
        int idcat = 0;
        Connection cnx = MyConnexion.getInstance().getCnx();
        String reqq ="select from categorie id where nom_categorie="+Nom;
        try {
            PreparedStatement pst = cnx.prepareStatement(reqq);
           ResultSet rs = pst.executeQuery(reqq);
            while (rs.next()) {
            idcat = rs.getInt("categorie_id");
             
            } 
           
            
        } catch (SQLException ex) {
          
        }
        
       return idcat;
        
    }
////CRUD SUPPRIMER ARTICLE
    public void supprimer(Integer t) {
        try {
            String req = "DELETE FROM `produit` WHERE `id`=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, t);
            pst.executeUpdate();
            System.out.println("Article supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    

//    //CRUD SUPPRIMER ARTICLE PAR ID
   
    public void supprimerArticleById(Article t,int id) {
        try {
            String req = "DELETE FROM produit WHERE id_art=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
             System.out.println("Article supprimé !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

////CRUD MODIFIER ARTICLE
    @Override
    public void modifier(Article t) {
        try {
            String req = "UPDATE produit SET nom_produit=?,description=?,image=?,prix=?  WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, t.getNom_produit());
            pst.setString(2, t.getDescription());
            pst.setString(4, t.getImage());
            pst.setFloat(5, t.getPrix());
            pst.setInt(6, t.getId());

            pst.executeUpdate();
            System.out.println("Article " + t.getNom_produit()+":" + " modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
   
//    public void modifier(Article t) {
//        try {
//            String req = "UPDATE article SET titre_art=?,description_art=?  WHERE id_art=?";
//            PreparedStatement pst = cnx.prepareStatement(req);
//            pst.setString(1, t.getTitre_art());
//            pst.setString(2, t.getDescription_art());
//            pst.setInt(3, t.getId_art());
//
//            pst.executeUpdate();
//            System.out.println("Article_id modifié !"+ t.getId_art());
//
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//    }
//
//    //CRUD AFFICHER LA LISTE DE TOUTES LES ARTICLES
    @Override
    public ObservableList<Article> afficher() {
        ObservableList<Article> list = FXCollections.observableArrayList();
      

        try {
            //String requete = "SELECT * FROM article";
            String requete="select  a.nom_produit ,a.description, a.image ,a.prix, cat.nom_categorie from produit a, categorie cat where (a.categorie_id= cat.id)";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
           
            while (rs.next()) {
               // list.add(new Article(rs.getInt(1),rs.getString(2), rs.getString(2), rs.getDate(3), rs.getString(4),rs.getInt(5), rs.getString(6), rs.getString(7)));
               list.add(new Article(rs.getString(1),rs.getString(2),rs.getString(4),rs.getFloat(4),rs.getString(5)));
               
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

//    //CRUD AFFICHER LISTE DES ARTICLES SELON CATEGORIE
    
    public ArrayList<Article> afficherArticleByCategorie(String x) {
        ArrayList<Article> listCat = new ArrayList<>();
        try {
            
            //String req = "select * from article a, categorie cat where (a.id_cat= cat.id_cat and cat.titre_cat='"+x+"')";
             String req ="select a.id, a.nom_produit,a.description,a.photo,a.prix,cat.nom_categorie from produit a, categorie cat where (a.id_cat= cat.id_cat and cat.titre_cat='"+x+"')";
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
              listCat.add(new Article(rs.getString(1),rs.getString(2),rs.getString(3),rs.getFloat(5),rs.getString(4)));
             
            }
 
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        if (listCat.isEmpty()) {
            System.out.println("Il y a aucun article de cette categorie");
        }

        return listCat;
    }

//    //METIER : FAIRE DES LIKES (REACTIONS)
    
    public void Likes(Article t) {
        try {
            String req = "UPDATE produit SET likes=likes+1 WHERE id_art=?";
            PreparedStatement pst1 = cnx.prepareStatement(req);
            pst1.setInt(1, t.getId());
            pst1.executeUpdate();
            req = "SELECT likes FROM produit WHERE id_art=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, t.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("l'article_id : " + t.getId() + " , Réaction_Likes = " + rs.getInt(1) );
                
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    

    @Override
    public void supprimer(Article t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    

  
    

}
