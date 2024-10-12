package com.amh.entity;

import com.amh.constants.PetAction;

import java.awt.*;

public class Pet implements PetFunctions {

    protected float x, y, width, height;
    protected float deltaX, deltaY;
    protected int movementSpeed;
    protected int action, animationTick;
    protected PetAction petAction;
    protected int frameCount;

    protected float hitBoxX, hitBoxY, hitBoxWidth, hitBoxHeight;

    public Pet(int x, int y, int width, int height, int movementSpeed, float hitBoxX, float hitBoxY, float hitBoxWidth, float hitBoxHeight, PetAction petAction) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.movementSpeed = movementSpeed;
        this.hitBoxX = hitBoxX;
        this.hitBoxY = hitBoxY;
        this.hitBoxWidth = hitBoxWidth;
        this.hitBoxHeight = hitBoxHeight;
        this.petAction = petAction;
    }

    @Override
    public void makeVoice() {}

    @Override
    public void render(Graphics g) {}

    @Override
    public void update() {}

    @Override
    public void eating() {}

    @Override
    public void animate() {}

    @Override
    public void move(int x, int y) {}

    @Override
    public void updatePosition() {

    }

    @Override
    public void stop() {

    }
}
