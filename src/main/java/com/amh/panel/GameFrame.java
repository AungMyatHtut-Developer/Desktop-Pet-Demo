package com.amh.panel;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;

import javax.swing.*;


import static com.amh.common.CommonData.*;

public class GameFrame extends JFrame{
    private static final int WS_EX_TOOLWINDOW = 0x00000080;

    public GameFrame(GamePanel gamePanel) {
        setTitle(GAME_TITLE);
        setUndecorated(true);
        setAlwaysOnTop(true);
        setBackground(BACKGROUND_COLOR);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(gamePanel);
        pack();
        setVisible(true);

//        hideTaskbarIcon(this);
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
}
