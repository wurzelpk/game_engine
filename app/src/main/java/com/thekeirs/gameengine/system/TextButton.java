package com.thekeirs.gameengine.system;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by wurzel on 12/30/16.
 */

public class TextButton extends GameObject {
    private String mText;
    private int mSize;
    private Paint mPaint;

    public TextButton(String name, int x, int y, String text) {
        super(name, x, y);
        mText = text;
        mSize = 12;
        recalc();
    }

    public void setSize(int size) {
        mSize = size;
        recalc();
    }

    public void setText(String text) {
        mText = text;
        recalc();
    }

    private void recalc() {
        if (mPaint == null) {
            mPaint = new Paint();
        }
        mPaint.setTextSize(mSize);
        Rect rect = new Rect();
        mPaint.getTextBounds(mText, 0, mText.length(), rect);
        rect.offset(x, y);
        boundingRect = rect;
    }

    public void draw(Canvas c) {
        // Log.d("gameobject", "Drawing " + name + " at " + x + ", " + y);
        c.drawText(mText, x, y, mPaint);
    }


}
