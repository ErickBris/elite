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
 * Created by androiddev on 29.07.15.
 */
@SuppressWarnings("ALL")
public class EventListFragment extends Fragment {

    private String mLogTag = "EventListFragment";
    private ListView mEventList;
    private List<Event> mEvents;
    private String mTitle;
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
        View fragmentView = inflater.inflate(R.layout.fragment_event_list, container, false);
        mEventList = (ListView)fragmentView.findViewById(R.id.event_list);
        mEventList.setOnItemClickListener(new onEventItemClickListener());
        service.getEvents(new Callback<Events>() {


            @Override
            public void success(Events events, Response response) {
                if (events != null && events.event != null) {
                    mEvents = events.event;
                    if (events.event.size() > 0) {
                        mEventList.setAdapter(new EventListAdapter(EventListFragment.this.getActivity(), events.event));
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

    private class onEventItemClickListener implements ListView.OnItemClickListener
    {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Fragment eventItemFragment = new EventItemFragment();
            Bundle args = new Bundle();
            args.putSerializable(EventItemFragment.ARG_EVENT_KEY,mEvents.get(position));
            args.putString(EventItemFragment.ARG_TITLE_KEY,mMainActivity.getSupportActionBar().getTitle().toString());
            eventItemFragment.setArguments(args);
            FragmentTransaction mFragmentTransaction = getFragmentManager().beginTransaction();
            mFragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            mFragmentTransaction.replace(R.id.event_content_layout, eventItemFragment);
            mFragmentTransaction.addToBackStack(null);
            mFragmentTransaction.commit();
        }
    }
}
