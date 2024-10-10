package com.amh.entity;

public class Pet {

    protected float x, y, width, height;
    protected float deltaX, deltaY;
    protected int movementSpeed;

    public Pet(int x, int y, int width, int height, int movementSpeed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.movementSpeed = movementSpeed;
    }
}
