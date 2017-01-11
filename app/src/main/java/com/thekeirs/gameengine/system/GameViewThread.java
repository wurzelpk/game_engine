package com.thekeirs.gameengine.system;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by wurzel on 1/8/17.
 */

public class GameViewThread extends Thread {
    final private String TAG = "GameViewThread";
    private GameView.IGameLogicService mGameLogic;
    private GameView.IRedrawService mRedrawService;
    private SurfaceHolder mHolder;

    public GameViewThread(SurfaceHolder holder, GameView.IGameLogicService gameLogic, GameView.IRedrawService redrawService) {
        mHolder = holder;
        mGameLogic = gameLogic;
        mRedrawService = redrawService;
    }

    public void run() {
        Log.d(TAG, "thread starting");
        while (!isInterrupted()) {
            Canvas c = mHolder.lockCanvas();
            mGameLogic.update(30);  // TODO: actual frame timing
            mRedrawService.draw(c);
            mHolder.unlockCanvasAndPost(c);
        }
        Log.d(TAG, "thread terminating");
    }

    public void gracefulStop() {
        Log.d(TAG, "thread termination requested");

        this.interrupt();
        try {
            this.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
