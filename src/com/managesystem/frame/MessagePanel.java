package com.managesystem.frame;

import com.managesystem.service.UserService;
import com.managesystem.service.impl.UserServiceImpl;
import com.managesystem.model.*;
import utils.IconNode;
import utils.IconNodeRender;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 99550 on 2017/12/24.
 */
public class MessagePanel extends JPanel{
    private JPanel mainPanel;
    private JPanel leftPanel;
    private JTextField textField1;
    private JPanel leftCardPanel;
    private JPanel treePanel;
    private JPanel listPanel;
    private JTree tree1;
    private UserService userService = new UserServiceImpl();
    private Map<String,List<EmployeeInfo>> map = new HashMap<>();
    private IconNode root= new IconNode(null);
    public MessagePanel() {
        tree1 = new JTree(root);
        treePanel.add(tree1,BorderLayout.CENTER);
        //DefaultTreeCellRenderer cellRenderer=(DefaultTreeCellRenderer)tree1.getCellRenderer();
        tree1.setCellRenderer(new IconNodeRender());
        tree1.setEditable(false);
        tree1.setRootVisible(false);
        tree1.setToggleClickCount(1);
        List<String> departmentNameList = new ArrayList<>();
        List<Department> departmentList = userService.getAllDepartment();
        departmentList.forEach(department -> departmentNameList.add(department.getName()));
        for (Department department:departmentList) {
            try {
                map.put(department.getName(),userService.getDepartmentEmployee(department.getId()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        for (Map.Entry<String,List<EmployeeInfo>> entry:map.entrySet()) {
            IconNode childRoot = new IconNode(new ImageIcon("src/img/file.png"),entry.getKey());
            for (EmployeeInfo employeeInfo:entry.getValue()) {
                IconNode iconNode = new IconNode(setIcon(new ImageIcon(employeeInfo.getAvatar())),employeeInfo.getName());
                childRoot.add(iconNode);
            }
            root.add(childRoot);
        }

        add(mainPanel);
    }
    public ImageIcon setIcon(ImageIcon imageIcon){
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(20,20, Image.SCALE_FAST));
        return imageIcon;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("LoginFrame");
        frame.setContentPane(new MessagePanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
