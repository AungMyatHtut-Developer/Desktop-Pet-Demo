package com.amh.panel;

import com.amh.constants.CorgiAssetName;
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
import static com.amh.constants.CorgiAssetName.CORGI_WITH_NO_TAIL;

public class GameFrame extends JFrame{
    private static final int WS_EX_TOOLWINDOW = 0x00000080;
    private TrayIcon trayIcon;

    public GameFrame(GamePanel gamePanel) {
        setTitle(GAME_TITLE);
        setUndecorated(true);
        setAlwaysOnTop(true);
        setBackground(BACKGROUND_COLOR);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(gamePanel);
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
}
