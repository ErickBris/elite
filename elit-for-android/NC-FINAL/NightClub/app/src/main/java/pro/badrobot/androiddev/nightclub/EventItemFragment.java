package pro.badrobot.androiddev.nightclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by androiddev on 30.07.15.
 */
public class EventItemFragment extends Fragment {

    ImageView mEventImage;
    TextView mEventDescription;
    Button mShareButton;
    Event mEvent;
    MainActivity mMainActivity;
    String mTitle;

    public static String ARG_EVENT_KEY = "event";
    public static String ARG_TITLE_KEY = "title";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View eventItemView = inflater.inflate(R.layout.fragment_event_item,container,false);
        mEventImage = (ImageView)eventItemView.findViewById(R.id.event_poster);
        mEventDescription = (TextView)eventItemView.findViewById(R.id.event_description);
        mShareButton = (Button)eventItemView.findViewById(R.id.button_share);

        mShareButton.setOnClickListener(new onShareButtonClickListener());
        mEventImage.setOnClickListener(new OnImageClickListener());

        mEventDescription.setText(mEvent.description);

        if(mEvent.images != null & mEvent.images.size() > 0)
            new ImageLoader(mEventImage).execute(getResources().getString(R.string.uploads_folder_api) + mEvent.images.get(0).path);
        return eventItemView;
    }

    private class OnImageClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            return;
        }
    }

    private class onShareButtonClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, mEvent.title.toUpperCase() + ". \n" + mEvent.description);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mEvent = (Event)getArguments().getSerializable(ARG_EVENT_KEY);
        mTitle = getArguments().getString(ARG_TITLE_KEY);
        mMainActivity = (MainActivity)getActivity();
        mMainActivity.getSupportActionBar().setTitle(mEvent.title);
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
