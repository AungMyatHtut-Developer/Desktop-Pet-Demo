package com.amh.common;

import java.awt.*;
import java.util.Random;

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
}

