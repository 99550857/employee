package com.managesystem.ui;

import javax.swing.*;
import java.awt.*;

public class Style {
    public final static Dimension BUTTON_SIZE = new Dimension(160, 50);
    public final static Dimension FIELD_SIZE = new Dimension(150, 50);

    public final static Color GREEN = new Color(51, 104, 51);
    public final static Color BLUE = new Color(102, 153, 153);
    public final static Color WHITE = new Color(255, 255, 255);

    public final static Font NOMAL_FONT = new Font("微软雅黑", Font.PLAIN, 20);
    public final static Font BOLD_FONT = new Font("微软雅黑", Font.BOLD, 24);

    public static void setButtonStyle(JButton button) {
        button.setPreferredSize(BUTTON_SIZE);
        button.setBackground(GREEN);
        button.setForeground(WHITE);
        button.setFont(NOMAL_FONT);
    }

    public static void setFieldStyle(JComponent component) {
        component.setPreferredSize(FIELD_SIZE);
        component.setBackground(BLUE);
        component.setForeground(WHITE);
        component.setFont(NOMAL_FONT);
    }

    public static void setLabelStyle(JLabel label) {
        label.setForeground(BLUE);
        label.setFont(BOLD_FONT);
    }

    public static void setNomalFontFont(JComponent component) {
        component.setFont(NOMAL_FONT);
    }

    public static void setBoldFontFont(JComponent component) {
        component.setFont(BOLD_FONT);
    }

}
