package com.thekeirs.gameengine.framework;

import android.view.MotionEvent;
import android.view.View;

import com.thekeirs.gameengine.system.IMessageClient;
import com.thekeirs.gameengine.system.Message;
import com.thekeirs.gameengine.system.MessageBus;

/**
 * Created by wurzel on 12/30/16.
 */

public final class InputHandler implements View.OnTouchListener {
    private MessageBus mBus;

    public InputHandler(MessageBus mbus) {
        mBus = mbus;
    }

    @Override
    public boolean onTouch(View view, MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Message msg = new Message("touch", (int) e.getX(), (int) e.getY());
                mBus.postMessage(msg);
                break;
            default:
                break;

        }
        return false;
    }
}
