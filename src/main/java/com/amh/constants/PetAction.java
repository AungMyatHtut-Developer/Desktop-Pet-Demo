package com.amh.constants;

public enum PetAction {
    JUMP(0,10,5),
    IDLE1(1,4,7),
    IDLE2(2,4,7),
    SIT(3,8,7),
    WALK(4,4,6),
    RUN(5,7,5),
    SNIFF(6,7,5),
    SNIFF_WALK(7,7,6);

    private final int action;
    private final int frames;
    private final int delayRate;

    PetAction(int action, int frames, int delayRate){
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
