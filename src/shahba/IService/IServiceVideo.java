/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shahba.IService;

import java.util.List;
import shahba.entity.Video;

/**
 *
 * @author isslem
 */
public interface IServiceVideo {
     public void createVideo(Video v);

    public List<Video> getAll();

    public void update(Video v,int id);

    public void delete(int id);

    
}
