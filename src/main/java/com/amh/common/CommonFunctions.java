package com.amh.common;

import java.awt.*;
import java.util.Random;

import static com.amh.common.CommonData.GAME_SCREEN_SIZE;

public class CommonFunctions {

    private final static Random random = new Random();

    public static Color getRandomColor() {
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return new Color(r, g, b);
    }

    public static int getRandomSize() {
        return random.nextInt(30);
    }

    public static int RANDOMX() {
        return random.nextInt((int) GAME_SCREEN_SIZE.getWidth());
    }

    public static int RANDOMY() {
        return random.nextInt((int) GAME_SCREEN_SIZE.getHeight());
    }

    public static float RANDOM_Y_MOVEMENT() {
        return random.nextFloat();
    }
}

