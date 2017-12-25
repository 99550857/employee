package com.managesystem.frame;

import com.managesystem.model.Admin;
import com.managesystem.model.Employee;
import com.managesystem.service.UserService;
import com.managesystem.service.impl.UserServiceImpl;
import com.managesystem.ui.TranslucenceJPanel;
import utils.JTextFieldHintListener;
import utils.SetupAutoComplete;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * @author shidongyang
 * 登录界面
 */
public class LoginFrame extends JFrame{
    private JPanel mainPanel;
    private JPanel loginPanel;
    private JTextField accountField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private UserService userService=new UserServiceImpl();
    private Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
    private JCheckBox accountCheckBox;


    public LoginFrame() {
        setComponent();
    }
    public void setComponent(){
        accountCheckBox = new JCheckBox("记住账号");
        accountCheckBox.setBounds(180,270,100,20);
        accountCheckBox.setOpaque(false);
        mainPanel=new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                ImageIcon imageIcon = new ImageIcon("src/img/2.jpg");
                imageIcon.setImage(imageIcon.getImage().getScaledInstance((int)screensize.getWidth(),(int)screensize.getHeight(),Image.SCALE_FAST));
                imageIcon.paintIcon(this,g,0,0);
            }
        };
        loginPanel=new TranslucenceJPanel(0.2f);
        loginPanel.setLayout(null);
        loginPanel.setBounds(800,270,500,500);
        loginPanel.setOpaque(false);
        mainPanel.setLayout(null);
        accountField=new JTextField("请输入账号");
        accountField.setBounds(150,80,200,40);
        passwordField=new JPasswordField();
        passwordField.setBounds(150,200,200,40);
        loginButton=new JButton("登录");
        loginButton.setBounds(190,350,100,50);
        loginPanel.add(accountCheckBox);
        loginPanel.add(accountField);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        mainPanel.add(loginPanel);
        accountField.addFocusListener(new JTextFieldHintListener("请输入账号",accountField));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String account = accountField.getText();
                String password = new String(passwordField.getPassword());
                Map<String,Object> map = userService.login(account,password);
                String info = map.get("info").toString();
                Map<String, java.util.List<String>> pMap = userService.getPower(account);
                if("员工".equals(info)||"管理员".equals(info)){
                    JOptionPane.showMessageDialog(null,"登录成功！");
                    LoginFrame.this.dispose();
                    if("员工".equals(info)){
                        new UserFrame((Employee)map.get("employee"),pMap);
                    }else {
                        new UserFrame((Admin) map.get("admin"),pMap);
                    }
                }else {
                    JOptionPane.showMessageDialog(null,info);
                }
            }
        });
        SetupAutoComplete.setupAutoComplete(accountField, (ArrayList<String>) userService.getLogInAccount());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("LoginFrame");
        frame.setContentPane(new LoginFrame().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setVisible(true);
    }
}
