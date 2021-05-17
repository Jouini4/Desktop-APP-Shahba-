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
import java.util.ArrayList;
import java.util.List;
import shahba.IService.IServiceLivraison;
import shahba.entity.livraison;
import shahba.utils.MyConnexion;

/**
 *
 * @author Anis
 */
public class ServiceLivraison implements IServiceLivraison<livraison>{

    private Connection con;
    private Statement st;
    private ResultSet rs ;
    private PreparedStatement pst ;
    private ServiceCommande sc = new ServiceCommande();

    public ServiceLivraison() {
        con=MyConnexion.getInstance().getCnx();
    }
    
    
    @Override
    public void ajouter(livraison l) throws SQLException {
                pst = con.prepareStatement ("INSERT INTO livraison (commande_id,statut) VALUES (?,?) ");
                pst.setInt(1,l.getCommande().getRef());
                pst.setString(2, l.getStatut());
                pst.executeUpdate();
    }

    @Override
    public void modifier(livraison l) throws SQLException {
                pst = con.prepareStatement ("UPDATE livraison SET statut=? WHERE  numero=? ");
                pst.setString(1, l.getStatut());
                pst.setInt(2,l.getNum());
                
                pst.executeUpdate();

    }

    @Override
    public void supprimer(livraison l) throws SQLException {
        pst = con.prepareStatement ("DELETE FROM livraison WHERE numero=?");
        pst.setInt(1, l.getNum());
        pst.executeUpdate();

    }

    @Override
    public List<livraison> afficher() throws SQLException {
        List<livraison> l = new ArrayList<>();
        st=con.createStatement();
        rs = st.executeQuery("select * from livraison");
        
        while(rs.next()){
            int num =rs.getInt(1);
            int commande_id = rs.getInt(2);
            String statut = rs.getString(3);
            livraison li = new livraison(num, sc.recherche(commande_id), statut);
            l.add(li);
        }
        return l;
    }
    
}
