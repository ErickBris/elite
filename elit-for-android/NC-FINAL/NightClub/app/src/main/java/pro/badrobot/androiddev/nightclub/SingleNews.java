package pro.badrobot.androiddev.nightclub;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by androiddev on 03.08.15.
 */
public class SingleNews implements Serializable {
    String id, title, description, status, added;
    List<Image> images;

    public SingleNews()
    {
        images = new ArrayList<Image>();
    }
}
