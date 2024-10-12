package com.amh.entity;

import com.amh.constants.PetAction;

import java.awt.image.BufferedImage;

public class Animation {

    private BufferedImage[][] frames;
    private PetAction petAction;
    private int currentFrame;
    private int frameCounter;
    private boolean isSitting;

    public Animation(BufferedImage[][] frames, PetAction petAction) {
        this.frames = frames;
        this.petAction = petAction;
    }

    public void animate(PetAction action) {
        frameCounter++;

        if (action.getAction() == PetAction.SIT.getAction()) {
            isSitting = true;
        }else{
            isSitting = false;
        }

        if (frameCounter >= petAction.getDelayRate()) {
            frameCounter = 0;
            currentFrame++;
            if (currentFrame >= petAction.getFrames()) {
                if (action.getAction() == PetAction.SIT.getAction() && isSitting) {
                    currentFrame = 3;
                }else{
                    currentFrame = 0;
                }
            }
        }


    }

    public BufferedImage getCurrentFrame() {
        return frames[petAction.getAction()][currentFrame];
    }

    public PetAction getPetAction() {
        return petAction;
    }
}
