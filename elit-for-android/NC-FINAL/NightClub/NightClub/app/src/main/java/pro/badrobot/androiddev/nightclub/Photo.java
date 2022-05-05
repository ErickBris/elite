package pro.badrobot.androiddev.nightclub;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by androiddev on 03.08.15.
 */
public class Photo implements Serializable {
    String id, title, description, status, added;
    List<Image> images;

    public Photo()
    {
        images = new ArrayList<Image>();
    }
}
