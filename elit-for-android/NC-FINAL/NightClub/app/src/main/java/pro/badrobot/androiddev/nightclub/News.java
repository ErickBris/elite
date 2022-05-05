package pro.badrobot.androiddev.nightclub;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by androiddev on 03.08.15.
 */
public class News implements Serializable {
    List<SingleNews> news;

    public News(){
        news = new ArrayList<SingleNews>();
    }
}
