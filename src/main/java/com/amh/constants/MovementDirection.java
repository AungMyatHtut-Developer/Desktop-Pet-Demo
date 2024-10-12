package com.amh.constants;

public enum MovementDirection {
    LEFT(-1),
    RIGHT(1),
    UP(-1),
    DOWN(1);

    private final int direction;

    MovementDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return this.direction;
    }

}
