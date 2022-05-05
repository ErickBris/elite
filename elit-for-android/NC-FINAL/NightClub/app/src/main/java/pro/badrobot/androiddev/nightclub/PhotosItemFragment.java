package pro.badrobot.androiddev.nightclub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.StackView;
import android.widget.TextView;

/**
 * Created by androiddev on 04.08.15.
 */
public class PhotosItemFragment extends Fragment {
    public static final String ARG_PHOTO_KEY = "photo";
    public static final String ARG_TITLE_KEY = "title";

    StackView mPhotoStack;
    TextView mPhotoDescription;
    TextView mPhotoTitle;
    Photo mPhotos;
    MainActivity mMainActivity;
    String mTitle;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_photo_item,container,false);

        mPhotoStack = (StackView) v.findViewById(R.id.photo_stack);
        mPhotoDescription = (TextView) v.findViewById(R.id.photo_description);
        mPhotoTitle = (TextView) v.findViewById(R.id.photo_title);
        mPhotoStack.setAdapter(new PhotoStackAdapter(mMainActivity,mPhotos.images));

        mPhotoTitle.setText(mPhotos.title);
        mPhotoDescription.setText(mPhotos.description);
        return  v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mPhotos = (Photo)getArguments().getSerializable(ARG_PHOTO_KEY);
        mTitle = getArguments().getString(ARG_TITLE_KEY);
        mMainActivity = (MainActivity)getActivity();
        mMainActivity.getSupportActionBar().setTitle(mPhotos.title);
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

