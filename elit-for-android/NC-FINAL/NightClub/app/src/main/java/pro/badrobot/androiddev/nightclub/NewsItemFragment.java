package pro.badrobot.androiddev.nightclub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by androiddev on 03.08.15.
 */
public class NewsItemFragment  extends Fragment {

    ImageView mNewsImage;
    TextView mNewsDescription;
    TextView mNewsTitle;
    SingleNews mNews;
    MainActivity mMainActivity;
    String mTitle;

    public static String ARG_NEWS_KEY = "news";
    public static String ARG_TITLE_KEY = "title";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View eventItemView = inflater.inflate(R.layout.fragment_news_item,container,false);
        mNewsImage = (ImageView)eventItemView.findViewById(R.id.news_poster);
        mNewsDescription = (TextView)eventItemView.findViewById(R.id.news_description);
        mNewsTitle = (TextView)eventItemView.findViewById(R.id.news_title);

        mNewsDescription.setText(mNews.description);
        mNewsTitle.setText(mNews.title);

        mNewsImage.setOnClickListener(new OnImageClickListener());

        if(mNews.images != null & mNews.images.size() > 0)
            new ImageLoader(mNewsImage).execute(getResources().getString(R.string.uploads_folder_api) + mNews.images.get(0).path);
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
        mNews = (SingleNews)getArguments().getSerializable(ARG_NEWS_KEY);
        mTitle = getArguments().getString(ARG_TITLE_KEY);
        mMainActivity = (MainActivity)getActivity();
        mMainActivity.getSupportActionBar().setTitle(mNews.title);
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

