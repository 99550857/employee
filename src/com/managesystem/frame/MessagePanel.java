package com.managesystem.frame;

import com.managesystem.service.UserService;
import com.managesystem.service.impl.UserServiceImpl;
import com.managesystem.model.*;
import utils.IconNode;
import utils.IconNodeRender;
import utils.RoundAvatar;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
    private JPanel leftCardPanel;
    private JPanel treePanel;
    private JPanel listPanel;
    private JTextField textField1;
    private JButton 搜索Button;
    private JTree tree1;
    private UserService userService = new UserServiceImpl();
    private Map<String,List<EmployeeInfo>> map = new HashMap<>();
    private IconNode root= new IconNode(null,null);
    public MessagePanel() {
        in();

        //DefaultTreeCellRenderer cellRenderer=(DefaultTreeCellRenderer)tree1.getCellRenderer();

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
            ImageIcon imageIcon = new ImageIcon("src/img/file.png");
            imageIcon.setImage(imageIcon.getImage().getScaledInstance(30,30,Image.SCALE_FAST));
            IconNode childRoot = new IconNode(imageIcon,entry.getKey());
            for (EmployeeInfo employeeInfo:entry.getValue()) {
                InputStream is = new ByteArrayInputStream(employeeInfo.getAvatar());
                BufferedImage avater = null;
                try {
                    BufferedImage img = ImageIO.read(is);
                     avater = RoundAvatar.scaleByPercentage(img,40,40);
                    avater = RoundAvatar.convertCircular(avater);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                IconNode iconNode = new IconNode(new ImageIcon(avater),employeeInfo.getName());
                childRoot.add(iconNode);
            }
            root.add(childRoot);
        }
        tree1 = new JTree(root);
        tree1.setCellRenderer(new IconNodeRender());
        tree1.setEditable(false);
        tree1.setRootVisible(false);
        tree1.setToggleClickCount(1);
        treePanel.add(tree1,BorderLayout.CENTER);
        add(mainPanel);
    }

    public void in(){
        CardLayout cardLayout = new CardLayout();
        leftCardPanel.setLayout(cardLayout);
        cardLayout.show(leftCardPanel,"Card1");
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("LoginFrame");
        frame.setContentPane(new MessagePanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
