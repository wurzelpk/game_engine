package com.thekeirs.gameengine.system;

import android.content.res.Resources;
import android.graphics.Canvas;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wurzel on 12/30/16.
 */

public final class GameObjectManager implements IMessageClient, GameView.IRedrawService, GameView.IGameLogicService {
    final private String TAG = "GameObjectManager";
    private Map<String, GameObject> mObjects;
    private Scene mScene;
    private GameLevel mLevel;

    private Resources mResources;
    public MessageBus mBus;

    public GameObjectManager(MessageBus mbus, Resources res) {
        mResources = res;

        mObjects = new HashMap<>();
        mBus = mbus;
        mBus.addClient(this);
    }

    public void setLevel(GameLevel level) {
        if (mLevel != null) {
            mLevel.finish();
        }
        mObjects.clear();
        mScene = null;

        mLevel = level;
        mLevel.setObjectManager(this);
        mLevel.setup();
    }

    public Resources getResources() {
        return mResources;
    }

    public void addObject(GameObject obj) {
        obj.setManager(this);
        mObjects.put(obj.name, obj);
    }

    public GameObject getObjectByName(String name) {
        return mObjects.get(name);
    }


    public void setScene(Scene scene) {
        this.mScene = scene;
    }

    public void removeScene() {
        this.mScene = null;
    }

    @Override
    public void handleMessage(Message msg) {
        if (msg.type.equals("touch")) {
            checkTouchedObjects(msg.x, msg.y);
        } else if (msg.type.equals("surface_ready")) {
        }
    }

    public void checkTouchedObjects(int x, int y) {
        for (GameObject obj : mObjects.values()) {
            if (obj.contains(x, y)) {
                obj.onTouch(x, y);
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        // Log.d(TAG, "draw");
        if (mScene != null) {
            mScene.draw(canvas);
        }
        for (GameObject obj : mObjects.values()) {
            obj.draw(canvas);
        }
    }

    @Override
    public void update(int millis) {
        // Log.d(TAG, "update");
        for (GameObject obj : mObjects.values()) {
            obj.update();
        }
    }
}
