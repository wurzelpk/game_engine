package com.thekeirs.gameengine.system;

import android.content.Context;

import com.thekeirs.gameengine.R;

import im.delight.android.audio.SoundManager;

/**
 * Created by wurzel on 12/31/16.
 */

public class Audio implements IMessageClient {
    private MessageBus mBus;
    private Context mContext;
    private SoundManager mSoundManager;

    public Audio(Context context, MessageBus mbus) {
        mBus = mbus;
        mBus.addClient(this);
        mContext = context;
    }

    public void onResume() {
        int maxStreams = 4;
        mSoundManager = new SoundManager(mContext, maxStreams);
        mSoundManager.start();
        mSoundManager.load(R.raw.frog_croak);
    }

    public void onPause() {
        if (mSoundManager != null) {
            mSoundManager.cancel();
            mSoundManager = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        if (mSoundManager == null) {
            return;
        }

        if (msg.type.equals("ribbit")) {
            mSoundManager.play(R.raw.frog_croak);
        }
    }
}
