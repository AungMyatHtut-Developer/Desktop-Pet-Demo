package com.amh.panel;

import com.amh.constants.PetAssetName;
import com.amh.constants.CorgiAction;
import com.amh.entity.*;
import com.amh.util.AssetStore;

import java.awt.*;

import static com.amh.common.CommonData.*;
import static com.amh.common.CommonFunctions.RANDOMX;
import static com.amh.common.CommonFunctions.RANDOMY;

public class GameWindow implements Runnable{

    private Thread gameThread;
    private volatile boolean isGameRunning = true;
    private Pet pet;

    GamePanel gamePanel;

    public GameWindow() {
        initRequiredData();
        gamePanel = new GamePanel(this);
        new GameFrame(gamePanel);

        gamePanel.setFocusable(true);
        startGameThread();
    }

    private void initRequiredData() {
        Animation animation = new Animation(AssetStore.GetCorgiAnimations(PetAssetName.CORGI_WITH_NO_TAIL), CorgiAction.SNIFF_WALK);
        Movement movement = new Movement(SPEED_X, SPEED_Y);

        pet = new Dog(RANDOMX(), RANDOMY(),CORGI_WIDTH, CORGI_HEIGHT,animation,movement);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        pet.update();
    }

    public void render(Graphics2D g) {
        pet.render(g);
    }

    public void movePet(int x, int y) {
        pet.move(x, y);
    }

    public void stopPet() {
        pet.stopPet();
    }

    public void changePetColor(PetAssetName petAssetName) {
        pet.changeColor(petAssetName);
    }

    public void changePet(Pet pet) {
        this.pet = pet;
    }

    public Pet getPet() {
        return this.pet;
    }


    @Override
    public void run() {

        double deltaFPS = 0;
        double deltaUPS = 0;

        long currentTimeTrackForFPS;
        long previousTimeTrackForFPS = System.nanoTime();

        long currentTimeTrackForUPS;
        long previousTimeTrackForUPS = System.nanoTime();

        long timeTrack = 0;
        long timeTrackPrevious = 0;

        int frames = 0;
        int updates = 0;

        while (isGameRunning) {

            currentTimeTrackForUPS = System.nanoTime();
            deltaUPS += (currentTimeTrackForUPS - previousTimeTrackForUPS) / UPS;
            previousTimeTrackForUPS = currentTimeTrackForUPS;
            if (deltaUPS >= 1) {
                update();
                updates++;
                deltaUPS--;
            }

            currentTimeTrackForFPS = System.nanoTime();
            deltaFPS +=  (currentTimeTrackForFPS - previousTimeTrackForFPS) / FPS;
            previousTimeTrackForFPS = currentTimeTrackForFPS;
            if(deltaFPS >= 1){
                gamePanel.repaint();
                frames++;
                deltaFPS--;
            }

            timeTrack = System.currentTimeMillis();
            if(timeTrack - timeTrackPrevious >= 1_000){
                timeTrackPrevious = timeTrack;
                frames = 0;
                updates = 0;
            }

        }
    }

}
