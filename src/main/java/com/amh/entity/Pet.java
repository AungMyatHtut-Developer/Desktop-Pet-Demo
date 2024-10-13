package com.amh.entity;

import java.awt.*;

public abstract class Pet {

    protected float x , y;
    protected float width, height;
    protected Animation animation;
    protected Movement movement;
    protected HitBox hitBox;
    protected boolean isStop;

    public Pet(float x, float y, float width, float height, Animation animation, Movement movement) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.animation = animation;
        this.movement = movement;
        this.hitBox = new HitBox(x, y, width, height);
    }

    public abstract void makeSound();

    public abstract void render(Graphics g);

    public abstract void update();

    public abstract void move(int x, int y);

    public abstract void stopPet();

}
