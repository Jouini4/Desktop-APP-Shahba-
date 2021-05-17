
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shahba.entity;
import entity.User;
/**
 *
 * @author Anis
 */
public class commande {
    
    private int ref;
    private User client;
    private float prixTotal;
    private String adresse;
    private String description_adresse;
    private String gouvernorat;
    private int codeP;
    private int tel;

    public commande(int ref, User client, float prixTotal, String adresse, String description_adresse, String gouvernorat, int codeP, int tel) {
        this.ref = ref;
        this.client = client;
        this.prixTotal = prixTotal;
        this.adresse = adresse;
        this.description_adresse = description_adresse;
        this.gouvernorat = gouvernorat;
        this.codeP = codeP;
        this.tel = tel;
    }

    public commande(User client, float prixTotal, String adresse, String description_adresse, String gouvernorat, int codeP, int tel) {
        this.client = client;
        this.prixTotal = prixTotal;
        this.adresse = adresse;
        this.description_adresse = description_adresse;
        this.gouvernorat = gouvernorat;
        this.codeP = codeP;
        this.tel = tel;
    }

    public commande() {
    }

    
    
    @Override
    public String toString() {
        return "commande{" + "ref=" + ref + ", client=" + client + ", prixTotal=" + prixTotal + ", adresse=" + adresse + ", description_adresse=" + description_adresse + ", gouvernorat=" + gouvernorat + ", codeP=" + codeP + ", tel=" + tel + '}';
    }

    
    
    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public float getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(float prixTotal) {
        this.prixTotal = prixTotal;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDescription_adresse() {
        return description_adresse;
    }

    public void setDescription_adresse(String description_adresse) {
        this.description_adresse = description_adresse;
    }

    public String getGouvernorat() {
        return gouvernorat;
    }

    public void setGouvernorat(String gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public int getCodeP() {
        return codeP;
    }

    public void setCodeP(int codeP) {
        this.codeP = codeP;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }
    
    
    
    
}
