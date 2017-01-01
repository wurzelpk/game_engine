package com.thekeirs.gameengine.system;

import com.thekeirs.gameengine.R;
import com.thekeirs.gameengine.framework.Rand;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wurzel on 12/30/16.
 */

public class GameLevel {
    public GameObjectManager manager;
    public List<GameObject> frogs;
    public Scene scene;

    public GameLevel(GameObjectManager manager) {
        this.manager = manager;

        scene = new SolidColorScene("#20c0ff");
        manager.setScene(scene);

        frogs = new ArrayList<>();

        for (int i = 0; i < 5; ++i) {
            GameObject frog = new GameObject(manager, "frog" + i, Rand.between(0, 500), Rand.between(0, 500));
            frog.loadImage(R.drawable.frog);
            frogs.add(frog);
        }
    }
}
