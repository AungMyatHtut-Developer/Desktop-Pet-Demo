package com.amh.entity;

import com.amh.constants.PetAssetName;
import com.amh.constants.MovementDirection;
import com.amh.util.AssetStore;

import java.awt.*;

import static com.amh.common.CommonFunctions.RANDOM_MOVEMENT_ACTION_FOR_CORGI;
import static com.amh.common.CommonFunctions.RANDOM_STOP_ACTION_FOR_CORGI;

public class Dog extends Pet {

    public Dog(float x, float y, float width, float height, Animation animation, Movement movement) {
        super(x,y,width,height,animation,movement);
    }

    @Override
    public void makeSound() {}

    @Override
    public void render(Graphics2D g) {

        if (movement.getMovementDirection() == MovementDirection.RIGHT) {
            g.drawImage(animation.getCurrentFrame(), (int) x, (int) y, (int) width, (int) height,null);
        }

        if (movement.getMovementDirection() == MovementDirection.LEFT) {
            g.translate((int) x + this.width / 2, (int) y + this.height / 2);
            g.scale(-1, 1); // Flip horizontally
            g.translate(-(int) x - this.width / 2, -(int) y - this.height / 2);
            g.drawImage(animation.getCurrentFrame(), (int) x, (int) y, (int) width, (int) height,null);
            g.setTransform(g.getDeviceConfiguration().getDefaultTransform());
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
            animation.setPetAction(RANDOM_STOP_ACTION_FOR_CORGI());
        }else{
            animation.setPetAction(RANDOM_MOVEMENT_ACTION_FOR_CORGI());
        }

    }

    @Override
    public void changeColor(PetAssetName petAssetName) {
        this.animation.setFrames(AssetStore.GetCorgiAnimations(petAssetName));
    }
}
