/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.UI.back;

import shahba.Service.*;
import shahba.entity.Categorie;
import shahba.utils.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import shahba.IService.IService;

/**
 *
 * @author ASUS
 */
public class ServiceCategorie implements IService<Categorie>{
    Connection cnx = MyConnexion.getInstance().getCnx();
    @Override
    public void ajouter(Categorie t) {
        try {
            String req = "INSERT INTO categorie (id,nom_categorie) VALUES  (?,?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, t.getId());
            pst.setString(2, t.getNom_categorie());
            pst.executeUpdate();
            System.out.println("Catégorie ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Categorie t) {
        try {
            String req = "DELETE FROM categorie WHERE nom_categorie=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, t.getNom_categorie());
            pst.executeUpdate();
            System.out.println("Catégorie supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
     public void supprimerCatById(int id) {
        try {
            String req = "DELETE FROM categorie WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
             System.out.println("Cat_id " + id +":" + " supprimé !");


        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Categorie> afficher() {
       List<Categorie> list = new ArrayList<>();

        try {
            String requete = "SELECT nom_categorie FROM categorie";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Categorie(rs.getString(1)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public void modifier(Categorie t) {
         //To change body of generated methods, choose Tools | Templates.
    }
}
