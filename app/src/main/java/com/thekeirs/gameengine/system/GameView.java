package com.thekeirs.gameengine.system;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Provides a surface for various tests to write to from background thread
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mSurfaceHolder;
    private SurfaceHolderManager mHolderManager;

    public GameView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
    }

    public void setHolderManager(SurfaceHolderManager manager) {
        mHolderManager = manager;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // Don't actually care about creation; only surfaceChanged when the surface is ready to do
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (mSurfaceHolder != null) {
            if (mHolderManager != null) {
                mHolderManager.onSurfaceHolderClosing();
            }
        }
        mSurfaceHolder = holder;

        if (mHolderManager != null) {
            mHolderManager.onSurfaceHolderReady(holder, width, height);
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mHolderManager != null) {
            mHolderManager.onSurfaceHolderClosing();
        }
        mSurfaceHolder = null;
    }

    public interface SurfaceHolderManager {
        void onSurfaceHolderReady(SurfaceHolder holder, int width, int height);

        void onSurfaceHolderClosing();
    }
}

