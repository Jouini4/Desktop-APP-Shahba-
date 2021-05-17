/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.IService;

import shahba.entity.reservation;

import java.util.List;

/**
 *
 * @author HAMMOUDA
 */
public interface IReservationService {
    
        public void createReservation(reservation r, int id_Event , int nbPlace , int user_id );

        public List<reservation> getAll();
        
     public void approuveReservation(int id);
     

        public void delete(int id);
    
}
