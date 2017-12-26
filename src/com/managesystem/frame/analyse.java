package com.managesystem.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by lenovo on 2017/12/25.
 */
public class analyse {
    private JPanel mainPanel;
    private JLabel label1;
    private JLabel label2;

    public analyse(){

        label1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame frame = new JFrame("********");
                // 添加柱形图
                frame.add(new BarChart1().getChartPanel(), BorderLayout.CENTER);
                frame.setSize(750, 500);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        label2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame frame = new JFrame("********");
                // 添加柱形图
                frame.add(new BarChart2().getChartPanel(), BorderLayout.CENTER);
                frame.setSize(750, 500);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("analyse");
        frame.setContentPane(new analyse().mainPanel);
        frame.setSize(300,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
