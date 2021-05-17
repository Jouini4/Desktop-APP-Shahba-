/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 package shahba.UI.front;
/**
 *
 * @author isslem
 */

  

import javafx.scene.input.MouseEvent;
import shahba.entity.evenement;
import shahba.entity.Astuce;
import shahba.entity.Reclamation;
import shahba.entity.Video;

public interface MyListener {
    public void onClickListener(evenement evenement);
public void onClickListener(Astuce Astuce,MouseEvent event);

    public void onClickListener(Video video, MouseEvent event);
     public void onClickListener(Reclamation reclamation,MouseEvent event);
}
