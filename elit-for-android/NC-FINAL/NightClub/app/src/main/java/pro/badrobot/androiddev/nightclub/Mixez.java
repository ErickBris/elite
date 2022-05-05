package pro.badrobot.androiddev.nightclub;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by androiddev on 30.07.15.
 */
public class Mixez implements Serializable{
    List<Mix> mixez;

    public Mixez()
    {
        mixez = new ArrayList<Mix>();
    }
}
