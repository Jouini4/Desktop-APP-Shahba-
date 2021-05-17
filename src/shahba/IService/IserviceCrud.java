  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.IService;

import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface IserviceCrud<class_name> {
    public void add(class_name c);
    public void delete(class_name c);
    public void update(class_name c);
    public List<class_name> read();
 
    
   
    
    
    
    
    
}
