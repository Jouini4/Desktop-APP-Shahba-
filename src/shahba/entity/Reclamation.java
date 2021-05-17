/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.entity;
import java.sql.Date;
 /**
 *
 * @author ahmed
 */ 
public class Reclamation {
    private int id ;
    
    private int typereclamation_id  ;
    
    private int user_id ;
    
    private int commande_id ;
    
    private String nomc ;
    
    private String pnomc ;
    
    private String mailc ;
    
    private int numclient ;
    
    private String etrc ;
    
    private String obrc ;
    
    private String desrec ;
    
    private String screenshot ;
    
    private  String created_at ;
    private String updated_at; 
    private String Typereclamation ;

    public Reclamation(int id, int typereclamation_id, int user_id, int commande_id, String nomc, String pnomc, String mailc, int numclient, String etrc, String obrc, String desrec, String screenshot, String created_at, String updated_at) {
        this.id = id;
        this.typereclamation_id = typereclamation_id;
        this.user_id = user_id;
        this.commande_id = commande_id;
        this.nomc = nomc;
        this.pnomc = pnomc;
        this.mailc = mailc;
        this.numclient = numclient;
        this.etrc = etrc;
        this.obrc = obrc;
        this.desrec = desrec;
        this.screenshot = screenshot;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
    public Reclamation(int aInt, int aInt0, int aInt1, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Reclamation(int aInt, int aInt0, int aInt1, String string, String string0, String string1, int aInt2, String string2, String string3, String string4, String string5) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypereclamation_id() {
        return typereclamation_id;
    }

    public void setTypereclamation_id(int typereclamation_id) {
        this.typereclamation_id = typereclamation_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCommande_id() {
        return commande_id;
    }

    public void setCommande_id(int commande_id) {
        this.commande_id = commande_id;
    }

    public String getNomc() {
        return nomc;
    }

    public void setNomc(String nomc) {
        this.nomc = nomc;
    }

    public String getPnomc() {
        return pnomc;
    }

    public void setPnomc(String pnomc) {
        this.pnomc = pnomc;
    }

    public String getMailc() {
        return mailc;
    }

    public void setMailc(String mailc) {
        this.mailc = mailc;
    }

    public int getNumclient() {
        return numclient;
    }

    public void setNumclient(int numclient) {
        this.numclient = numclient;
    }

    public String getEtrc() {
        return etrc;
    }

    public void setEtrc(String etrc) {
        this.etrc = etrc;
    }

    public String getObrc() {
        return obrc;
    }

    public void setObrc(String obrc) {
        this.obrc = obrc;
    }

    public String getDesrec() {
        return desrec;
    }

    public void setDesrec(String desrec) {
        this.desrec = desrec;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getTypereclamation() {
        return Typereclamation;
    }

    public void setTypereclamation(String Typereclamation) {
        this.Typereclamation = Typereclamation;
    }

    @Override
    public String toString() {
        return "reclamation{" + " typereclamation_id=" + typereclamation_id
                + ", user_id=" + user_id + ", commande_id=" + commande_id + ", nomc=" + nomc 
                + ", pnomc=" + pnomc + ", mailc=" + mailc + ", numclient=" + numclient
                + ", etrc=" + etrc + ", obrc=" + obrc + ", desrec=" + desrec 
                + ", screenshot=" + screenshot + ", created_at=" + created_at + ", updated_at=" + updated_at + "} \n";
    }

        
    
    
    
    
    
    
}
