/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.Service;


import shahba.utils.MyConnexion;
import shahba.entity.Typereclamation;
import shahba.IService.IserviceCrud;
import shahba.entity.Reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ahmed
 */
public class TypereclamationServices implements IserviceCrud<Typereclamation>{
Connection cnx = MyConnexion.getInstance().getCnx();

    
@Override
    public void add(Typereclamation t) {
         String req ="insert into typereclamation ( tyrc, comrc,ida, color) VALUES (?,?,?,?)";  
          try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getTyrc());
            ps.setString(2, t.getComrc());
            ps.setInt(3, t.getIda());
            ps.setString(4, t.getColor());
             ps.executeUpdate();
              System.out.println("type reclamation ajouté !");
          } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    @Override
    public void delete(Typereclamation t) {
             String req = "delete from typereclamation where id = ? ";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, t.getId());
            ps.executeUpdate();
            System.out.println("type reclamation supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }


    }

    @Override
    public void update(Typereclamation t) {

      String req ="update typereclamation set tyrc=?, comrc=?, color=? where id = ?";  
          try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getTyrc());
            ps.setString(2, t.getComrc());
            ps.setString(3, t.getColor());
            ps.setInt(4, t.getId());
             ps.executeUpdate();
              System.out.println("type reclamation modifié !");
          } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    @Override
    public List<Typereclamation> read() {
 List<Typereclamation> list = new ArrayList<>();
        String req = "select * from typereclamation";

        try {
            PreparedStatement ps = cnx.prepareStatement(req);

            ResultSet rs = ps.executeQuery(req);
            while (rs.next()) {
                list.add(new Typereclamation(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return list;
    

    }
    
}
