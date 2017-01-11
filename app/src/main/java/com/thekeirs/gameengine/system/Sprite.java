package com.thekeirs.gameengine.system;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by wurzel on 12/30/16.
 */

public class Sprite extends GameObject {
    private int mImageId = -1;
    private Bitmap mImage;

    public Sprite(String name, int x, int y) {
        super(name, x, y);
    }

    public Sprite(String name, int x, int y, int image_id) {
        super(name, x, y);
        loadImage(image_id);
    }

    public void loadImage(int id) {
        if (id != mImageId) {
            mImageId = id;
            mImage = null;
        }
    }

    public void draw(Canvas c) {
        if (mImage == null) {
            mImage = BitmapFactory.decodeResource(manager.getResources(), mImageId);
            this.boundingRect.set(x, y, x + mImage.getWidth(), y + mImage.getHeight());
        }
        // Log.d("gameobject", "Drawing " + name + " at " + x + ", " + y);
        c.drawBitmap(mImage, x, y, null);
    }


}
