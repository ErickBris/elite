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
 * Created by androiddev on 04.08.15.
 */
public class PhotoListFragment extends Fragment {
    private ListView mPhotoList;
    private List<Photo> mPhotos;
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
        View fragmentView = inflater.inflate(R.layout.fragment_photo_list, container, false);
        mPhotoList = (ListView)fragmentView.findViewById(R.id.photo_list);
        mPhotoList.setOnItemClickListener(new onPhotoItemClickListener());
        String mLogTag = "PhotosListFragment";
        Log.d(mLogTag,"create view");
        service.getPhotos(new Callback<Photos>() {
            @Override
            public void success(Photos photos, Response response) {
                Log.d("success", photos.photo.toString());
                if (photos != null && photos.photo != null) {
                    mPhotos = photos.photo;
                    if (photos.photo.size() > 0) {
                        mPhotoList.setAdapter(new PhotoListAdapter(PhotoListFragment.this.getActivity(), photos.photo));
                        Log.d("photoSize", String.valueOf(photos.photo.size()));
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

    private class onPhotoItemClickListener implements ListView.OnItemClickListener
    {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Fragment photoItemFragment = new PhotosItemFragment();
            Bundle args = new Bundle();
            args.putSerializable(PhotosItemFragment.ARG_PHOTO_KEY,mPhotos.get(position));
            args.putString(PhotosItemFragment.ARG_TITLE_KEY,mMainActivity.getSupportActionBar().getTitle().toString());
            photoItemFragment.setArguments(args);
            FragmentTransaction mFragmentTransaction = getFragmentManager().beginTransaction();
            mFragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            mFragmentTransaction.replace(R.id.photo_content_layout, photoItemFragment);
            mFragmentTransaction.addToBackStack(null);
            mFragmentTransaction.commit();
        }
    }
}