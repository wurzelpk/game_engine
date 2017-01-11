package com.thekeirs.gameengine.games;

import com.thekeirs.gameengine.R;
import com.thekeirs.gameengine.system.BackgroundImageScene;
import com.thekeirs.gameengine.system.GameLevel;
import com.thekeirs.gameengine.system.Sprite;

/**
 * Created by wurzel on 1/6/17.
 */

public class OpeningScreenLevel extends GameLevel {
    public OpeningScreenLevel() {
        super();
    }

    public void setup() {

        mManager.setScene(new BackgroundImageScene(mManager, R.raw.opening_background));

        mManager.addObject(
                new Sprite("frog_herder_button",
                        300, 100,
                        R.raw.button_frog_herder) {
                    @Override
                    public void onTouch(int x, int y) {
                        mManager.setLevel(new FrogHerderLevel());
                    }
                }
        );

        mManager.addObject(
                new Sprite("frogger_button",
                        300, 175,
                        R.raw.button_frogger) {
                    @Override
                    public void onTouch(int x, int y) {
                        mManager.setLevel(new FrogHerderLevel());
                    }
                }
        );

        mManager.addObject(
                new Sprite("castle_blaster_button",
                        300, 250,
                        R.raw.button_castle_blaster) {
                    @Override
                    public void onTouch(int x, int y) {
                        mManager.setLevel(new FrogHerderLevel());
                    }
                }
        );

    }

    public void finish() {

    }
}
