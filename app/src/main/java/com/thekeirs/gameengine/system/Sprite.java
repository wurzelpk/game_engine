package com.thekeirs.gameengine.system;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by wurzel on 12/30/16.
 */

public class Sprite extends GameObject {
    public Bitmap image;

    public Sprite(GameObjectManager manager, String name, int x, int y) {
        super(manager, name, x, y);
    }

    public void loadImage(int id) {
        image = BitmapFactory.decodeResource(manager.getResources(), id);
        this.boundingRect.set(x, y, x + image.getWidth(), y + image.getHeight());
    }

    public void draw(Canvas c) {
        // Log.d("gameobject", "Drawing " + name + " at " + x + ", " + y);
        c.drawBitmap(image, x, y, null);
    }


}
