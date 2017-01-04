package com.thekeirs.gameengine.system;

import com.thekeirs.gameengine.R;
import com.thekeirs.gameengine.framework.Rand;

/**
 * Created by wurzel on 12/30/16.
 */

public class FrogSprite extends Sprite {
    public FrogSprite(GameObjectManager manager, String name, int x, int y) {
        super(manager, name, x, y);
        loadImage(R.drawable.frog);
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

    public void onTouch(int x, int y) {
        int dist = Rand.between(30, 50);

        hopToward(dist, 250, 250);

        manager.mBus.postMessage(new Message("ribbit"));
    }
}
