package com.managesystem.frame;

import com.managesystem.model.Admin;
import com.managesystem.model.Employee;
import com.managesystem.model.Inform;
import com.managesystem.service.UserService;
import com.managesystem.service.impl.UserServiceImpl;
import com.managesystem.ui.InformJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;
import java.util.List;

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
    private JPanel allInformPanel;
    private JPanel departmentInfoPanel;
    private JPanel personalPanel;
    private JLabel titleLabel;
    private Timestamp timestamp;
    private Admin admin=null;
    private Employee employee=null;
    private CardLayout card = new CardLayout();
    private UserService userService = new UserServiceImpl();
    private List<String> powerList =new ArrayList<>();
    public InformPanel(Admin admin,List<String> list) {
        setPreferredSize(new Dimension(1500,800));
        this.powerList=list;
        this.admin = admin;
        add(mainPanel);
        init();
    }
    public InformPanel(Employee employee, List<String> list) {
        this.powerList=list;
        this.employee = employee;
        add(mainPanel);
        init();
        sendInform();
        modifyInform();
    }
    public void init(){
        cardPanel.setLayout(card);
        if(powerList.contains("增加通知")) {
            cardPanel.add(sendCard,"card1");
            departmentInform.setVisible(true);
            departmentInform.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    card.show(cardPanel,"card1");
                }
            });
            sendInform();
        }

        if(powerList.contains("查看通知")){
            cardPanel.add(modifyCard,"card2");
            readLabel.setVisible(true);
            readLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    card.show(cardPanel,"card2");
                }
            });
            modifyInform();
        }
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
        java.util.List<Inform> informListAll = null;
        try {
             informListAll = userService.getAllInform();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Inform> allPart = getPart(informListAll);
        for (Inform inform:allPart) {
            allInformPanel.add(new InformJPanel(800,30,inform));
        }
    }


    public List<Inform> getPart(List<Inform> list){
        Collections.reverse(list);
        List<Inform> list1 = new ArrayList<>();
        for (Inform inform:list ) {
            if(list1.size()<8){
                list1.add(inform);
            }else {
                break;
            }
        }
        return list1;
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("LoginFrame");
        frame.setContentPane(new InformPanel(new Admin ("111","111"),new ArrayList<String>()));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setVisible(true);
    }
}
