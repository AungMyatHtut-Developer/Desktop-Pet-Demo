package com.amh.entity;

import java.awt.*;
import java.util.Random;

import static com.amh.common.CommonData.GAME_SCREEN_SIZE;
import static com.amh.common.CommonFunctions.getRandomColor;
import static com.amh.common.CommonFunctions.getRandomSize;

public class MyRect {

    private int x, y, width, height;
    private int xDelta = 10, yDelta = 10;
    private int direction = -1;
    private Color color;

    public MyRect(int x, int y, int width, int height, Color color){
        this.x = x; this.y = y;
        this.width = width; this.height = height;
        this.color = color;
    }

    public void update(){
        if(x > GAME_SCREEN_SIZE.width || x < 0){
            xDelta *= direction;
            changeColor();
            changeSize();
        }
        if (y > GAME_SCREEN_SIZE.height || y < 0) {
            yDelta *= direction;
            changeColor();
            changeSize();
        }
        x += xDelta;
        y += yDelta;
    }

    public void render(Graphics g){
        g.setColor(this.color);
        g.fillRect(x,y,width,height);
    }

    public void changeColor() {
        this.color = getRandomColor();
    }

    public void changeSize() {
        this.height = getRandomSize();
        this.width = getRandomSize();
    }


}
