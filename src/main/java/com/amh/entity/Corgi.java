package com.amh.entity;

import com.amh.constants.CorgiAssetName;
import com.amh.util.AssetStore;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.amh.common.CommonData.GAME_SCREEN_SIZE;

public class Corgi extends Pet implements PetFunctions{

    private BufferedImage[][] PET_ANIMATIONS;
    private int direction = -1;

    public Corgi(int x, int y, int width, int height, int movementSpeed, CorgiAssetName corgiAssetName) {
        super(x,y,width,height,movementSpeed);
        deltaX = 0.9f;
        deltaY = 0.9f;
        PET_ANIMATIONS = AssetStore.GetCorgiAnimations(corgiAssetName);
    }

    @Override
    public void makeVoice() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(PET_ANIMATIONS[0][0],(int) x, (int) y, 64,64, null);
    }

    @Override
    public void update() {

        if (x > GAME_SCREEN_SIZE.width - 64 || x < 0) {
            deltaX *= direction;
        }
        if (y > GAME_SCREEN_SIZE.height - 64 || y < 0) {
            deltaY *= direction;
        }
        this.x += deltaX;
//        this.y += deltaY;
    }

    @Override
    public void eating() {

    }

    @Override
    public void animate() {

    }
}
