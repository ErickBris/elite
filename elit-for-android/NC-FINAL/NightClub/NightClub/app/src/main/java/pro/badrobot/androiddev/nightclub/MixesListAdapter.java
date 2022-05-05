package pro.badrobot.androiddev.nightclub;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by androiddev on 30.07.15.
 */
public class MixesListAdapter extends ArrayAdapter<Mix>{
    Context mContext;
    List<Mix> mixes;
    TextView mMixTitle;
    Mix mMix;

    public MixesListAdapter(Context context, List<Mix> objects) {
        super(context, R.layout.mixes_item, objects);
        mixes = objects;
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        mMix = mixes.get(position);
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mixView = inflater.inflate(R.layout.mixes_item,parent,false);
        mMixTitle = (TextView) mixView.findViewById(R.id.mix_title);
        mMixTitle.setText(mMix.title);
        return mixView;
    }
}
