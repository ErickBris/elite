package pro.badrobot.androiddev.nightclub;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by androiddev on 29.07.15.
 */
public class Event implements Serializable{
    String id, title, description, datatime, status, added;
    List<Image> images;

    public Event()
    {
        images = new ArrayList<Image>();
    }
}
