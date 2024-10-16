package com.amh.panel;

import com.amh.constants.CatAction;
import com.amh.constants.CorgiAction;
import com.amh.constants.PetAssetName;
import com.amh.entity.Animation;
import com.amh.entity.Cat;
import com.amh.entity.Dog;
import com.amh.entity.Movement;
import com.amh.util.AssetStore;
import com.amh.util.ImageLoader;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import static com.amh.common.CommonData.*;
import static com.amh.common.CommonFunctions.RANDOMX;
import static com.amh.common.CommonFunctions.RANDOMY;
import static com.amh.constants.PetAssetName.CORGI_WITH_NO_TAIL;

public class GameFrame extends JFrame{
    private static final int WS_EX_TOOLWINDOW = 0x00000080;
    private TrayIcon trayIcon;
    private GamePanel gamePanel;

    public GameFrame(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        setTitle(GAME_TITLE);
        setUndecorated(true);
        setAlwaysOnTop(true);
        setBackground(BACKGROUND_COLOR);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(this.gamePanel);
        pack();
        setVisible(true);

        hideTaskbarIcon(this);
        addToSystemTray();
    }

    // Method to hide JFrame icon from Windows taskbar using JNA
    private static void hideTaskbarIcon(JFrame frame) {
        // Get the HWND of the JFrame using JNA
        WinDef.HWND hwnd = new WinDef.HWND();
        hwnd.setPointer(Native.getComponentPointer(frame));

        // Retrieve the current window style
        int currentStyle = User32.INSTANCE.GetWindowLong(hwnd, WinUser.GWL_EXSTYLE);

        // Set new style to remove taskbar icon (WS_EX_TOOLWINDOW)
        int newStyle = currentStyle | WS_EX_TOOLWINDOW;// WS_EX_TOOLWINDOW;

        // Update the window style
        User32.INSTANCE.SetWindowLong(hwnd, WinUser.GWL_EXSTYLE, newStyle);
    }

    private void addToSystemTray() {
        if (!SystemTray.isSupported()) {
            System.out.println("System tray is not supported!");
            return;
        }

        // Create an icon for the tray

        Image image = ImageLoader.GetResourceImage("icon.png");
        Image resizedImage = resizeImage(image, 16, 16);
        trayIcon = new TrayIcon(resizedImage, "Corgi");

        // Create a popup menu for the tray icon
        PopupMenu popupMenu = new PopupMenu();

        MenuItem openItem = new MenuItem("Open");
        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
                setExtendedState(JFrame.NORMAL);
            }
        });

        MenuItem exitItem = new MenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        // New Menu for Dog Colors
        Menu dogColorsMenu = new Menu("Dog Colors");
        MenuItem triColorWithNoTail = new MenuItem("Tri Color No Tail");
        MenuItem triColorWithTail = new MenuItem("Tri Color With Tail");
        MenuItem lightColorWithNoTail = new MenuItem("Light Color No Tail");
        MenuItem lightColorWithTail = new MenuItem("Light Color With Tail");

        triColorWithNoTail.addActionListener(e -> changeDogColor(PetAssetName.CORGI_TRI_COLOR_WITH_NO_TAIL));
        triColorWithTail.addActionListener(e -> changeDogColor(PetAssetName.CORGI_TRI_COLOR_WITH_TAIL));
        lightColorWithNoTail.addActionListener(e -> changeDogColor(CORGI_WITH_NO_TAIL));
        lightColorWithTail.addActionListener(e -> changeDogColor(PetAssetName.CORGI_WITH_TAIL));


        dogColorsMenu.add(triColorWithNoTail);
        dogColorsMenu.add(triColorWithTail);
        dogColorsMenu.add(lightColorWithNoTail);
        dogColorsMenu.add(lightColorWithTail);
        popupMenu.add(dogColorsMenu);

        // New Menu for Companion Types
        Menu companionTypeMenu = new Menu("Companion Type");
        MenuItem corgiItem = new MenuItem("Dog");
        MenuItem catItem = new MenuItem("Cat");

        corgiItem.addActionListener(e -> changeCompanionType("Dog"));
        catItem.addActionListener(e -> changeCompanionType("Cat"));

        companionTypeMenu.add(corgiItem);
        companionTypeMenu.add(catItem);
        popupMenu.add(companionTypeMenu);

        popupMenu.add(openItem);
        popupMenu.add(exitItem);
        trayIcon.setPopupMenu(popupMenu);

        // Add the tray icon to the system tray
        SystemTray tray = SystemTray.getSystemTray();
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
        }
    }

    private Image resizeImage(Image originalImage, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }

    // Define the new methods
    private void changeDogColor(PetAssetName petAssetName) {
        gamePanel.gameWindow.changePetColor(petAssetName);
    }

    private void changeCompanionType(String type) {

        if (type.contentEquals("Dog")) {
            Animation animation = new Animation(
                    AssetStore.GetCorgiAnimations(PetAssetName.CORGI_WITH_NO_TAIL),
                    CorgiAction.SNIFF_WALK);
            Movement movement = new Movement(SPEED_X, SPEED_Y);

            gamePanel.gameWindow.changePet(new Dog(gamePanel.gameWindow.getPet().getX(), gamePanel.gameWindow.getPet().getY(),CORGI_WIDTH, CORGI_HEIGHT,animation,movement));
        } else if (type.contentEquals("Cat")) {
            Animation animation = new Animation(
                    AssetStore.GetCatAnimations(PetAssetName.CAT),
                    CatAction.WALK);
            Movement movement = new Movement(SPEED_X, SPEED_Y);
            gamePanel.gameWindow.changePet(new Cat(gamePanel.gameWindow.getPet().getX(), gamePanel.gameWindow.getPet().getY(),CAT_WIDTH * 1.5f, CAT_HEIGHT * 1.5f, animation, movement));
        }


    }
}
