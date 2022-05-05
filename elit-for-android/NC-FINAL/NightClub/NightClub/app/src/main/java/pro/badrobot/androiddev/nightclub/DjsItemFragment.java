package pro.badrobot.androiddev.nightclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by androiddev on 03.08.15.
 */
public class DjsItemFragment extends Fragment {

    ImageView mDjImage;
    TextView mDjDescription;
    Dj mDj;
    MainActivity mMainActivity;
    String mTitle;

    public static String ARG_DJ_KEY = "dj";
    public static String ARG_TITLE_KEY = "title";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View eventItemView = inflater.inflate(R.layout.fragment_djs_item,container,false);
        mDjImage = (ImageView)eventItemView.findViewById(R.id.dj_poster);
        mDjDescription = (TextView)eventItemView.findViewById(R.id.dj_description);

        mDjDescription.setText(mDj.description);

        mDjImage.setOnClickListener(new OnImageClickListener());

        if(mDj.images != null & mDj.images.size() > 0)
            new ImageLoader(mDjImage).execute(getResources().getString(R.string.uploads_folder_api) + mDj.images.get(0).path);
        return eventItemView;
    }

    private class OnImageClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            return;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mDj = (Dj)getArguments().getSerializable(ARG_DJ_KEY);
        mTitle = getArguments().getString(ARG_TITLE_KEY);
        mMainActivity = (MainActivity)getActivity();
        mMainActivity.getSupportActionBar().setTitle(mDj.title);
        mMainActivity.getActionBarDrawerToggle().setDrawerIndicatorEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                mMainActivity.getSupportActionBar().setTitle(mTitle);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
