/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author user
 */
public class User {
    
    private int id;
    private String username ;
    private String email;
    private String password;
    private int userphone;
    private int usercin;
    private String useraddress;
    private String roles;

    public User() {
    }

    public User(String username, String email, String password, int userphone, int usercin, String useraddress) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.userphone = userphone;
        this.usercin = usercin;
        this.useraddress = useraddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserphone() {
        return userphone;
    }

    public void setUserphone(int userphone) {
        this.userphone = userphone;
    }

    public int getUsercin() {
        return usercin;
    }

    public void setUsercin(int usercin) {
        this.usercin = usercin;
    }

    public String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public User(int id, String username, String email, String password, int userphone, int usercin, String useraddress, String roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userphone = userphone;
        this.usercin = usercin;
        this.useraddress = useraddress;
        this.roles = roles;
    }

    public User(int id, String username, String email, String password, int userphone, int usercin, String useraddress) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userphone = userphone;
        this.usercin = usercin;
        this.useraddress = useraddress;
    }

    public User(String username, String email, String password, int userphone, int usercin, String useraddress, String roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.userphone = userphone;
        this.usercin = usercin;
        this.useraddress = useraddress;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", userphone=" + userphone + ", usercin=" + usercin + ", useraddress=" + useraddress + ", roles=" + roles + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

   

    

   
    
   
   
}
  