package com.amh.common;

import com.amh.constants.PetAction;

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

    public static PetAction RANDOM_MOVEMENT_ACTION() {
        int ran = random.nextInt(4);

        switch (ran) {
            case 0: return PetAction.JUMP;
            case 2: return PetAction.RUN;
            case 3: return PetAction.SNIFF_WALK;
            default: return PetAction.WALK;
        }
    }

    public static PetAction RANDOM_STOP_ACTION() {
        int ran = random.nextInt(4);

        switch (ran) {
            case 0: return PetAction.IDLE1;
            case 2: return PetAction.IDLE2;
            case 3: return PetAction.SIT;
            default: return PetAction.SNIFF;
        }
    }
}

