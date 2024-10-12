package com.amh.entity;

import java.awt.*;

public interface PetFunctions {
    void makeVoice();
    void render(Graphics g);
    void update();
    void eating();
    void animate();
    void move(int x, int y);
    void updatePosition();
    void stop();
}
