package com.amh.panel;

import com.amh.constants.CorgiAssetName;
import com.amh.constants.MovementDirection;
import com.amh.constants.PetAction;
import com.amh.entity.*;
import com.amh.util.AssetStore;

import java.awt.*;

import static com.amh.common.CommonData.*;
import static com.amh.common.CommonFunctions.RANDOMX;
import static com.amh.common.CommonFunctions.RANDOMY;

public class GameWindow implements Runnable{

    private Thread gameThread;
    private volatile boolean isGameRunning = true;
    private PetV2 pet;

    GamePanel gamePanel;

    public GameWindow() {
        initRequiredData();
        gamePanel = new GamePanel(this);
        new GameFrame(gamePanel);

        gamePanel.setFocusable(true);
        startGameThread();
    }

    private void initRequiredData() {
        Animation animation = new Animation(AssetStore.GetCorgiAnimations(CorgiAssetName.CORGI_WITH_TAIL), PetAction.SIT);
        Movement movement = new Movement(SPEED_X, SPEED_Y);

        pet = new CorgiV2(RANDOMX(), RANDOMY(),CORGI_WIDTH, CORGI_HEIGHT,animation,movement);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        pet.update();
    }

    public void render(Graphics g) {
        pet.render(g);
    }


    @Override
    public void run() {

        double deltaFPS = 0;
        double deltaUPS = 0;

        long currentTimeTrackForFPS;
        long previousTimeTrackForFPS = 0;

        long currentTimeTrackForUPS;
        long previousTimeTrackForUPS = 0;

        long timeTrack = 0;
        long timeTrackPrevious = 0;

        int frames = 0;
        int updates = 0;

        while (isGameRunning) {

            currentTimeTrackForUPS = System.nanoTime();
            deltaUPS = (double) (currentTimeTrackForUPS - previousTimeTrackForUPS) / UPS;
            if (deltaUPS >= 1) {
                previousTimeTrackForUPS = currentTimeTrackForUPS;
                update();
                updates++;
                deltaUPS--;
            }

            currentTimeTrackForFPS = System.nanoTime();
            deltaFPS = (double) (currentTimeTrackForFPS - previousTimeTrackForFPS) / FPS;
            if(deltaFPS >= 1){
                previousTimeTrackForFPS = currentTimeTrackForFPS;
                gamePanel.repaint();
                frames++;
                deltaFPS--;
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
