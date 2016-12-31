package com.thekeirs.gameengine.framework;

import java.util.Random;

/**
 * Created by wurzel on 12/30/16.
 */

final public class Rand {
    private static Random mRand = new Random();

    public static int between(int min, int max) {
        return min + mRand.nextInt(max - min + 1);
    }
}
