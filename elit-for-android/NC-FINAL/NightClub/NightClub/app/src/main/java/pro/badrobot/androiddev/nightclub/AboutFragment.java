package pro.badrobot.androiddev.nightclub;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by androiddev on 04.08.15.
 */
public class AboutFragment extends Fragment {

    private MainActivity mMainActivity;
    private NightClubService mNightClubService;

    private TextView mPhone, mAddress, mDescription;
    private ImageButton mCallButton;
    private About mAbout;

    private OnNavigationClickListener mOnNavigationClickListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainActivity = (MainActivity) getActivity();
        RetainFragment retainFragment = (RetainFragment) mMainActivity.getSupportFragmentManager().findFragmentByTag(RetainFragment.RETAIN_FRAGMENT_TAG);
        if (retainFragment == null)
            return;
        mOnNavigationClickListener = new OnNavigationClickListener();
        mNightClubService = retainFragment.getAPI();
    }

    private class OnNavigationClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.map_button:
                    Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4194");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mMainActivity.startActivity(mapIntent);
                    break;
                case R.id.call_button:
                    if(mAbout == null) return;
                    Uri callUri = Uri.parse("tel:" + mAbout.phone);
                    Intent callIntent = new Intent(Intent.ACTION_VIEW, callUri);
                    mMainActivity.startActivity(callIntent);
                    break;
                case R.id.email_button:
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","abc@gmail.com",null));
                    mMainActivity.startActivity(Intent.createChooser(emailIntent,"Send email..."));
                    break;
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_about, container, false);
        mPhone = (TextView) v.findViewById(R.id.phone_text);
        mAddress = (TextView) v.findViewById(R.id.address_text);
        mDescription = (TextView) v.findViewById(R.id.about_description);
        ImageButton mMapButton = (ImageButton) v.findViewById(R.id.map_button);
        mCallButton = (ImageButton) v.findViewById(R.id.call_button);
        ImageButton mEmailButton = (ImageButton) v.findViewById(R.id.email_button);

        mMapButton.setOnClickListener(mOnNavigationClickListener);
        mEmailButton.setOnClickListener(mOnNavigationClickListener);

        mNightClubService.getAbout(new Callback<About>(){

            @Override
            public void success(About about, Response response) {
                if(about == null) return;
                mAbout = about;
                mDescription.setText(about.description);
                mPhone.setText(about.phone);
                mAddress.setText(about.adres);
                mCallButton.setOnClickListener(mOnNavigationClickListener);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

        return v;
    }
}
