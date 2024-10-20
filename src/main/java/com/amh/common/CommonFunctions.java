package com.amh.common;

import com.amh.constants.CatAction;
import com.amh.constants.CorgiAction;

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
        return random.nextInt((int) GAME_SCREEN_SIZE.getWidth() - 200);
    }

    public static int RANDOMY() {
        return random.nextInt((int) GAME_SCREEN_SIZE.getHeight() - 200);
    }

    public static float RANDOM_Y_MOVEMENT() {
        return random.nextFloat();
    }

    public static CorgiAction RANDOM_MOVEMENT_ACTION_FOR_CORGI() {
        int ran = random.nextInt(4);

        switch (ran) {
            case 1: return CorgiAction.JUMP;
            case 2: return CorgiAction.RUN;
            case 3: return CorgiAction.SNIFF_WALK;
            default: return CorgiAction.WALK;
        }
    }

    public static CorgiAction RANDOM_STOP_ACTION_FOR_CORGI() {
        int ran = random.nextInt(4);

        switch (ran) {
            case 1: return CorgiAction.IDLE1;
            case 2: return CorgiAction.IDLE2;
            case 3: return CorgiAction.SIT;
            default: return CorgiAction.SNIFF;
        }
    }

    public static CorgiAction RANDOM_MOVEMENT_ACTION_FOR_CORGI_V2() {
        int ran = random.nextInt(6);

        switch (ran) {
            case 1: return CorgiAction.JUMP;
            case 2: return CorgiAction.RUN;
            case 3: return CorgiAction.SNIFF_WALK;
            case 4: return CorgiAction.RUN_WITH_BALL;
            case 5: return CorgiAction.RUN_WITH_STICK;
            default: return CorgiAction.WALK;
        }
    }

    public static CorgiAction RANDOM_STOP_ACTION_FOR_CORGI_V2() {
        int ran = random.nextInt(10);

        switch (ran) {
            case 1: return CorgiAction.IDLE1;
            case 2: return CorgiAction.IDLE2;
            case 3: return CorgiAction.SIT;
//            case 4: return CorgiAction.DROP_BALL;
//            case 5: return CorgiAction.PICKUP_BALL;
            case 6: return CorgiAction.LYING;
            case 7: return CorgiAction.SLEEPING;
//            case 8: return CorgiAction.DROP_STICK;
//            case 9: return CorgiAction.PICKUP_STICK;
            default: return CorgiAction.SNIFF;
        }
    }

    public static CatAction RANDOM_STOP_ACTION_FOR_CAT() {
        int ran = random.nextInt(8);

        switch (ran) {
            case 1: return CatAction.IDLE2;
            case 2: return CatAction.IDLE3;
            case 3: return CatAction.IDLE4;
            case 4: return CatAction.PLAY;
            case 5: return CatAction.AFRAID;
            case 6: return CatAction.SLEEP;
            case 7: return CatAction.CATCH;
            default: return CatAction.IDLE1;
        }
    }

    public static CatAction RANDOM_MOVEMENT_ACTION_FOR_CAT() {
        int ran = random.nextInt(2);

        switch (ran) {
            case 1: return CatAction.JUMP_RUN;
            default: return CatAction.WALK;
        }
    }
}

