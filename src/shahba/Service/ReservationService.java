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
import shahba.IService.IReservationService;
import static  shahba.Service.EvenementService.getNbPlace;
import shahba.entity.evenement;
import shahba.entity.reservation;
import shahba.entity.user;
import shahba.utils.MyConnexion;

/**
 *
 * @author HAMMOUDA
 */
public class ReservationService implements IReservationService{
   Connection cnx;

    public ReservationService() {
        cnx = MyConnexion.getInstance().getCnx();
    }
    
    
    @Override
    public void createReservation(reservation r, int id_Event , int nbPlace , int user_id) {
                   try {
                    int newNbPlace = 0 ;
                    int  nbPlaceTotal = 0 ;
            String requete2 = "INSERT INTO reservation (nbrplace,approuve,id_Event,user_id) VALUES (?,?,?,?)";
            PreparedStatement pst = MyConnexion.getInstance().cnx.prepareStatement(requete2);
            pst.setInt(1, nbPlace);
            pst.setBoolean(2, false);
            pst.setInt(3, id_Event);
            pst.setInt(4, user_id );
            pst.executeUpdate();
      
          
            nbPlaceTotal = getNbPlace(id_Event);
            
            newNbPlace =  nbPlaceTotal -  nbPlace  ;
          
           String requete3 = "update evenement set nbr_place =  " + newNbPlace + "  where id = " + id_Event;
            PreparedStatement pst1 = MyConnexion.getInstance().cnx.prepareStatement(requete3);
            pst1.executeUpdate();
         
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
   @Override
     public void approuveReservation(int id){
        
          try {
               System.out.println("open");
         
          String requete44 = "update reservation set approuve  = true    where id = " + id +" ; ";
            System.out.println("show id   "  +id );
           PreparedStatement pst44 = MyConnexion.getInstance().cnx.prepareStatement(requete44);
            pst44.executeUpdate();         
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
         
         
         }

 

    @Override
    public List<reservation> getAll() {
        
        ObservableList<reservation> myList = FXCollections.observableArrayList();
String ch ;
        try {
            String requete = "SELECT re.id , re.nbrplace , re.approuve ,  ev.nom_event , us.username   FROM reservation re inner join evenement ev on (ev.id = re.id_Event ) inner join user us on (us.id = re.user_id) ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                reservation r = new reservation();
             
                r.setId(rs.getInt(1));
                r.setNbrplace(rs.getInt("nbrplace"));
                  r.setApprouve(rs.getBoolean("approuve"));
                    r.setUser_id(rs.getString("username"));  
                  r.setNom_event(rs.getString("nom_event"));
                  if (rs.getBoolean("approuve") )
                     r.setStatus("Confirmé") ;
                  else 
                      r.setStatus("En Cours") ;
                myList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
   
      return myList;

    }

   

        
    

    @Override
    public void delete(int id) {
         try {
            String requete7 = "DELETE FROM reservation WHERE id=?";
            PreparedStatement pst7 = MyConnexion.getInstance().cnx.prepareStatement(requete7);
            pst7.setInt(1, id);
            pst7.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
    }
    }



 

    
}
