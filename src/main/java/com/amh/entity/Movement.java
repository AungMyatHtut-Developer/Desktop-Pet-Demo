package com.amh.entity;

import com.amh.constants.CatAction;
import com.amh.constants.CorgiAction;
import com.amh.constants.MovementDirection;
import com.amh.constants.PetAction;

import static com.amh.common.CommonData.*;
import static com.amh.common.CommonFunctions.RANDOM_Y_MOVEMENT;

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

        if (pet instanceof Dog) {
            if (pet.animation.getPetAction() == CorgiAction.JUMP ||
                    pet.animation.getPetAction() == CorgiAction.WALK ||
                    pet.animation.getPetAction() == CorgiAction.RUN ||
                    pet.animation.getPetAction() == CorgiAction.SNIFF_WALK) {
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

        if (pet instanceof Cat) {
            PetAction petAction = pet.animation.getPetAction();
            if (petAction == CatAction.WALK || petAction == CatAction.JUMP_RUN) {
                if (pet.x > GAME_SCREEN_SIZE.width - pet.width) {
                    speedX *= switchDirection;
                    movementDirectionX = MovementDirection.LEFT;
                }

                if (pet.x < 0) {
                    speedX *= switchDirection;
                    movementDirectionX = MovementDirection.RIGHT;
                }

                System.out.println("Cat Y: "+ pet.y+" Game Screen Height : "+ (GAME_SCREEN_SIZE.height - pet.height));
                if (pet.y > GAME_SCREEN_SIZE.height - pet.height) {
                    speedY = RANDOM_Y_MOVEMENT();
                    speedY *= switchDirection;
                }

                if (pet.y < 0) {
                    speedY = RANDOM_Y_MOVEMENT();
                    speedY *= switchDirection;
                }

                pet.x += speedX;
                pet.y += speedY;
            }
        }


    }

    public float checkAnimation(PetAction petAction, float speedX) {
        if (petAction.equals(CorgiAction.RUN)) {
            return (CORGI_MOVEMENT_SPEED * 3) * ((speedX < 0) ? switchDirection : 1);
        } else if (petAction.equals(CorgiAction.WALK) || petAction.equals(CorgiAction.SNIFF_WALK)) {
            return (CORGI_MOVEMENT_SPEED) * ((speedX < 0) ? switchDirection : 1);
        } else if (petAction.equals(CorgiAction.JUMP)) {
            return (CORGI_MOVEMENT_SPEED * 4) * ((speedX < 0) ? switchDirection : 1);
        } else if (petAction.equals(CatAction.WALK)) {
            return (CAT_MOVEMENT_SPEED * 1) * ((speedX < 0) ? switchDirection : 1);
        } else if (petAction.equals(CatAction.JUMP_RUN)) {
            return (CAT_MOVEMENT_SPEED * 2) * ((speedX < 0) ? switchDirection : 1);
        }
        return speedX;
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
