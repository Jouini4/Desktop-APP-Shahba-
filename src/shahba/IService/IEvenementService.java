/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.IService;

import shahba.entity.evenement;
import java.util.List;

/**
 *
 * @author HAMMOUDA
 */
public interface IEvenementService {
     public void createEvenement(evenement e);

    public List<evenement> getAll();

    public void update(evenement e,int id);

    public void delete(int id);
    
}
