package com.amh.panel;

import com.amh.constants.CorgiAssetName;
import com.amh.entity.Corgi;

import java.awt.*;

import static com.amh.common.CommonData.FPS;
import static com.amh.common.CommonData.UPS;

public class GameWindow implements Runnable{

    private Thread gameThread;
    private volatile boolean isGameRunning = true;
    private Corgi corgi;

    GamePanel gamePanel;

    public GameWindow() {
        initRequiredData();
        gamePanel = new GamePanel(this);
        new GameFrame(gamePanel);

        gamePanel.setFocusable(true);
        startGameThread();
    }

    private void initRequiredData() {
        corgi = new Corgi(10,10,64,64,2, CorgiAssetName.CORGI_WITH_TAIL);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        corgi.update();
    }

    public void render(Graphics g) {
        corgi.render(g);
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
