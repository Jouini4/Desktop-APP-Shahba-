/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.entity;

import java.util.Date;

/**
 *
 * @author isslem
 */
public class Astuce {
    
 private int id ;
    private String titre;
    private String description;
    private String image;

    public Astuce() {
    }

    public Astuce(String titre, String description, String image) {
        this.titre = titre;
        this.description = description;
        this.image = image;
    }

    public Astuce(int id, String titre, String description, String image) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Astuce{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", image=" + image + '}';
    }

    public int getQuantity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Date getDebut() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
   
   
