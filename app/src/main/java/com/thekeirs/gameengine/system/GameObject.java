package com.thekeirs.gameengine.system;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

/**
 * Created by wurzel on 12/30/16.
 */

public class GameObject {
    public String name;
    public int x;
    public int y;
    public Bitmap image;
    public GameObjectManager manager;

    public GameObject(GameObjectManager manager, String name, int x, int y) {
        this.manager = manager;
        this.name = name;
        this.x = x;
        this.y = y;

        this.manager.addObject(this);
    }

    public void loadImage(int id) {
        image = BitmapFactory.decodeResource(manager.getResources(), id);
    }

    public void update() {

    }

    public void draw(Canvas c) {
        Log.d("gameobject", "Drawing " + name + " at " + x + ", " + y);
        c.drawBitmap(image, x, y, null);
    }
}
