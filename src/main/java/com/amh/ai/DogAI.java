package com.amh.ai;

import com.amh.constants.CorgiAction;
import com.amh.constants.MovementDirection;
import com.amh.constants.PetAction;
import com.amh.entity.Dog;

import java.util.Random;

import static com.amh.common.CommonData.CORGI_MOVEMENT_SPEED;
import static com.amh.common.CommonData.GAME_SCREEN_SIZE;
import static com.amh.common.CommonFunctions.*;

public class DogAI {

    private int interval;

    Random random;
    boolean randomStop;

    PetAction petAction;
    boolean isStop;
    MovementDirection movementDirectionX;

    private float speedX, speedY;
    private int switchDirection = -1;

    public void wander(Dog dog) {
        random = new Random();
        randomStop = random.nextBoolean();
        double randomMovement = random.nextDouble();

        if (interval <= 0) {
            if (randomStop) {
                randomStopAction();

                interval = random.nextInt(400);
            } else {
                randomMovementAction();
                interval = random.nextInt(700);
            }

            if (dog.getX() <= 0) {
                movementDirectionX = MovementDirection.RIGHT;
                dog.updateX(0);
            } else if (dog.getX() >= GAME_SCREEN_SIZE.width - dog.getWidth()) {
                movementDirectionX = MovementDirection.LEFT;
                dog.updateX(GAME_SCREEN_SIZE.width - dog.getWidth());
            } else {
                if (randomMovement < 0.5) {
                    movementDirectionX = MovementDirection.LEFT;
                } else {
                    movementDirectionX = MovementDirection.RIGHT;
                }
            }

        } else {
            interval--;
        }

        dog.setDogAction(petAction, isStop, movementDirectionX);
        updatePosition(dog);
    }

    public void randomMovementAction() {
        this.petAction = RANDOM_MOVEMENT_ACTION_FOR_CORGI();
        this.isStop = false;
    }

    public void randomStopAction() {
        this.petAction = RANDOM_STOP_ACTION_FOR_CORGI();
        this.isStop = true;
    }

    public void updatePosition(Dog dog) {
        speedX = checkAnimation(dog.getAction(), speedX);

        if (dog.getAction() == CorgiAction.JUMP ||
                dog.getAction() == CorgiAction.WALK ||
                dog.getAction() == CorgiAction.RUN ||
                dog.getAction() == CorgiAction.SNIFF_WALK) {


            if (movementDirectionX == MovementDirection.RIGHT && !(dog.getX() >= GAME_SCREEN_SIZE.width - dog.getWidth())) {
                dog.updateX(dog.getX() + speedX);
            } else {
                movementDirectionX = MovementDirection.LEFT;
            }

            if (movementDirectionX == MovementDirection.LEFT && !(dog.getX() <= 0)) {
                dog.updateX(dog.getX() - speedX);
            } else {
                movementDirectionX = MovementDirection.RIGHT;
            }

            if (dog.getY() > GAME_SCREEN_SIZE.height - dog.getHeight()) {
                speedY = RANDOM_Y_MOVEMENT();
                speedY *= switchDirection;
            }


            if (dog.getY() < 0) {
                speedY = -RANDOM_Y_MOVEMENT();
                speedY *= switchDirection;
            }

            dog.updateY(dog.getY() + speedY);
        }
    }

    public float checkAnimation(PetAction petAction, float speedX) {
        if (petAction.equals(CorgiAction.RUN)) {
            return (CORGI_MOVEMENT_SPEED * 3) * ((speedX < 0) ? switchDirection : 1);
        } else if (petAction.equals(CorgiAction.WALK) || petAction.equals(CorgiAction.SNIFF_WALK)) {
            return (CORGI_MOVEMENT_SPEED) * ((speedX < 0) ? switchDirection : 1);
        } else if (petAction.equals(CorgiAction.JUMP)) {
            return (CORGI_MOVEMENT_SPEED * 4) * ((speedX < 0) ? switchDirection : 1);
        }
        return speedX;
    }
}
