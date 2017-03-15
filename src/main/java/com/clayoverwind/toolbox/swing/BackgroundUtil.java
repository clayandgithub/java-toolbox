package com.clayoverwind.toolbox.swing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by wangweiwei on 2017/3/15.
 */
public class BackgroundUtil {
    public static void addImageBackgroundPanel(final JFrame window, final String imageName) {
        if (imageName != null) {
            JPanel imgPanel = new JPanel() {
                private static final long serialVersionUID = 2580564128458591658L;

                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    ImageIcon icon = new ImageIcon(this.getClass().getResource(imageName));
                    g.drawImage(icon.getImage(), 0, 0, window.getWidth(),
                            window.getHeight(), this);
                }
            };
            imgPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
            imgPanel.setOpaque(false);
            window.setContentPane(imgPanel);
        }
    }
}
