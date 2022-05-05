package pro.badrobot.androiddev.nightclub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.squareup.okhttp.OkHttpClient;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

/**
 * Created by androiddev on 03.08.15.
 */
public class NewsListFragment extends Fragment {
    private ListView mNewsList;
    private List<SingleNews> mNews;

    private MainActivity mMainActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainActivity = (MainActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        OkHttpClient mHttpClient = new OkHttpClient();
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(getResources().getString(R.string.endpoint_api)).setClient(new OkClient(mHttpClient)).build();
        NightClubService service = restAdapter.create(NightClubService.class);
        View fragmentView = inflater.inflate(R.layout.fragment_news_list, container, false);
        mNewsList = (ListView)fragmentView.findViewById(R.id.news_list);

        mNewsList.setOnItemClickListener(new onNewsItemClickListener());
        service.getNews(new Callback<News>() {

            @Override
            public void success(News news, Response response) {
                if (news != null && news.news != null) {
                    mNews = news.news;
                    if (news.news.size() > 0) {
                        mNewsList.setAdapter(new NewsListAdapter(NewsListFragment.this.getActivity(), news.news));
                    }
                } else
                    Log.d("success", "null");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("failure", error.toString());
            }
        });
        return fragmentView;
    }

    private class onNewsItemClickListener implements ListView.OnItemClickListener
    {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Fragment newsItemFragment = new NewsItemFragment();
            Bundle args = new Bundle();
            args.putSerializable(NewsItemFragment.ARG_NEWS_KEY,mNews.get(position));
            args.putString(NewsItemFragment.ARG_TITLE_KEY,mMainActivity.getSupportActionBar().getTitle().toString());
            newsItemFragment.setArguments(args);
            FragmentTransaction mFragmentTransaction = getFragmentManager().beginTransaction();
            mFragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            mFragmentTransaction.replace(R.id.news_content_layout, newsItemFragment);
            mFragmentTransaction.addToBackStack(null);
            mFragmentTransaction.commit();
        }
    }
}