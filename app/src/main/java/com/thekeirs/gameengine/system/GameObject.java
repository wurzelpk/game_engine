package com.thekeirs.gameengine.system;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.thekeirs.gameengine.framework.Rand;

/**
 * Created by wurzel on 12/30/16.
 */

public class GameObject {
    public String name;
    public int x;
    public int y;
    public Bitmap image;
    public GameObjectManager manager;
    public Rect boundingRect;

    public GameObject(GameObjectManager manager, String name, int x, int y) {
        this.manager = manager;
        this.name = name;
        this.x = x;
        this.y = y;
        this.boundingRect = new Rect(x, y, x, y);

        this.manager.addObject(this);
    }

    public void loadImage(int id) {
        image = BitmapFactory.decodeResource(manager.getResources(), id);
        this.boundingRect.set(x, y, x + image.getWidth(), y + image.getHeight());
    }

    public void update() {
        if (Rand.onceEvery(2.0f)) {
            int dist = Rand.between(30, 50);
            double direction = Math.toRadians(Rand.between(0, 360));
            hop(dist, direction);
        }

        int width = image.getWidth();
        int height = image.getHeight();

        if (x < 0) {
            x = 0;
        } else if (x + width > 500) {
            x = 500 - width;
        }

        if (y < 0) {
            y = 0;
        } else if (y + height > 500) {
            y = 500 - height;
        }

        this.boundingRect.set(x, y, x + width, y + height);
    }


    public void draw(Canvas c) {
        // Log.d("gameobject", "Drawing " + name + " at " + x + ", " + y);
        c.drawBitmap(image, x, y, null);
    }


    public boolean contains(int x, int y) {
        return boundingRect.contains(x, y);
    }

    public void onTouch(int x, int y) {
        int dist = Rand.between(30, 50);

        hopToward(dist, 250, 250);

        manager.mBus.postMessage(new Message("ribbit"));
    }

    private void hop(double distance, double direction) {
        x += (int) (distance * Math.cos(direction));
        y += (int) (distance * Math.sin(direction));
    }

    private void hopToward(double distance, int destx, int desty) {
        int dx = destx - x;
        int dy = desty - y;
        double totaldist = Math.sqrt(dx * dx + dy * dy);
        if (distance < totaldist) {
            x += (int) (dx * distance / totaldist);
            y += (int) (dy * distance / totaldist);
        } else {
            x = destx;
            y = desty;
        }
    }
}
