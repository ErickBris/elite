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
public class DjsListFragment extends Fragment {
    private String mLogTag = "DjsListFragment";
    private ListView mDjList;
    private List<Dj> mDjs;
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
        View fragmentView = inflater.inflate(R.layout.fragment_djs_list, container, false);
        mDjList = (ListView)fragmentView.findViewById(R.id.djs_list);
        mDjList.setOnItemClickListener(new onEventItemClickListener());
        service.getDjs(new Callback<Djs>() {


            @Override
            public void success(Djs djs, Response response) {
                if (djs != null && djs.djs != null) {
                    mDjs = djs.djs;
                    if (djs.djs.size() > 0) {
                        mDjList.setAdapter(new DjListAdapter(DjsListFragment.this.getActivity(), djs.djs));
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
            Fragment djItemFragment = new DjsItemFragment();
            Bundle args = new Bundle();
            args.putSerializable(DjsItemFragment.ARG_DJ_KEY,mDjs.get(position));
            args.putString(DjsItemFragment.ARG_TITLE_KEY,mMainActivity.getSupportActionBar().getTitle().toString());
            djItemFragment.setArguments(args);
            FragmentTransaction mFragmentTransaction = getFragmentManager().beginTransaction();
            mFragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            mFragmentTransaction.replace(R.id.djs_content_layout, djItemFragment);
            mFragmentTransaction.addToBackStack(null);
            mFragmentTransaction.commit();
        }
    }
}
