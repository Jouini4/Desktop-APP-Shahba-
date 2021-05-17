/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import Utils.Connexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.util.ArrayList;
import java.util.Date;
import Utils.BCrypt;




/**
 *
 * @author user
 */
public class Userdao implements Idao<User>{
    private static Userdao instance;
    private Statement st;
    private ResultSet rs;
    public Date datenow= new Date() ;
    
    public Userdao() {
        Connexion cs=Connexion.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Userdao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Userdao getInstance(){
        if(instance==null) 
            instance=new Userdao();
        return instance;
    }/**INSERT INTO `user` (`id`, `nom`, `prenom`, `email`, `mdp`, `date_de_naisssance`, `photo`, `telephone`, `portfeuille`, `fidalite`, `role`, `etat`, `date_de_creation`) VALUES (NULL, 'sscscsc', 'scxc', 'xcxcx', 'cxcxc', 'xcxcxcxcx', 'cxcxcxcxc', '45454', '', '', '', '', ''); **/

    /**
     * INSERT INTO `user` (`id`, `nom`, `prenom`, `email`, `mdp`, `date_de_naisssance`, `photo`, `telephone`, `portfeuille`, `fidalite`, `role`, `etat`, `date_de_creation`) VALUES (NULL, 'sscscsc', 'scxc', 'xcxcx', 'cxcxc', 'xcxcxcxcx', 'cxcxcxcxc', '45454', '', '', '', '', '');
     * @param o
     * 
     * INSERT INTO `user` (`id`, `nom`, `prenom`, `email`, `mdp`, `date_de_naisssance`, `photo`, `telephone`, `portfeuille`, `fidalite`, `role`, `etat`, `date_de_creation`) VALUES (NULL, 'fdfd', 'fdfdfd', 'fdffd', 'fdfdfd', 'fdfdf', 'fdf', 'fdfdf', '', '', '', '', '');
     */
    @Override
    public void insert(User o) {
        String req="INSERT INTO `client` (`email`, `password`, `username`, `userphone`, `useraddress`, `usercin`, `roles`) VALUES ('"+o.getEmail()+"', '"+o.getPassword()+"', '"+o.getUsername()+"', '"+o.getUserphone()+"', '"+o.getUseraddress()+"', '"+o.getUsercin()+"', '"+o.getRoles()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(Userdao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
     public void insert3(User o) {
        String req="INSERT INTO `admin` (`email`, `password`, `username`, `userphone`, `useraddress`, `usercin`, `roles`) VALUES ('"+o.getEmail()+"', '"+o.getPassword()+"', '"+o.getUsername()+"', '"+o.getUserphone()+"', '"+o.getUseraddress()+"', '"+o.getUsercin()+"', '"+o.getRoles()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(Userdao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
   
     

  @Override
    public void delete(User o) {
        String req="delete from client where id="+o.getId();
        User p=displayById(o.getId());
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(Userdao.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }
     public void delete2(User o) {
        String req="delete from admin where id="+o.getId();
        User p=displayById2(o.getId());
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(Userdao.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }

    @Override
    public ObservableList<User> displayAll() {
        String req="SELECT `id`,`email`,`password`,`username`,`userphone`,`useraddress`,`usercin` FROM `client`";
        ObservableList<User> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                User p=new User();
                p.setId(rs.getInt(1));
                p.setEmail(rs.getString(2));
                p.setPassword(rs.getString(3));
                p.setUsername(rs.getString(4));
                p.setUserphone(rs.getInt(5));
                p.setUseraddress(rs.getString(6));
                p.setUsercin(rs.getInt(7));
                
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Userdao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<User> displayAllList() {
        String req="SELECT `id`,`email`,`password`,`username`,`userphone`,`useraddress`,`usercin` FROM `client`";
        List<User> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                User p=new User();
                p.setId(rs.getInt(1));
                p.setEmail(rs.getString(2));
                p.setPassword(rs.getString(3));
                p.setUsername(rs.getString(4));
                p.setUserphone(rs.getInt(5));
                p.setUseraddress(rs.getString(6));
                p.setUsercin(rs.getInt(7));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Userdao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public User displayById(int id) {
           String req="SELECT `id`,`email`,`password`,`username`,`userphone`,`useraddress`,`usercin` FROM client where id ="+id;
           User p=new User();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setId(rs.getInt("id"));
                p.setId(rs.getInt(1));
                p.setEmail(rs.getString(2));
                p.setPassword(rs.getString(3));
                p.setUsername(rs.getString(4));
                p.setUserphone(rs.getInt(5));
                p.setUseraddress(rs.getString(6));
                p.setUsercin(rs.getInt(7));

            //}  
        } catch (SQLException ex) {
            Logger.getLogger(Userdao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }
     public User displayById2(int id) {
           String req="SELECT `id`,`email`,`password`,`username`,`userphone`,`useraddress`,`usercin` FROM admin where id ="+id;
           User p=new User();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setId(rs.getInt("id"));
                p.setId(rs.getInt(1));
                p.setEmail(rs.getString(2));
                p.setPassword(rs.getString(3));
                p.setUsername(rs.getString(4));
                p.setUserphone(rs.getInt(5));
                p.setUseraddress(rs.getString(6));
                p.setUsercin(rs.getInt(7));

            //}  
        } catch (SQLException ex) {
            Logger.getLogger(Userdao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }
    

    @Override
    public boolean update(User p) {
        String qry = "UPDATE client SET email = '"+p.getEmail()+"', password = '"+p.getPassword()+"', username = '"+p.getUsername()+"', userphone = '"+p.getUserphone()+"', useraddress = '"+p.getUseraddress()+"', usercin = '"+p.getUsercin()+"' WHERE id = "+p.getId();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Userdao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
     public boolean updateByemail(String email, String mdp) {
        String qry = "UPDATE client SET  password = '"+mdp+"' WHERE email = '"+email+"'";
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Userdao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
     public User VerifyUser(String email, String mdp) {
		User u = new User();
		Boolean found = false;
		
		String query = "select `id`,`email`,`password`,`username`,`userphone`,`useraddress`,`usercin` from client where email = '"+email+"' AND password = '"+mdp+"' Or username = '"+email+"' AND password = '"+mdp+"'";
		try {
			rs=st.executeQuery(query);
			if (rs.next()) {
                            
                            u.setId(rs.getInt(1));
                            u.setEmail(rs.getString(2));
                            u.setPassword(rs.getString(3));
                            u.setUsername(rs.getString(4));
			
			}
			return u;
		} catch (SQLException ex) {
                         System.out.println("erreur" + ex.getMessage());
            return null;
                }
		
	}
      public User VerifyUser1(String email, String mdp) {
		User u = new User();
		Boolean found = false;
		
		String query = "select `id`,`email`,`password`,`username`,`userphone`,`useraddress`,`usercin` from admin where email = '"+email+"' AND password = '"+mdp+"' Or username = '"+email+"' AND password = '"+mdp+"'";
		try {
			rs=st.executeQuery(query);
			if (rs.next()) {
                            
                            u.setId(rs.getInt(1));
                            u.setEmail(rs.getString(2));
                            u.setPassword(rs.getString(3));
                            u.setUsername(rs.getString(4));
			
			}
			return u;
		} catch (SQLException ex) {
                         System.out.println("erreur" + ex.getMessage());
            return null;
                }
		
	}
     
        public Boolean VerifyUserByEmail(String email) {
		User u = new User();
		//Boolean found = false;
		
		String query = "select `id`,`email`,`password`,`username`,`userphone`,`useraddress`,`usercin` from client where email = '" + email + "'";
		try {
			rs=st.executeQuery(query);
			if (rs.next()){ 
			return true;
                        }
		} catch (SQLException ex) {
                         System.out.println("erreur" + ex.getMessage());
                }
       return false;
	};
     
     public User UserById(int id) {
          
           String req="select `id`,`email`,`password`,`username`,`userphone`,`useraddress`,`usercin` from client where id ="+id;
           User p=new User();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setId(rs.getInt("id"));
                p.setId(rs.getInt(1));
                p.setEmail(rs.getString(2));
                p.setPassword(rs.getString(3));
                p.setUsername(rs.getString(4));
                p.setUserphone(rs.getInt(5));
                p.setUseraddress(rs.getString(6));
                p.setUsercin(rs.getInt(7));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(Userdao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
		
	}
      public User UserById1(int id) {
          
           String req="select `id`,`email`,`password`,`username`,`userphone`,`useraddress`,`usercin` from admin where id ="+id;
           User p=new User();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setId(rs.getInt("id"));
                p.setId(rs.getInt(1));
                p.setEmail(rs.getString(2));
                p.setPassword(rs.getString(3));
                p.setUsername(rs.getString(4));
                p.setUserphone(rs.getInt(5));
                p.setUseraddress(rs.getString(6));
                p.setUsercin(rs.getInt(7));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(Userdao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
		
	}
     
     
     public User UserByemail(String e) {
          
           String req="select `id`,`email`,`password`,`username`,`userphone`,`useraddress`,`usercin` from client where email ="+e;
           User p=new User();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setId(rs.getInt("id"));
                p.setId(rs.getInt(1));
                p.setEmail(rs.getString(2));
                p.setPassword(rs.getString(3));
                p.setUsername(rs.getString(4));
                p.setUserphone(rs.getInt(5));
                p.setUseraddress(rs.getString(6));
                p.setUsercin(rs.getInt(7));
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(Userdao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
     }

    @Override
    public void insert2(User o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
};

    

