package pro.badrobot.androiddev.nightclub;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import java.io.IOException;

/**
 * Created by androiddev on 31.07.15.
 */
public class MixPlayService extends Service {
    MediaPlayer mMediaPlayer = null;
   // static final String MIX_STORAGE_URL = "http://club.apps76.com/uploadsmix/";
    public static final String ACTION_PLAY = "ACTION_PLAY";
    public static final int MSG_STOP = 1;
    public static final int MSG_PLAY = 2;
    public static final int MSG_PAUSE = 3;
    public static final int MSG_PREV = 4;
    public static final int MSG_NEXT = 5;
    public static final int MSG_REGISTER = 0;
    public static final int MSG_SEND_CURRENT_POSITION = 6;
    public static final int MSG_GET_CURRENT_POSITION = 7;

    private Messenger mClientMessenger = null;
    private final Messenger mMessenger = new Messenger(new InputHandler());
    private static boolean isRunning = false;
    private Mixez mMixez;
    private int mPosition;
    private int mMixesSize;

    private Handler mHandler;

    private class InputHandler extends Handler
    {
        @Override
        public void handleMessage(Message msg) {
            if(mClientMessenger == null)
            {
                mClientMessenger = msg.replyTo;
            }
            switch (msg.what)
            {
                case MSG_PAUSE:
                    if(mMediaPlayer != null & mMediaPlayer.isPlaying()) {
                        Message clientMsg = Message.obtain(null, MSG_PAUSE);
                        try {
                            mClientMessenger.send(clientMsg);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        mMediaPlayer.pause();
                    }
                    break;
                case MSG_PLAY:
                    if(mMediaPlayer != null & !mMediaPlayer.isPlaying())
                    {
                        mMediaPlayer.start();
                        Message clientMsg = Message.obtain(null, MSG_PLAY);
                        Bundle data = new Bundle();
                        data.putString(MixesItemFragment.ARG_MIX_TITLE,mMixez.mixez.get(mPosition).title);
                        clientMsg.setData(data);
                        try {
                            mClientMessenger.send(clientMsg);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case MSG_NEXT:
                    mPosition += 1;
                    if (mPosition >= mMixesSize) mPosition = 0;
                    try {
                        mMediaPlayer.reset();
                        mMediaPlayer.setDataSource(getResources().getString(R.string.mix_folder_api) + mMixez.mixez.get(mPosition).fileurl);
                        mMediaPlayer.prepareAsync();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case MSG_PREV:
                    mPosition -= 1;
                    if (mPosition <= 0) mPosition = mMixesSize - 1;
                    try {
                        mMediaPlayer.reset();
                        mMediaPlayer.setDataSource(getResources().getString(R.string.mix_folder_api) + mMixez.mixez.get(mPosition).fileurl);
                        mMediaPlayer.prepareAsync();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case MSG_GET_CURRENT_POSITION:
                    if(mMediaPlayer == null) break;
                    mMediaPlayer.seekTo(msg.getData().getInt(MixesItemFragment.ARG_CURRENT_POSITION));
                    break;

            }
            super.handleMessage(msg);
        }
    }

    private class OnSeekCompleate implements MediaPlayer.OnSeekCompleteListener
    {

        @Override
        public void onSeekComplete(MediaPlayer mp) {
            mp.start();
            Log.d("MixPlayService", "OnSeekComplete");
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mHandler = new Handler();
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setVolume(1.0f, 1.0f);
        mMediaPlayer.setOnCompletionListener(new OnMixPlayerCompletionListener());
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setOnPreparedListener(new onPreparedPlayerListener());
    }

    private class OnMixPlayerCompletionListener implements MediaPlayer.OnCompletionListener{

        @Override
        public void onCompletion(MediaPlayer mp) {
            mPosition += 1;
            if (mPosition >= mMixesSize) mPosition = 0;
            try {
                mp.reset();
                mp.setDataSource(getResources().getString(R.string.mix_folder_api) + mMixez.mixez.get(mPosition).fileurl);
                mp.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class SendCurrentPosition implements Runnable {

        @Override
        public void run() {
            if(mMediaPlayer == null) return;
            if(mMediaPlayer.isPlaying())
            {
                int currentPosition = mMediaPlayer.getCurrentPosition()/1000;
                Log.d("sendCurrentPosition",String.valueOf(currentPosition));
                Message msg = Message.obtain(null, MSG_SEND_CURRENT_POSITION);
                Bundle data = new Bundle();
                data.putInt(MixesItemFragment.ARG_CURRENT_POSITION, mMediaPlayer.getCurrentPosition());
                msg.setData(data);
                try {
                    mClientMessenger.send(msg);
                } catch (RemoteException e) {
                    Log.d("send:Error", e.toString());
                }
                mHandler.postDelayed(this,1000);
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mMixez = (Mixez) intent.getSerializableExtra(MixesItemFragment.ARG_MIXEZ);
        mPosition = intent.getIntExtra(MixesItemFragment.ARG_POSITION,-1);
        mMixesSize = mMixez.mixez.size();
        switch (intent.getAction()) {
            case ACTION_PLAY: {
                if (mMediaPlayer != null & mMediaPlayer.isPlaying()) {
                    mMediaPlayer.stop();
                    isRunning = true;
                }
                playMix((Mix) intent.getSerializableExtra(MixesItemFragment.ARG_MIX));
                break;
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public void playMix(Mix mix) {
        try {
            mMediaPlayer.setDataSource(getResources().getString(R.string.mix_folder_api) + mix.fileurl);
            mMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("MixPlayService", "onBind");
        return mMessenger.getBinder();
    }

    public static boolean isRunning()
    {
        return isRunning;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMediaPlayer.stop();
        mMediaPlayer.release();
        mMediaPlayer = null;
        Log.d("MixPlayer","destroy");
    }

    private class onPreparedPlayerListener implements MediaPlayer.OnPreparedListener
    {
        @Override
        public void onPrepared(MediaPlayer mp) {
            PendingIntent pi = PendingIntent.getActivity(getApplicationContext(),0,new Intent(getApplicationContext(),MainActivity.class),PendingIntent.FLAG_UPDATE_CURRENT);
            Notification n = new Notification();
            n.tickerText = "some text";
            n.icon = R.drawable.ic_av_play_arrow;
            n.flags |= Notification.FLAG_ONGOING_EVENT;
            n.setLatestEventInfo(getApplicationContext(), "MixPlayService", "Song", pi);
            startForeground(Notification.COLOR_DEFAULT, n);
            Message msg = Message.obtain(null, MSG_PLAY);
            Bundle data = new Bundle();
            data.putString(MixesItemFragment.ARG_MIX_TITLE, mMixez.mixez.get(mPosition).title);
            data.putInt(MixesItemFragment.ARG_DURATION, mp.getDuration());
            msg.setData(data);

            try {
                mClientMessenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            mp.start();
            mHandler.post(new SendCurrentPosition());
        }
    }
}
