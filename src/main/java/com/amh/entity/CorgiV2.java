package com.amh.entity;

import java.awt.*;

public class CorgiV2 extends PetV2{

    public CorgiV2(float x, float y, float width, float height, Animation animation, Movement movement) {
        super(x,y,width,height,animation,movement);
    }

    @Override
    public void makeSound() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animation.getCurrentFrame(), (int) x, (int) y, (int) width, (int) height,null);
    }

    @Override
    public void update() {
        animation.animate(animation.getPetAction());
        movement.updatePosition(this);
    }
}
