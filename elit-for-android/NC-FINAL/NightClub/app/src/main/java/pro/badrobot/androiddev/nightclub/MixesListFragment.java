package pro.badrobot.androiddev.nightclub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by androiddev on 30.07.15.
 */
public class MixesListFragment extends Fragment {

    private ListView mMixesList;
    private RetainFragment mRetainFragment;
    private Mixez mMixez;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity mMainActivity = (MainActivity) getActivity();
        mRetainFragment = mMainActivity.getRetainFragment();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mixesContentView = inflater.inflate(R.layout.fragment_mixes_list, container, false);
        mMixesList = (ListView)mixesContentView.findViewById(R.id.mixes_list);
        mMixesList.setOnItemClickListener(new onMixesItemClickListener());
        mRetainFragment.getAPI().getMixez(new Callback<Mixez>() {
            @Override
            public void success(Mixez mixez, Response response) {
                mMixez = mixez;
                mMixesList.setAdapter(new MixesListAdapter(getActivity(), mMixez.mixez));
                Log.d("LoadMixez", String.valueOf(mMixez.mixez.get(0).fileurl));
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("LoadMixez", "error");
            }
        });

        return mixesContentView;
    }

    private class onMixesItemClickListener implements ListView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MixesItemFragment mixesItemFragment = new MixesItemFragment();
            Bundle args = new Bundle();
            args.putSerializable(MixesItemFragment.ARG_MIX, mMixez.mixez.get(position));
            args.putSerializable(MixesItemFragment.ARG_MIXEZ, mMixez);
            args.putInt(MixesItemFragment.ARG_POSITION,position);
            mixesItemFragment.setArguments(args);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.mixes_content_layout, mixesItemFragment);
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}
