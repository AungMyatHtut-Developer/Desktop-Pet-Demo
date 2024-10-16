package com.amh.constants;

public enum CatAction implements PetAction{
    IDLE1(0,3,7),
    IDLE2(1,3,8),
    IDLE3(2,3,9),
    IDLE4(3,3,9),
    WALK(4,7,8),
    JUMP_RUN(5,7,7),
    SLEEP(6,3,7),
    PLAY(7,5,8),
    CATCH(8,6,5),
    AFRAID(9,7,5);

    private final int action;
    private final int frames;
    private final int delayRate;

    CatAction(int action, int frames, int delayRate){
        this.action = action;
        this.frames = frames;
        this.delayRate = delayRate;
    }

    public int getAction() {
        return action;
    }

    public int getFrames() {
        return frames;
    }

    public int getDelayRate() {
        return delayRate;
    }
}
