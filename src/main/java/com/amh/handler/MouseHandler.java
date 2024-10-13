package com.amh.handler;

import com.amh.panel.GameWindow;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener {

    private GameWindow gameWindow;

    public MouseHandler(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 3) {
            gameWindow.stopPet();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        gameWindow.movePet(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
}
