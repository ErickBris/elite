package pro.badrobot.androiddev.nightclub;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.sql.Time;

/**
 * Created by androiddev on 31.07.15.
 */
public class MixesItemFragment extends Fragment implements View.OnClickListener{

    public static final String ARG_MIX = "mix";
    public static final String ARG_MIXEZ = "mixez";
    public static final String ARG_POSITION = "position";
    public static final String ARG_CURRENT_POSITION = "current_position";
    public static final String ARG_DURATION = "duration";

    public static final String ARG_MIX_TITLE = "mix_title";

    private MainActivity mMainActivity;

    private ImageButton mPlayPauseButton;
    private TextView mMixTitle;
    private TextView mDurationText;
    private TextView mCurrentPositionText;
    private SeekBar mMixProgress;
    private ImageView mImgMixPoster;

    private boolean mMixIsPaused;

    private Intent mPlayerServiceIntent;
    private final Messenger mMessenger = new Messenger(new IncomingHandler());
    private Messenger mService = null;
    private boolean mIsBound;

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            default:
                break;
        }
    }

    private class IncomingHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what)
            {
                case MixPlayService.MSG_PAUSE:
                    mMixIsPaused = true;
                    mPlayPauseButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_av_play_arrow));
                    break;
                case MixPlayService.MSG_PLAY:
                    Bundle data = msg.getData();
                    if(data != null)
                    {
                        String mixTitle = data.getString(ARG_MIX_TITLE);
                        int duration = data.getInt(ARG_DURATION,-1);
                        Log.d("receiveDuration",String.valueOf(duration));
                        if(duration > 0)
                        {
                            String durationString = DateUtils.formatElapsedTime(duration/1000);
                            mDurationText.setText(durationString);
                            if(duration != mMixProgress.getMax())
                                mMixProgress.setMax(duration);
                        }
                        if(mixTitle == null) {
                            mMixTitle.setText("Unknown title");
                        }
                        else {
                            mMixTitle.setText(mixTitle);
                        }
                    }
                    mMixIsPaused = false;
                    mPlayPauseButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_av_pause));
                    break;
                case MixPlayService.MSG_SEND_CURRENT_POSITION:
                    Log.d("receiveCurrentPosition","begin");

                    int currentPosition = msg.getData().getInt(ARG_CURRENT_POSITION,-1);
                    Log.d("receiveCurrentPosition",String.valueOf(currentPosition));
                    if(currentPosition > 0)
                    {
                        String currentPositionString = DateUtils.formatElapsedTime(currentPosition/1000);
                        mCurrentPositionText.setText(currentPositionString);
                        mMixProgress.setProgress(currentPosition);
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = new Messenger(service);
            Message msg = Message.obtain(null, MixPlayService.MSG_REGISTER);
            msg.replyTo = mMessenger;
            try {
                mService.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
        }
    };

    void doBindService(Intent service)
    {
        mMainActivity.bindService(service,mServiceConnection, Context.BIND_AUTO_CREATE);
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        Mix mMix = (Mix) getArguments().getSerializable(ARG_MIX);
        Mixez mMixez = (Mixez) getArguments().getSerializable(ARG_MIXEZ);
        int mPosition = getArguments().getInt(ARG_POSITION);

        mMainActivity = (MainActivity)getActivity();
        mPlayerServiceIntent = new Intent(mMainActivity,MixPlayService.class);
        mPlayerServiceIntent.putExtra(ARG_MIX, mMix);
        mPlayerServiceIntent.putExtra(ARG_MIXEZ, mMixez);
        mPlayerServiceIntent.putExtra(ARG_POSITION, mPosition);
        mPlayerServiceIntent.setAction(MixPlayService.ACTION_PLAY);
        doBindService(mPlayerServiceIntent);
        mMainActivity.startService(mPlayerServiceIntent);
        mMainActivity.getActionBarDrawerToggle().setDrawerIndicatorEnabled(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mixes_item,container,false);

        mPlayPauseButton = (ImageButton)v.findViewById(R.id.button_play_pause);
        ImageButton mPrevButton = (ImageButton) v.findViewById(R.id.button_skip_prev);
        ImageButton mNextButton = (ImageButton) v.findViewById(R.id.button_skip_next);
        mMixTitle = (TextView) v.findViewById(R.id.mix_title);
        mMixProgress = (SeekBar) v.findViewById(R.id.mix_progress);
        mDurationText = (TextView) v.findViewById(R.id.duration_text);
        mCurrentPositionText = (TextView) v.findViewById(R.id.current_position_text);
        mImgMixPoster = (ImageView) v.findViewById(R.id.img_mix_poster);

        mMixTitle.setSelected(true);

        mMixProgress.setOnSeekBarChangeListener(new OnMixProgressChanged());

        mPrevButton.setOnClickListener(new OnNavigationButtonClickListener());
        mNextButton.setOnClickListener(new OnNavigationButtonClickListener());
        mImgMixPoster.setOnClickListener(this);
        mPlayPauseButton.setOnClickListener(new OnNavigationButtonClickListener());
        return v;
    }

    private class OnMixProgressChanged implements SeekBar.OnSeekBarChangeListener{

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            try {
                Message msg = Message.obtain(null, MixPlayService.MSG_GET_CURRENT_POSITION);
                Bundle data = new Bundle();
                Log.d("OnStopTracking", String.valueOf(seekBar.getProgress()));
                data.putInt(ARG_CURRENT_POSITION, seekBar.getProgress());
                msg.setData(data);
                mService.send(msg);
            } catch (RemoteException e){
                e.printStackTrace();
            }
        }
    }

    private class OnNavigationButtonClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Message msg = null;
            switch (v.getId())
            {
                case R.id.button_play_pause:
                    if(mMixIsPaused == true)
                        msg= Message.obtain(null,MixPlayService.MSG_PLAY);
                    else
                        msg= Message.obtain(null,MixPlayService.MSG_PAUSE);
                    break;
                case R.id.button_skip_prev:
                    msg = Message.obtain(null,MixPlayService.MSG_PREV);
                    break;
                case R.id.button_skip_next:
                    msg = Message.obtain(null,MixPlayService.MSG_PREV);
                    break;

            }
            try {
                mService.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                mMainActivity.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MixItem", "destroy");
        mMainActivity.unbindService(mServiceConnection);
        mMainActivity.stopService(mPlayerServiceIntent);
    }
}
