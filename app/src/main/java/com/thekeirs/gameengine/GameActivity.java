package com.thekeirs.gameengine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.thekeirs.gameengine.framework.Draw;
import com.thekeirs.gameengine.framework.InputHandler;
import com.thekeirs.gameengine.system.Audio;
import com.thekeirs.gameengine.system.GameLevel;
import com.thekeirs.gameengine.system.GameObjectManager;
import com.thekeirs.gameengine.system.GameView;
import com.thekeirs.gameengine.system.MessageBus;

public class GameActivity extends AppCompatActivity {
    private MessageBus mBus;
    private InputHandler mInputHandler;
    private GameView mGameView;
    private GameObjectManager mObjectManager;
    private GameLevel mGameLevel;
    private Draw mDraw;
    private Audio mAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mBus = new MessageBus();
        mInputHandler = new InputHandler(mBus);

        mGameView = (GameView) findViewById(R.id.gameview);
        mGameView.setOnTouchListener(mInputHandler);
        mAudio = new Audio(this, mBus);

        mObjectManager = new GameObjectManager(mBus, getResources());

        mGameLevel = new GameLevel(mObjectManager);

        mDraw = new Draw(mBus, mGameView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAudio.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAudio.onPause();
    }
}
