package com.amh.entity;

import com.amh.constants.MovementDirection;

import java.awt.*;

import static com.amh.common.CommonFunctions.RANDOM_MOVEMENT_ACTION;
import static com.amh.common.CommonFunctions.RANDOM_STOP_ACTION;

public class Dog extends Pet {

    public Dog(float x, float y, float width, float height, Animation animation, Movement movement) {
        super(x,y,width,height,animation,movement);
    }

    @Override
    public void makeSound() {}

    @Override
    public void render(Graphics g) {

        Graphics2D g2D = (Graphics2D) g;
//        System.out.println(movement.getMovementDirection());

        if (movement.getMovementDirection() == MovementDirection.RIGHT) {
            g2D.drawImage(animation.getCurrentFrame(), (int) x, (int) y, (int) width, (int) height,null);
        }

        if (movement.getMovementDirection() == MovementDirection.LEFT) {
            g2D.translate((int) x + this.width / 2, (int) y + this.height / 2);
            g2D.scale(-1, 1); // Flip horizontally
            g2D.translate(-(int) x - this.width / 2, -(int) y - this.height / 2);
            g2D.drawImage(animation.getCurrentFrame(), (int) x, (int) y, (int) width, (int) height,null);
            g2D.setTransform(g2D.getDeviceConfiguration().getDefaultTransform());
        }
    }

    @Override
    public void update() {
        animation.animate(animation.getPetAction());
        movement.updatePosition(this);
    }

    @Override
    public void move(int x, int y) {
        this.x = x - (width / 2);
        this.y = y - (height / 2);
    }

    @Override
    public void stopPet() {
        isStop = !isStop;
        if (isStop) {
            animation.setPetAction(RANDOM_STOP_ACTION());
        }else{
            animation.setPetAction(RANDOM_MOVEMENT_ACTION());
        }

    }
}
