/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.entity;

/**
 *
 * @author ahmed
 */
public class Typereclamation {
    private int id;
    private String tyrc;
    private String comrc;
    private int ida; 
    private String color;
    

    public Typereclamation(int id, String tyrc, String comrc, int ida, String color) {
        this.id = id;
        this.tyrc = tyrc;
        this.comrc = comrc;
        this.ida = ida;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTyrc() {
        return tyrc;
    }

    public void setTyrc(String tyrc) {
        this.tyrc = tyrc;
    }

    public String getComrc() {
        return comrc;
    }

    public void setComrc(String comrc) {
        this.comrc = comrc;
    }

    public int getIda() {
        return ida;
    }

    public void setIda(int ida) {
        this.ida = ida;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return  tyrc ;
    }
    
}
