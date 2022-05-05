package pro.badrobot.androiddev.nightclub;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by androiddev on 03.08.15.
 */
public class Dj implements Serializable {
    String id, title, description, status, added;
    List<Image> images;

    public Dj()
    {
        images = new ArrayList<Image>();
    }
}
