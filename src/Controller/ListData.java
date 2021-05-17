/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entity.User;
import entity.Userdao;




/**
 *
 * @author wiemhjiri
 */
public class ListData {
    
     /**
     * The data as an observable list of Persons.
     */
    
    private ObservableList<User> users=FXCollections.observableArrayList();

    public ListData() {
        
        Userdao pdao=Userdao.getInstance();
        users= pdao.displayAll();
        System.out.println(users);
    }
    
    public ObservableList<User> getPersons(){
        return users;
    }
   
}
