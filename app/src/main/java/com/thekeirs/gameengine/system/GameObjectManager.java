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

    private Resources mResources;
    private MessageBus mBus;

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

    public void removeObject(GameObject obj) {
        objects.remove(obj);
    }

    @Override
    public void handleMessage(Message msg) {
        if (msg.type.equals("update_redraw")) {
            Canvas canvas = (Canvas) msg.obj;
            for (GameObject obj : objects.values()) {
                obj.update();
            }
            for (GameObject obj : objects.values()) {
                obj.draw(canvas);
            }
        }
    }
}
