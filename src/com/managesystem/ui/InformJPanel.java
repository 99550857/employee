package com.managesystem.ui;

import com.managesystem.frame.InformPanel;
import com.managesystem.frame.NewEmployeeDialog;
import com.managesystem.model.Inform;
import com.managesystem.service.UserService;
import com.managesystem.service.impl.UserServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 99550 on 2017/12/23.
 */
public class InformJPanel extends JPanel{
    private JPanel mainPanel;
    private JLabel frontLabel;
    private JLabel backLabel;
    private UserService userService = new UserServiceImpl();
    private Color color1 = new Color(153,153,153);
    private Color color2 = new Color(255,137,0);
    public InformJPanel(int width, int height, Inform inform ) {
        setLayout(new BorderLayout());
        add(mainPanel);
        frontLabel.setText(inform.getTitle());
        String deaprtment= userService.getDepartmentname(inform.getDepartmentid());
        if(deaprtment==null){
        backLabel.setText(inform.getDate()+" "+"全体"+" "+inform.getName());
        }else {
            backLabel.setText(inform.getDate()+" "+deaprtment+" "+inform.getName());
        }
        frontLabel.setForeground(color1);
        backLabel.setForeground(color1);
        setPreferredSize(new Dimension(width,height));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                frontLabel.setForeground(color2);
                backLabel.setForeground(color2);
                mainPanel.setBackground(new Color(208, 223, 235));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                frontLabel.setForeground(color1);
                backLabel.setForeground(color1);
                mainPanel.setBackground(new Color(255, 255, 255));
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //new  InformContentDialog(inform,deaprtment);
                new NewEmployeeDialog();
            }
        });
    }
}
