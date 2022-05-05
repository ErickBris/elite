package pro.badrobot.androiddev.nightclub;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by androiddev on 04.08.15.
 */
public class Photos implements Serializable
{
    List<Photo> photo;

    public Photos(){
        photo = new ArrayList<Photo>();
    }
}
