/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.entity;

/**
 *
 * @author isslem
 */
public class Video {
     private int id ;
    private String titre ;
    private String description ; 
    private String url ; 

    public Video() {
    }

    public Video(String titre, String description, String url) {
        this.titre = titre;
        this.description = description;
        this.url = url;
    }

    public Video(int id, String titre, String description, String url) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.url = url;
    }

    public Video(String toString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Video{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", url=" + url + '}';
    }
    
    
    
}
