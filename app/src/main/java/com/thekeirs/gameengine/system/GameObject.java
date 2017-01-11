package com.thekeirs.gameengine.system;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by wurzel on 12/30/16.
 */

abstract public class GameObject {
    public String name;
    public int x;
    public int y;
    public Bitmap image;
    protected GameObjectManager manager;
    public Rect boundingRect;

    public GameObject(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.boundingRect = new Rect(x, y, x, y);
    }

    public void setManager(GameObjectManager manager) {
        this.manager = manager;

    }
    public void update() {
    }

    public void onTouch(int x, int y) {
    }

    abstract public void draw(Canvas c);

    public boolean contains(int x, int y) {
        return boundingRect.contains(x, y);
    }

    protected void hop(double distance, double direction) {
        x += (int) (distance * Math.cos(direction));
        y += (int) (distance * Math.sin(direction));
    }

    protected void hopToward(double distance, int destx, int desty) {
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
