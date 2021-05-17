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

import shahba.IService.IServiceVideo;

import shahba.entity.Video;
import shahba.utils.MyConnexion;

/**
 *
 * @author isslem
 */
public class ServiceVideo  implements IServiceVideo{

   
  Connection cnx;

    public ServiceVideo() {
        cnx = MyConnexion.getInstance().getCnx();}
    
    
  @Override
     public void createVideo(Video v) {
           String requete2 = "INSERT INTO video (titre,description,url) VALUES (?,?,?)";
        try {
            
            PreparedStatement pst = MyConnexion.getInstance().cnx.prepareStatement(requete2);
            pst.setString(1, v.getTitre());
            pst.setString(2, v.getDescription());
            pst.setString(3, v.getUrl());
            pst.executeUpdate();
            System.out.println("video added");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

     
  @Override
    public void update(Video v,int id) {
 try {
            String requete3 = "UPDATE video SET titre=?,description=?,url=? WHERE id=?";
            PreparedStatement pst2 = MyConnexion.getInstance().cnx.prepareStatement(requete3);
            pst2.setInt(4,id);
            pst2.setString(1, v.getTitre());
            pst2.setString(2, v.getDescription());
            pst2.setString(3, v.getUrl());

            
            pst2.executeUpdate();
            System.out.println("Video updated");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }  
    }

    
  @Override
    public void delete(int id) {
 try {
            String requete7 = "DELETE FROM video WHERE id=?";
            PreparedStatement pst7 = MyConnexion.getInstance().cnx.prepareStatement(requete7);
            pst7.setInt(1, id);
            pst7.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
    }
    }
    
    
    
  @Override
    public List<Video> getAll() {
ObservableList<Video> myList = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM video";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                Video v = new Video();
                v.setId(rs.getInt(1));
                v.setTitre(rs.getString("titre"));
                v.setDescription(rs.getString("description"));
                v.setUrl(rs.getString("url"));
               
                myList.add(v);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
   
      return myList;

    }
  public ObservableList<Video> FindVideo() {

        ObservableList<Video> list = FXCollections.observableArrayList();
        try {
            String requete4 = "SELECT * FROM video";
            Statement st5 = MyConnexion.getInstance().cnx.createStatement();
            ResultSet rs = st5.executeQuery(requete4);
            while (rs.next()) {
                Video v = new Video();
                v.setId(rs.getInt("id"));
                v.setTitre(rs.getString("titre"));
                v.setDescription(rs.getString("description"));
                v.setUrl(rs.getString("video"));
              
                list.add(v);
            }

        } catch (SQLException ex) {
            System.out.println("error");
        }
        return list;

    }

   
   
}
