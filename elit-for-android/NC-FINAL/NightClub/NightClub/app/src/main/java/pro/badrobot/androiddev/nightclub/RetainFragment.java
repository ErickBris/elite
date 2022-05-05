package pro.badrobot.androiddev.nightclub;

import android.os.Bundle;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by androiddev on 30.07.15.
 */
public class RetainFragment extends android.support.v4.app.Fragment {

    OkHttpClient mHttpClient;
    RestAdapter mRestAdapter;
    NightClubService API;

    public static final String RETAIN_FRAGMENT_TAG = "retain_fragment";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mHttpClient = new OkHttpClient();
        mRestAdapter = new RestAdapter.Builder().setEndpoint(getResources().getString(R.string.endpoint_api)).setClient(new OkClient(mHttpClient)).build();
        API = mRestAdapter.create(NightClubService.class);
    }

    public NightClubService getAPI()
    {
        return API;
    }

}
