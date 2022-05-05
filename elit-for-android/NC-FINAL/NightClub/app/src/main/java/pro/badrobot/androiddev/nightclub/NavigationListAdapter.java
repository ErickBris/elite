package pro.badrobot.androiddev.nightclub;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

/**
 * Created by androiddev on 04.08.15.
 */
public class NavigationListAdapter extends ArrayAdapter<String>{

    private Context mContext;
    private List<String> mValues;

    public NavigationListAdapter(Context context, List<String> values) {
        super(context, R.layout.navigation_item, values);
        mContext = context;
        mValues = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.navigation_item, parent, false);
        ImageView mIcon = (ImageView) v.findViewById(R.id.icon);
        ImageView mLine = (ImageView) v.findViewById(R.id.item_line);
        TextView mText = (TextView) v.findViewById(R.id.item_text);
        Random rnd = new Random();

        Log.d("getView","there");
        mLine.setBackgroundColor(Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));

        switch (mValues.get(position))
        {
            case "events":
                mIcon.setImageResource(R.drawable.ic_action_event);
                break;
            case "mixes":
                mIcon.setImageResource(R.drawable.ic_av_queue_music);
                break;
            case "dj's":
                mIcon.setImageResource(R.drawable.ic_av_dj);
                break;
            case "news":
                mIcon.setImageResource(R.drawable.ic_action_news);
                break;
            case "photo":
                mIcon.setImageResource(R.drawable.ic_image_photo_camera);
                break;
            case "about":
                mIcon.setImageResource(R.drawable.ic_action_info_outline);
                break;
        }

        mText.setText(mValues.get(position));
        return v;
    }
}
