package com.managesystem.frame;

import com.managesystem.model.Admin;
import com.managesystem.model.Employee;
import com.managesystem.model.EmployeeInfo;
import com.managesystem.service.UserService;
import com.managesystem.service.impl.UserServiceImpl;
import utils.RoundAvatar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * @author lishuang
 * 用户界面
 *
 */
public class UserFrame extends JFrame{
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel leftPanel;
    private JPanel centerPanel;
    private JLabel titleLabel;
    private JLabel avatarLabel;
    private JLabel nameLabel;
    private UserService userService =new UserServiceImpl();
    private EmployeeInfo employeeInfo;
    private JButton[] buttons;
    private int i = 0;
    private AttendancePanel ap;
    private EmployeePanel ep;
    private NoticePanel np;

    private Map<String,List<String>> pMap;
    public UserFrame(Admin admin, Map<String,List<String>> pMap) {
        this.pMap=pMap;
        employeeInfo= userService.getInfo(admin.getAccount());
        init();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        add(mainPanel);
    }
    public UserFrame(Employee employee, Map<String,List<String>> pMap) {
        this.pMap=pMap;
        employeeInfo= userService.getInfo(employee.getEmployeeid());
        init();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        add(mainPanel);
    }
    public void init(){
        CardLayout card = new CardLayout();
        centerPanel.setLayout(card);
        topPanel.setLayout(null);
        titleLabel=new JLabel("员工管理系统");
        titleLabel.setBounds(50,10,200,80);
        titleLabel.setFont(new Font("微软雅黑",Font.BOLD,25));
        topPanel.add(titleLabel);
        avatarLabel=new JLabel();
        RoundAvatar.getRoundAvatar(avatarLabel,employeeInfo.getAvatar());
        avatarLabel.setBounds(1600,10,100,100);
        topPanel.add(avatarLabel);

        int count = pMap.entrySet().size();
        buttons = new JButton[count];
        Iterator<Map.Entry<String,List<String>>> iterator =  pMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,List<String>> entry = iterator.next();
            String groupname=entry.getKey();
            JButton jButton = new JButton(groupname);
            leftPanel.add(jButton);
            buttons[i] = jButton;
            if ("员工管理".equals(groupname)) {
                ep = new EmployeePanel(entry.getValue());
                centerPanel.add("card1",ep);
            }
            if ("通知管理".equals(groupname)) {
                np = new NoticePanel(entry.getValue());
                centerPanel.add("card2", np);
            }
            if ("考勤管理".equals(groupname)) {
                ap = new AttendancePanel(entry.getValue());
                centerPanel.add("card3", ap);
            }
            i++;
        }
        for (JButton button: buttons) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = button.getText();
                    System.out.println(name);
                    if ("员工管理".equals(name)) {
                        card.show(centerPanel, "card1");
                    } else if ("通知管理".equals(name)) {
                        card.show(centerPanel, "card2");
                    } else if ("考勤管理".equals(name)) {
                        card.show(centerPanel, "card3");
                    }
                }
            });
        }
    }
}
