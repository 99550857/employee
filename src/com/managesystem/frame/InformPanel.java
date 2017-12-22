package com.managesystem.frame;

import com.managesystem.model.Admin;
import com.managesystem.model.Inform;
import com.managesystem.service.UserService;
import com.managesystem.service.impl.UserServiceImpl;
import javafx.scene.input.DataFormat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by 99550 on 2017/12/22.
 */
public class InformPanel  extends JPanel{
    private JPanel mainPanel;
    private JLabel departmentInform;
    private JLabel readLabel;
    private JPanel sendCard;
    private JTextArea textArea1;
    private JLabel nameLabel;
    private JButton upButton;
    private JTextField titleTextField;
    private JLabel promptLabel;
    private JPanel modifyCard;
    private JPanel cardPanel;
    private Timestamp timestamp;
    private Admin admin;
    private UserService userService = new UserServiceImpl();
    public InformPanel(Admin admin) {
        this.admin = admin;
        add(mainPanel);
        CardLayout card = new CardLayout();
        cardPanel.setLayout(card);
        cardPanel.add(sendCard,"card1");
        cardPanel.add(modifyCard,"card2");
        sendInform();
        readLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                card.show(cardPanel,"card1");
            }
        });
        departmentInform.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                card.show(cardPanel,"card2");
            }
        });
    }
    public void sendInform(){
        String name = userService.getInfo(admin.getAccount()).getName();
        String departmentID = userService.getInfo(admin.getAccount()).getDepartmentid();
        String account = admin.getAccount();
        nameLabel.setText(name);
        titleTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if("".equals(titleTextField.getText())){
                    promptLabel.setVisible(true);
                }else {
                    promptLabel.setVisible(false);
                }
            }
        });
        upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timestamp = new Timestamp(System.currentTimeMillis());
                String content = textArea1.getText();
                String title = titleTextField.getText();
                Inform inform = new Inform(departmentID,account,title,content,name,timestamp);
                int n = userService.InsertInform(inform);
                if (n!=0){
                    JOptionPane.showMessageDialog(null,"发布成功");
                    titleTextField.setText("");
                    textArea1.setText("");
                }else {
                    JOptionPane.showMessageDialog(null,"发布失败");
                }
            }
        });
    }
    public void modifyInform(){

    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("LoginFrame");
        frame.setContentPane(new InformPanel(new Admin ("111","111")).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setVisible(true);
    }
}
