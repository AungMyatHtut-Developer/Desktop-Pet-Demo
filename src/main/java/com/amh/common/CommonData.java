package com.amh.common;

import java.awt.*;

public class CommonData {

    public static final String GAME_TITLE = "DESKTOP PET";
    public static final Color BACKGROUND_COLOR = new Color(0, 0, 0, 0);
    public static final Dimension GAME_SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public static final double FPS = 1_000_000_000 / 60;
    public static final double UPS = 1_000_000_000 / 60;

    public static final String CORGI_WITH_TAIL_IMG ="corgi-asset-tail.png";
    public static final String CORGI_WITH_NO_TAIL_IMG ="corgi-asset-notail-v2.png";
    public static final String CORGI_TRICOLOR_WITH_TAIL_IMG ="tricolorcorgi-asset-tail.png";
    public static final String CORGI_TRICOLOR_WITH_NO_TAIL_IMG ="tricolorcorgi-asset-notail.png";
    public static final String CAT = "cat.png";

    public static final float CORGI_HEIGHT = 128, CORGI_WIDTH = 128;
    public static final float CORGI_MOVEMENT_SPEED = 1.2f;
    public static final float SPEED_X = 2f, SPEED_Y  =2f;

    public static final float CAT_HEIGHT = 64, CAT_WIDTH = 64;
    public static final float CAT_MOVEMENT_SPEED = 1.5f;

}
