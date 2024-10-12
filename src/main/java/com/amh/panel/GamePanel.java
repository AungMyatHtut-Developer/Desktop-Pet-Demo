package com.amh.panel;

import com.amh.handler.MouseHandler;

import javax.swing.*;
import java.awt.*;

import static com.amh.common.CommonData.BACKGROUND_COLOR;

public class GamePanel extends JPanel {

    GameWindow gameWindow;
    MouseHandler mouseHandler;

    public GamePanel(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setBackground(BACKGROUND_COLOR);
        setGamePanelSize();
        setOpaque(false);

        mouseHandler = new MouseHandler(gameWindow);
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    void setGamePanelSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(screenSize);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameWindow.render(g);
    }

}
