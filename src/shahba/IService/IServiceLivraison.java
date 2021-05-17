/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.IService;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Anis
 */
public interface IServiceLivraison<T> {
    
    void ajouter(T l) throws SQLException;
    
    void modifier(T l) throws SQLException;
    
    void supprimer(T l) throws SQLException;
    
    List<T>afficher() throws SQLException;
    
    
}
