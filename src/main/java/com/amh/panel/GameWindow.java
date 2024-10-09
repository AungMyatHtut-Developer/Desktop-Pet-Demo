package com.amh.panel;

import com.amh.entity.MyRect;

import java.awt.*;

import static com.amh.common.CommonData.FPS;
import static com.amh.common.CommonData.UPS;
import static com.amh.common.CommonFunctions.getRandomColor;
import static com.amh.common.CommonFunctions.getRandomSize;

public class GameWindow implements Runnable{

    private Thread gameThread;
    private volatile boolean isGameRunning = true;

    GamePanel gamePanel;
    private MyRect myRect;

    public GameWindow() {
        initMyRect();
        gamePanel = new GamePanel(this);
        new GameFrame(gamePanel);

        gamePanel.setFocusable(true);
        startGameThread();
    }

    public void initMyRect() {
        myRect = new MyRect(0,0,getRandomSize(),getRandomSize(),getRandomColor());
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        myRect.update();
    }

    public void render(Graphics g) {
        myRect.render(g);
    }

    @Override
    public void run() {

        long currentTimeTrackForFPS;
        long previousTimeTrackForFPS = 0;

        long currentTimeTrackForUPS;
        long previousTimeTrackForUPS = 0;

        long timeTrack = 0;
        long timeTrackPrevious = 0;

        int frames = 0;
        int updates = 0;

        while (isGameRunning) {

            currentTimeTrackForFPS = System.nanoTime();
            if(currentTimeTrackForFPS - previousTimeTrackForFPS >= FPS){
                previousTimeTrackForFPS = currentTimeTrackForFPS;
                gamePanel.repaint();
                frames++;
            }

            currentTimeTrackForUPS = System.nanoTime();
            if (currentTimeTrackForUPS - previousTimeTrackForUPS >= UPS) {
                previousTimeTrackForUPS = currentTimeTrackForUPS;
                update();
                updates++;
            }

            timeTrack = System.currentTimeMillis();
            if(timeTrack - timeTrackPrevious >= 1_000){
                timeTrackPrevious = timeTrack;
                System.out.println("FPS : "+ frames +" UPS : "+ updates);
                frames = 0;
                updates = 0;
            }

        }
    }

}
