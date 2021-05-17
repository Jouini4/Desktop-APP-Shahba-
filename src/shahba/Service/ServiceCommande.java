/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.Service;

import static Controller.SigninController.login;
import entity.User;
import java.sql.SQLException;
import shahba.IService.IServiceCommande;
import shahba.entity.commande;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import shahba.utils.MyConnexion;
/**
 *
 * @author Anis
 */
public class ServiceCommande implements IServiceCommande<commande>{

    private Connection con;
    private Statement st;
    private ResultSet rs ;
    private PreparedStatement pst ;

    private User client = login;
    
    public ServiceCommande()
    {
        con=MyConnexion.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(commande c) throws SQLException {
        pst = con.prepareStatement ("INSERT INTO commande (user_id,prix_total,adresse,description_adresse,gouvernorat,code_postal,numero_telephone,produits_id) VALUES (?,?,?,?,?,?,?,1) ");
        pst.setInt(1, login.getId());
        pst.setFloat(2, c.getPrixTotal());
        pst.setString(3, c.getAdresse());
        pst.setString(4, c.getDescription_adresse());
        pst.setString(5, c.getGouvernorat());
        pst.setInt(6, c.getCodeP());
        pst.setInt(7, c.getTel());
        pst.executeUpdate();
    }

    @Override
    public void modifier(commande c) throws SQLException {
        pst = con.prepareStatement ("UPDATE commande SET adresse=?,description_adresse=?,gouvernorat=?,code_postal=?,numero_telephone=? WHERE  ref=? ");
        pst.setString(1, c.getAdresse());
        pst.setString(2, c.getDescription_adresse());
        pst.setString(3, c.getGouvernorat());
        pst.setInt(4, c.getCodeP());
        pst.setInt(5, c.getTel());
        pst.setInt(6, c.getRef());
        
        pst.executeUpdate();
        
    }

   @Override
    public boolean supprimer(commande c) throws SQLException {
        pst = con.prepareStatement ("DELETE FROM commande WHERE ref=?");
        pst.setInt(1, c.getRef());
        pst.executeUpdate();
        return true;
    }

    @Override
    public List<commande> afficher() throws SQLException {
        List<commande> l = new ArrayList<>();
        st=con.createStatement();
        rs = st.executeQuery("select * from commande");
        while(rs.next())
        {
            int ref = rs.getInt(1);
            int client_id= rs.getInt(2);
            float prix_total = rs.getFloat(4);
            String adresse = rs.getString(5);
            String description_adresse = rs.getString(8);
            String gouvernorat = rs.getString(9);
            int code_postal = rs.getInt(10);
            int numero_telephone = rs.getInt(11);
            
            commande c = new commande(ref, client, prix_total, adresse, description_adresse, gouvernorat, code_postal, numero_telephone);
            l.add(c);
        }
        return l;
    }

    @Override
    public commande recherche(int id) throws SQLException {
        String req = "select * from commande where ref="+id;
        
        try {
            st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs=st.executeQuery(req);
            rs.last();
            int nb=rs.getRow() ;
            rs.beforeFirst();
            if(nb!=0){
                while(rs.next())
                {
                    int ref = rs.getInt(1);
            int client_id= rs.getInt(2);
            float prix_total = rs.getFloat(3);
            String adresse = rs.getString(4);
            String description_adresse = rs.getString(5);
            String gouvernorat = rs.getString(6);
            int code_postal = rs.getInt(7);
            int numero_telephone = rs.getInt(8);
            
            commande c = new commande(ref, client, prix_total, adresse, description_adresse, gouvernorat, code_postal, numero_telephone);
                
            return c;
                }
            }else{
                return new commande();
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new commande();
    }

    @Override
    public commande recherche_client(int id) throws SQLException {
        String req = "select * from commande where user_id="+id;
        
        try {
            st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs=st.executeQuery(req);
            rs.last();
            
            
            
                    int ref = rs.getInt(1);
            int client_id= rs.getInt(2);
            float prix_total = rs.getFloat(4);
            String adresse = rs.getString(5);
            String description_adresse = rs.getString(8);
            String gouvernorat = rs.getString(9);
            int code_postal = rs.getInt(10);
            int numero_telephone = rs.getInt(11);
            
            commande c = new commande(ref, client, prix_total, adresse, description_adresse, gouvernorat, code_postal, numero_telephone);
                
            return c;
  
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new commande();
   }

    @Override
    public int totalCommandes() throws SQLException {
        String req = "select count(user_id) from commande";
        try {
            st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs=st.executeQuery(req);
            rs.last();
            int tc = rs.getInt(1);
            return tc;
  
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        
            return 0;
    }
   }

    @Override
    public float Revenue() throws SQLException {
        String req = "select sum(prix_total) from commande";
         try {
            st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs=st.executeQuery(req);
            rs.last();
            float tc = rs.getFloat(1);
            return tc;
  
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
    }
    }

    @Override
    public ResultSet totalCommandesParClient() throws SQLException {
        String req = "SELECT c.id,c.username,\n" +
            "count(co.REF) as Totalcommandes from commande co,client c \n" +
            "WHERE co.user_id=c.id\n" +
            "GROUP by c.id\n" +
            "ORDER by count(co.REF) DESC";
        
        try{
            st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs=st.executeQuery(req);
            return rs;
        }catch(SQLException e)
        {
            System.out.println(e.getMessage());
            
        }
        return null;
    }

  }
