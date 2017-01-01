package com.thekeirs.gameengine.system;

import android.content.res.Resources;
import android.graphics.Canvas;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wurzel on 12/30/16.
 */

public final class GameObjectManager implements IMessageClient {
    public Map<String, GameObject> objects;
    public Scene scene;

    private Resources mResources;
    public MessageBus mBus;

    public GameObjectManager(MessageBus mbus, Resources res) {
        mResources = res;

        objects = new HashMap<>();
        mBus = mbus;
        mBus.addClient(this);
    }

    public Resources getResources() {
        return mResources;
    }

    public void addObject(GameObject obj) {
        objects.put(obj.name, obj);
    }

    public GameObject getObjectByName(String name) {
        return objects.get(name);
    }


    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void removeScene() {
        this.scene = null;
    }

    @Override
    public void handleMessage(Message msg) {
        if (msg.type.equals("update_redraw")) {
            Canvas canvas = (Canvas) msg.obj;
            if (scene != null) {
                scene.draw(canvas);
            }
            for (GameObject obj : objects.values()) {
                obj.update();
            }
            for (GameObject obj : objects.values()) {
                obj.draw(canvas);
            }
        } else if (msg.type.equals("touch")) {
            checkTouchedObjects(msg.x, msg.y);
        }
    }

    public void checkTouchedObjects(int x, int y) {
        for (GameObject obj : objects.values()) {
            if (obj.contains(x, y)) {
                obj.onTouch(x, y);
            }
        }
    }
}
