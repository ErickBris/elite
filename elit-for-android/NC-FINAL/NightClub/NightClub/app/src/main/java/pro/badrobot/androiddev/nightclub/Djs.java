package pro.badrobot.androiddev.nightclub;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by androiddev on 03.08.15.
 */
public class Djs implements Serializable{
    List<Dj> djs;

    public Djs(){
        djs = new ArrayList<Dj>();
    }
}
