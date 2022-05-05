package pro.badrobot.androiddev.nightclub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by androiddev on 03.08.15.
 */
public class DjListAdapter extends ArrayAdapter<Dj> {
    private final List<Dj> djs;
    private final Context context;

    public DjListAdapter(Context context, List<Dj> djs) {
        super(context, R.layout.djs_item, djs);
        this.djs = djs;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.djs_item,parent, false);
        ImageView eventPoster = (ImageView) item.findViewById(R.id.dj_poster);
        TextView eventName = (TextView) item.findViewById(R.id.dj_name_text);
        eventName.setText(djs.get(position).title);
        if(djs.get(position).images.size() > 0) {
            new ImageLoader(eventPoster).execute(context.getResources().getString(R.string.uploads_folder_api) + djs.get(position).images.get(0).path);
        }
        return item;
    }
}
