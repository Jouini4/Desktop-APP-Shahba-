/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shahba.IService.IServiceAstuce;
import shahba.entity.Astuce;
import shahba.utils.MyConnexion;

/**
 *
 * @author isslem
 */
public class ServiceAstuce  implements IServiceAstuce{

   
  Connection cnx;

    public ServiceAstuce() {
        cnx = MyConnexion.getInstance().getCnx();}
    @Override
    public void createAstuce(Astuce a) {
           String requete2 = "INSERT INTO astuce (titre,description,image) VALUES (?,?,?)";
        try {
            
            PreparedStatement pst = MyConnexion.getInstance().cnx.prepareStatement(requete2);
            pst.setString(1, a.getTitre());
            pst.setString(2, a.getDescription());
            pst.setString(3, a.getImage());
            pst.executeUpdate();
            System.out.println("Astuce added");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

      @Override
    public void update(Astuce a,int id) {
 try {
            String requete3 = "UPDATE astuce SET titre=?,description=?,image=? WHERE id=?";
            PreparedStatement pst2 = MyConnexion.getInstance().cnx.prepareStatement(requete3);
            pst2.setInt(4,id);
            pst2.setString(1, a.getTitre());
            pst2.setString(2, a.getDescription());
            pst2.setString(3, a.getImage());

            
            pst2.executeUpdate();
            System.out.println("Astuce updated");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }  
    }

    @Override
    public void delete(int id) {
 try {
            String requete7 = "DELETE FROM astuce WHERE id=?";
            PreparedStatement pst7 = MyConnexion.getInstance().cnx.prepareStatement(requete7);
            pst7.setInt(1, id);
            pst7.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
    }
    }
    
    
     @Override
    public List<Astuce> getAll() {
ObservableList<Astuce> myList = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM astuce";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Astuce a = new Astuce();
                a.setId(rs.getInt(1));
                a.setTitre(rs.getString("titre"));
                a.setDescription(rs.getString("description"));
                a.setImage(rs.getString("image"));
               
                myList.add(a);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
   
      return myList;

    }
  public ObservableList<Astuce> FindAstuce() {

        ObservableList<Astuce> list = FXCollections.observableArrayList();
        try {
            String requete4 = "SELECT * FROM astuce";
            Statement st5 = MyConnexion.getInstance().cnx.createStatement();
            ResultSet rs = st5.executeQuery(requete4);
            while (rs.next()) {
                Astuce a = new Astuce();
                a.setId(rs.getInt("id"));
                a.setTitre(rs.getString("titre"));
                a.setDescription(rs.getString("description"));
                a.setImage(rs.getString("image"));
              
                list.add(a);
            }

        } catch (SQLException ex) {
            System.out.println("error");
        }
        return list;

    }

    public Astuce recherche(int i) {
     String req = "select * from astuce where id=?";
        
        try {
            Statement st6=cnx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st6.executeQuery(req);
            rs.last();
            int nb=rs.getRow() ;
            rs.beforeFirst();
            if(nb!=0)
            {
                while(rs.next())
                {
                    int id = rs.getInt(1);
                    String titre = rs.getString(2);
                    String description = rs.getString(3);
                    String image = rs.getString(4);
                   
                    
                    Astuce a = new Astuce(id, titre, description, image);
                    System.out.println(a);
                    return a;
                }
            }else{
                return new Astuce();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage()+" "+e.getErrorCode());
        }
        return new Astuce();
    }
    


   
}
