package com.thekeirs.gameengine.games;

import com.thekeirs.gameengine.framework.Rand;
import com.thekeirs.gameengine.system.FrogSprite;
import com.thekeirs.gameengine.system.GameLevel;
import com.thekeirs.gameengine.system.GameObject;
import com.thekeirs.gameengine.system.Scene;
import com.thekeirs.gameengine.system.SolidColorScene;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wurzel on 1/6/17.
 */

public class FrogHerderLevel extends GameLevel {
    public List<GameObject> frogs;
    public Scene scene;

    public FrogHerderLevel() {
        super();
    }

    public void setup() {
        scene = new SolidColorScene("#20c0ff");
        // scene = new BackgroundImageScene(mManager, R.raw.stupid_background);
        mManager.setScene(scene);

        frogs = new ArrayList<>();

        for (int i = 0; i < 5; ++i) {
            GameObject frog = new FrogSprite("frog" + i, Rand.between(0, 500), Rand.between(0, 500));
            frogs.add(frog);
            mManager.addObject(frog);
        }
    }
}
