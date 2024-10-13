package com.amh.entity;

import com.amh.constants.MovementDirection;
import com.amh.constants.PetAction;

import static com.amh.common.CommonData.CORGI_MOVEMENT_SPEED;
import static com.amh.common.CommonData.GAME_SCREEN_SIZE;

public class Movement {

    private float speedX, speedY;
    private int switchDirection = -1;
    private MovementDirection movementDirectionX;

    public Movement(float speedX, float speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public void updatePosition(Pet pet) {

        speedX = checkAnimation(pet.animation.getPetAction(), speedX);

        if(pet.animation.getPetAction() == PetAction.JUMP ||
        pet.animation.getPetAction() == PetAction.WALK ||
        pet.animation.getPetAction() == PetAction.RUN ||
        pet.animation.getPetAction() == PetAction.SNIFF_WALK){
            if (pet.x > GAME_SCREEN_SIZE.width - pet.width) {
                speedX *= switchDirection;
                movementDirectionX = MovementDirection.LEFT;
            }

            if (pet.x < 0) {
                speedX *= switchDirection;
                movementDirectionX = MovementDirection.RIGHT;
            }

            if (pet.y > GAME_SCREEN_SIZE.height - pet.height) {
                speedY *= switchDirection;
            }

            if (pet.y < 0) {
                speedY *= switchDirection;
            }

            pet.x += speedX;
//            pet.y += speedY;
        }

    }

    public float checkAnimation(PetAction petAction, float speedX) {
        switch (petAction) {
            case RUN: return  (CORGI_MOVEMENT_SPEED * 3) * ((speedX < 0 ) ? switchDirection : 1);
            case WALK:
            case SNIFF_WALK:
                return  (CORGI_MOVEMENT_SPEED) * ((speedX < 0) ? switchDirection : 1);
            case JUMP:
                return (CORGI_MOVEMENT_SPEED * 4) * ((speedX < 0) ? switchDirection : 1);
            default:return  speedX;
        }
    }

    public void stop() {
        speedX = 0;
        speedY = 0;
    }

    public MovementDirection getMovementDirection() {
        if (movementDirectionX != null) {
            return this.movementDirectionX;
        }

        return MovementDirection.RIGHT;
    }
}
