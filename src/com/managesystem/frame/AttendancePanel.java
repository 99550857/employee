package com.managesystem.frame;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by 99550 on 2017/12/19.
 */
public class AttendancePanel extends JPanel {
    private JPanel topPanel;
    public AttendancePanel(List<String> function) {
        super();
        setPreferredSize(new Dimension(500,500));
        topPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
        setLayout(new BorderLayout());
        function.forEach(s -> topPanel.add(new JLabel(s)));
        add(topPanel,BorderLayout.NORTH);
    }
}
