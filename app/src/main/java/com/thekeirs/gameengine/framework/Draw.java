package com.thekeirs.gameengine.framework;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.thekeirs.gameengine.system.GameView;
import com.thekeirs.gameengine.system.Message;
import com.thekeirs.gameengine.system.MessageBus;

/**
 * Created by wurzel on 12/31/16.
 */

public class Draw {
    private MessageBus mBus;
    private GameView mGameView;

    private SurfaceHolder mHolder;
    private int mWidth, mHeight;

    public Draw(MessageBus mbus, GameView gameview) {
        mBus = mbus;
        mGameView = gameview;

        mGameView.setHolderManager(new GameView.SurfaceHolderManager() {
            @Override
            public void onSurfaceHolderReady(SurfaceHolder holder, int width, int height) {
                mHolder = holder;
                mWidth = width;
                mHeight = height;

                mBus.postMessage(new Message("surface_ready", width, height));
                forceUpdateRedraw();
            }

            @Override
            public void onSurfaceHolderClosing() {
                mHolder = null;
                mWidth = 0;
                mHeight = 0;
            }

        });
    }

    public void forceUpdateRedraw() {
        Canvas canvas = mHolder.lockCanvas();
        mBus.postMessage(new Message("update_redraw", canvas));
        mHolder.unlockCanvasAndPost(canvas);
    }
}
