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
 * Created by androiddev on 04.08.15.
 */
public class PhotoListAdapter extends ArrayAdapter<Photo>{
    TextView mPhotoTitle;
    ImageView mPhotoPoster;
    Context mContext;
    List<Photo> mValues;

    public PhotoListAdapter(Context context, List<Photo> values) {
        super(context, R.layout.photo_item, values);
        this.mValues = values;
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.photo_item,parent,false);
        mPhotoTitle = (TextView) v.findViewById(R.id.photo_title);
        mPhotoPoster = (ImageView)v.findViewById(R.id.photo_poster);

        mPhotoTitle.setText(mValues.get(position).title);

        if(mValues.get(position).images.size() > 0) {
            new ImageLoader(mPhotoPoster).execute(mContext.getResources().getString(R.string.uploads_folder_api) + mValues.get(position).images.get(0).path);
        }

        return v;
    }
}
