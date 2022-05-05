package pro.badrobot.androiddev.nightclub;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by androiddev on 29.07.15.
 */
public class EventListAdapter extends ArrayAdapter<Event> {
    private final List<Event> events;
    private final Context context;

    public EventListAdapter(Context context, List<Event> events) {
        super(context, R.layout.event_item, events);
        this.events = events;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.event_item,parent, false);
        ImageView eventPoster = (ImageView) item.findViewById(R.id.event_poster);
        TextView eventName = (TextView) item.findViewById(R.id.event_name_text);
        TextView eventDate = (TextView) item.findViewById(R.id.event_date_text);
        eventName.setText(events.get(position).title);
        eventDate.setText(events.get(position).datatime);
        if(events.get(position).images.size() > 0) {
            new ImageLoader(eventPoster).execute(context.getResources().getString(R.string.uploads_folder_api) + events.get(position).images.get(0).path);
        }
        return item;
    }
}
