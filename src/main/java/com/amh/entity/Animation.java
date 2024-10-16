package com.amh.entity;

import com.amh.constants.CatAction;
import com.amh.constants.CorgiAction;
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


        if (petAction instanceof CorgiAction) {

            frameCounter++;

            if (action.getAction() == CorgiAction.SIT.getAction()) {
                isSitting = true;
            }else{
                isSitting = false;
            }

            if (frameCounter >= petAction.getDelayRate()) {
                frameCounter = 0;
                currentFrame++;
                if (currentFrame >= petAction.getFrames()) {
                    if (action.getAction() == CorgiAction.SIT.getAction() && isSitting) {
                        currentFrame = 3;
                    }else{
                        currentFrame = 0;
                    }
                }
            }
        }

        if (petAction instanceof CatAction) {
            frameCounter++;
            if (frameCounter >= petAction.getDelayRate()) {
                frameCounter = 0;
                currentFrame++;
                if (currentFrame >= petAction.getFrames()) {
                    currentFrame = 0;
                }
            }
        }

    }

    public void setFrames(BufferedImage[][] frames) {
        this.frames = frames;
    }

    public BufferedImage getCurrentFrame() {
        return frames[petAction.getAction()][currentFrame];
    }

    public PetAction getPetAction() {
        return petAction;
    }

    public void setPetAction(PetAction petAction) {
        this.petAction = petAction;
    }
}
