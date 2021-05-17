/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.IService;

import java.util.List;
import shahba.entity.Astuce;

/**
 *
 * @author isslem
 */
public interface IServiceAstuce {
     public void createAstuce(Astuce a);

    public List<Astuce> getAll();

    public void update(Astuce a,int id);

    public void delete(int id);

    
}
