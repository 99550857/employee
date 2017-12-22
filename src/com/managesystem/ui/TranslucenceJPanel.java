package com.managesystem.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author lihui
 *透明面板
 */
public class TranslucenceJPanel extends JPanel{

    private float transparency;

    public TranslucenceJPanel(float transparency){
        this.transparency = transparency;
    }

    /**这个方法用来设置JPanel的透明度
     *
     * @param transparency:透明度
     * 在0-1.0之间
     * @return void
     */
    public void setTransparent(float transparency) {
        this.transparency = transparency;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D graphics2d = (Graphics2D) g.create();
        graphics2d.setComposite(AlphaComposite.SrcOver.derive(transparency));
        graphics2d.setColor(getBackground());
        graphics2d.fillRect(0, 0, getWidth(), getHeight());
        graphics2d.dispose();
    }
}
