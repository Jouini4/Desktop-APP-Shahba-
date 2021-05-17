/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.IService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Anis
 */
public interface IServiceCommande<T> {
    
    void ajouter(T t) throws SQLException;
    
    void modifier(T t) throws SQLException;
    
    boolean supprimer(T t) throws SQLException;
    
    List<T>afficher() throws SQLException;
    
    T recherche(int id) throws SQLException;
    
    T recherche_client(int id) throws SQLException;
    
    int totalCommandes() throws SQLException;
    
    float Revenue() throws SQLException;
    
    ResultSet totalCommandesParClient() throws SQLException;
    
    
    
    
}
