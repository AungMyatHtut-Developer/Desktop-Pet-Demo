package com.amh.constants;

public enum CorgiAction implements PetAction {
    JUMP(0,10,5),
    IDLE1(1,4,7),
    IDLE2(2,4,7),
    SIT(3,8,7),
    WALK(4,4,6),
    RUN(5,7,5),
    SNIFF(6,7,5),
    SNIFF_WALK(7,7,6),
    DROP_BALL(8,6,6),
    PICKUP_BALL(9,12,7),
    RUN_WITH_BALL(10,7,6),
    LYING(11,3,5),
    SLEEPING(12,3,5),
    DROP_STICK(13,6,6),
    PICKUP_STICK(14,12,7),
    RUN_WITH_STICK(15,7,6);

    private final int action;
    private final int frames;
    private final int delayRate;

    CorgiAction(int action, int frames, int delayRate){
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
