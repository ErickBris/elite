package pro.badrobot.androiddev.nightclub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by androiddev on 11.08.15.
 */
public class PhotoStackAdapter extends ArrayAdapter<Image> {
    List<Image> objects;
    Context context;

    public PhotoStackAdapter(Context context, List<Image> objects) {
        super(context, R.layout.photo_stack_item, objects);
        this.context = context;
        this.objects = objects;
    }

    static class ViewHolder {
        ImageView photo;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.photo_stack_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.photo = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(context.getResources().getString(R.string.uploads_folder_api) + objects.get(position).path).into(viewHolder.photo);
        return convertView;
    }
}
