package com.amh.entity;

import com.amh.constants.CorgiAssetName;
import com.amh.constants.PetAction;
import com.amh.util.AssetStore;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.amh.common.CommonData.GAME_SCREEN_SIZE;
import static com.amh.common.CommonFunctions.RANDOM_Y_MOVEMENT;

public class Corgi extends Pet {

    private BufferedImage[][] PET_ANIMATIONS;
    private int direction = -1;
    private boolean isMovingRight;
    private boolean isMoving;
    private boolean isSitting;

    public Corgi(int x, int y, int width, int height, int movementSpeed, CorgiAssetName corgiAssetName, PetAction petAction) {
        super(x, y, width, height, movementSpeed, x, y, width, height, petAction);
        deltaX = 1.5f;
        deltaY = 1.2f;
        PET_ANIMATIONS = AssetStore.GetCorgiAnimations(corgiAssetName);
        if (x > 0) {
            isMovingRight = true;
            isMoving = true;
        }else{
            isMovingRight = false;
            isMoving = true;
        }
    }

    @Override
    public void makeVoice() {
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect((int) hitBoxX, (int) hitBoxY, (int) hitBoxWidth, (int) hitBoxHeight);
        Graphics2D g2D = (Graphics2D) g;

        if (isMovingRight) {
            g2D.drawImage(PET_ANIMATIONS[action][animationTick], (int) x, (int) y, 128, 128, null);
        }else {
            g2D.translate((int) x + 128 / 2, (int) y + 128 / 2);
            g2D.scale(-1, 1); // Flip horizontally
            g2D.translate(-(int) x - 128 / 2, -(int) y - 128 / 2);

            g2D.drawImage(PET_ANIMATIONS[action][animationTick],(int) x, (int) y, 128,128,null);

            // Reset transform
            g2D.setTransform(g2D.getDeviceConfiguration().getDefaultTransform());
        }

    }

    @Override
    public void update() {
        updatePosition();
        animate();
    }

    @Override
    public void eating() {

    }

    @Override
    public void animate() {
        action = petAction.getAction();

        if (action == PetAction.SIT.getAction()) {
            isSitting = true;
        }else{
            isSitting = false;
        }



        frameCount++;
        if (frameCount >= 6) {
            frameCount = 0;

            animationTick++;

            if (animationTick > petAction.getFrames()) {
                if (action == PetAction.SIT.getAction() && isSitting) {
                    animationTick = 3;
                }else{
                    animationTick = 0;
                }
            }

        }


    }

    @Override
    public void updatePosition() {

        if (isMoving) {
            if (x > GAME_SCREEN_SIZE.width - 128) {
                isMovingRight = false;
                deltaX *= direction;
                x = GAME_SCREEN_SIZE.width -128;
            }

            if( x < 0){
                isMovingRight = true;
                deltaX *= direction;
                x = 0;
            }

            if (y > GAME_SCREEN_SIZE.height - 128 || y < 0) {
                deltaY = RANDOM_Y_MOVEMENT();
                deltaY *= direction;
            }
            this.x += deltaX;
            this.hitBoxX += deltaX;
            this.y += deltaY;
            this.hitBoxY += deltaY;
        }

    }

    @Override
    public void move(int x, int y) {
        if (x > GAME_SCREEN_SIZE.width - 128) {
            x = GAME_SCREEN_SIZE.width - 128;
        }
        if (x < 0) {
            x = 0;
        }
        this.x = x - (width / 2);
        this.y = y - (height / 2);
        this.hitBoxX = x - (hitBoxWidth / 2);
        this.hitBoxY = y - (hitBoxHeight / 2);
    }

    public void stop() {
        isMoving = !isMoving;
        if (!isMoving) {
            petAction = PetAction.SIT;
        }else{
            petAction = PetAction.WALK;
        }

    }
}
