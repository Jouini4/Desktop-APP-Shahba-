/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.Service;

import shahba.utils.MyConnexion;
import shahba.entity.Reclamation;
import entity.User;
import shahba.IService.IserviceCrud;
import shahba.entity.Typereclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ahmed
 */
public class ReclamationServices implements IserviceCrud<Reclamation>{
    Connection cnx = MyConnexion.getInstance().getCnx();

    public void add(Reclamation r , User us) {
        String req ="insert into reclamation ( typereclamation_id, user_id, commande_id, nomc, pnomc, mailc,numclient, etrc, obrc, desrec, screenshot, created_at, updated_at) values(?,?,?,?,?,?,?,?,?,?,?,now(),now())";
          

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, r.getTypereclamation_id());
            ps.setInt(2, us.getId());
            ps.setInt(3, r.getCommande_id());
            ps.setString(4, r.getNomc());
            ps.setString(5, r.getPnomc());
            ps.setString(6, r.getMailc());
            ps.setInt(7, r.getNumclient());
            ps.setString(8, r.getEtrc());
            ps.setString(9, r.getObrc());
            ps.setString(10, r.getDesrec());
            ps.setString(11, r.getScreenshot());

            ps.executeUpdate();
            System.out.println("reclamation ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }


    }

    @Override
    public void delete(Reclamation r) {
                String req = "delete from reclamation where id = ? ";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, r.getId());
            ps.executeUpdate();
            System.out.println("reclamation supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    @Override
    public void update(Reclamation r) {
         String req ="update reclamation set  typereclamation_id=?, commande_id=?, nomc=?, pnomc=?, mailc=?,numclient=?, obrc=?, desrec=?, screenshot=?, updated_at=now() where id=?";
             

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, r.getTypereclamation_id());
            ps.setInt(2, r.getCommande_id());
            ps.setString(3, r.getNomc());
            ps.setString(4, r.getPnomc());
            ps.setString(5, r.getMailc());
            ps.setInt(6, r.getNumclient());
            ps.setString(7, r.getObrc());
            ps.setString(8, r.getDesrec());
            ps.setString(9, r.getScreenshot());
            ps.setInt(10, r.getId());

            ps.executeUpdate();
            System.out.println("reclamation modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }
    
    public void updateetat(Reclamation r) {
         String req ="update reclamation set  etrc=? where id=?";
             

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, r.getEtrc());
            ps.setInt(2, r.getId());

            ps.executeUpdate();
            System.out.println("reclamation modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    @Override
    public List<Reclamation> read() {
        List<Reclamation> list = new ArrayList<>();
        String req = "select * from reclamation";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);

            ResultSet rs = ps.executeQuery(req);
            while (rs.next()) {
                Reclamation rec =new Reclamation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6)
                        , rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14));
                rec.setTypereclamation(ReclamationType(rec).get(0).toString());
                list.add(rec);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return list;
    

    }

    @Override
    public void add(Reclamation r) {
        String req ="insert into reclamation ( typereclamation_id, user_id, commande_id, nomc, pnomc, mailc,numclient, etrc, obrc, desrec, screenshot, created_at, updated_at) values(?,?,?,?,?,?,?,?,?,?,?,now(),now())";
          

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, r.getTypereclamation_id());
            ps.setInt(2, r.getUser_id());
            ps.setInt(3, r.getCommande_id());
            ps.setString(4, r.getNomc());
            ps.setString(5, r.getPnomc());
            ps.setString(6, r.getMailc());
            ps.setInt(7, r.getNumclient());
            ps.setString(8, r.getEtrc());
            ps.setString(9, r.getObrc());
            ps.setString(10, r.getDesrec());
            ps.setString(11, r.getScreenshot());

            ps.executeUpdate();
            System.out.println("reclamation ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }


    }
    public List<Reclamation> RechercheNom(String x) {
       
        List<Reclamation> list = new ArrayList<>();
       list = read();
            list = list.stream().filter(e -> e.getNomc().contains(x)).collect(Collectors.toList());
        
        return list;
          
    }
public List<Typereclamation> ReclamationType(Reclamation r){
         List<Typereclamation> list = new ArrayList<>();
         String req = "SELECT tr.* FROM typereclamation tr JOIN reclamation r ON tr.id = r.typereclamation_id  WHERE r.id = ? ";
           try {
            
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, r.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Typereclamation(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    
    return list;
}
     
    
}

