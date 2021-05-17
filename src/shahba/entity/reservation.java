/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.entity;

/**
 *
 * @author HAMMOUDA
 */
public class reservation {
         private int id ;
        private int nbrplace;
        private Boolean approuve ;
      
        private int id_Event ;
        private String user_id ;
        private String status ;
        private String nom_event ;

    public String getNom_event() {
        return nom_event;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public reservation() {
    }

    public reservation(int id) {
        this.id = id;
    }
    

   

    public reservation(int nbrplace, Boolean approuve) {
        this.nbrplace = nbrplace;
        this.approuve = approuve;
    }

    public String getUser_id() {
        return user_id;
    }

    public reservation(int id, int nbrplace, Boolean approuve, String user_id) {
        this.id = id;
        this.nbrplace = nbrplace;
        this.approuve = approuve;
        this.user_id = user_id;
    }
    

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public reservation(int id, int nbrplace, Boolean approuve, int id_Event, String user_id) {
        this.id = id;
        this.nbrplace = nbrplace;
        this.approuve = approuve;
        this.id_Event = id_Event;
        this.user_id = user_id;
    }

    public reservation(int nbrplace, Boolean approuve, int id_Event, String user_id) {
        this.nbrplace = nbrplace;
        this.approuve = approuve;
        this.id_Event = id_Event;
        this.user_id = user_id;
    }
        

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbrplace() {
        return nbrplace;
    }

    public void setNbrplace(int nbrplace) {
        this.nbrplace = nbrplace;
    }

    public int getId_event() {
        return id_Event;
    }

    public void setId_event(int id_event) {
        this.id_Event = id_event;
    }

    public Boolean getApprouve() {
        return approuve;
    }

    public void setApprouve(Boolean approuve) {
        this.approuve = approuve;
    }

  

    public reservation(int nbrplace, Boolean approuve , int id_event) {
        this.nbrplace = nbrplace;
        this.approuve = approuve;
        this.id_Event = id_event;
        
    }
    
        


}
