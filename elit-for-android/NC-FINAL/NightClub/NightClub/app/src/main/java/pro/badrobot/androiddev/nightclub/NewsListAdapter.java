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
public class NewsListAdapter extends ArrayAdapter<SingleNews> {
    private final List<SingleNews> news;
    private final Context context;

    public NewsListAdapter(Context context, List<SingleNews> news) {
        super(context, R.layout.news_item, news);
        this.news = news;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.news_item,parent, false);
        ImageView newsPoster = (ImageView) item.findViewById(R.id.news_poster);
        TextView newsTitle = (TextView) item.findViewById(R.id.news_title);
        newsTitle.setText(news.get(position).title);
        if(news.get(position).images.size() > 0) {
            new ImageLoader(newsPoster).execute(context.getResources().getString(R.string.uploads_folder_api) + news.get(position).images.get(0).path);
        }
        return item;
    }
}