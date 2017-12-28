package com.managesystem.frame;

import com.managesystem.dao.DepartmentDAO;
import com.managesystem.factory.DAOFactory;
import com.managesystem.factory.ServiceFactory;
import com.managesystem.model.Department;
import com.managesystem.service.DepartmentService;
import com.managesystem.ui.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;


public class DepartmentPanel extends JPanel {
    private JLabel[] infoLabels;
    private JButton deleteButton;
    private JButton addButton;
    private JButton updateButton;
    private Department department;
    private JPanel card5;
    private DepartmentService departmentService = ServiceFactory.getDepartmentService();
    private JLabel label;

    public DepartmentPanel(Department department,List<String> fumctionList) {
        setPreferredSize(new Dimension(250,250));
        this.department=department;
        this.setPreferredSize(new Dimension(300,400));
        Color bgColor = new Color(79,193,176);
        this.setBackground(bgColor);
        deleteButton = new JButton("删除");
        addButton = new JButton("新增");
        updateButton = new JButton("修改");
        label = new JLabel();
        Style.setButtonStyle(deleteButton);
        Style.setButtonStyle(addButton);
        Style.setButtonStyle(updateButton);
        deleteButton.setSize(50,60);
        addButton.setSize(50,60);
        updateButton.setSize(50,60);
        Font font = new Font("微软雅黑",Font.BOLD,16);
        Color foreColor = new Color(255,255,255);
        //标签数组，分别显示部门logo，部门名称，部门人数，部门介绍，联系电话
        infoLabels = new JLabel[5];
        for (int i = 0; i < infoLabels.length; i++){
            infoLabels[i] = new JLabel();
        }
        for (JLabel label : infoLabels){
            label.setForeground(foreColor);
            label.setFont(font);
        }
        infoLabels[0].setIcon(new ImageIcon(department.getLogo()));
        infoLabels[1].setText(department.getName());
        infoLabels[2].setText(String.valueOf(department.getCount()) + "人");
        infoLabels[3].setText(department.getIntroduction());
        infoLabels[4].setText(department.getContactway());
        this.setLayout(new GridLayout(5,1,5,5));
        for (JLabel label : infoLabels) {
            add(label);
        }


        add(deleteButton);


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = department.getId();
                int n = ServiceFactory.getDepartmentService().deleteDepartment(id);
                if (n != 0) {
                    JOptionPane.showMessageDialog(null,"删除院系成功");
                    card5.remove(DepartmentPanel.this);
                    card5.revalidate();
                } else {
                    JOptionPane.showMessageDialog(null,"删除院系失败");
                }
            }
        });

        add(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewDepartmentFrame();
            }
        });

        add(updateButton);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateDepartmentFrame();
               // department = departmentService.get();

            }
        });

        infoLabels[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new UpdateDepartmentFrame();
            }
        });

        add(label);
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame frame = new JFrame("根据院系统计学生人数柱形图");
                // 添加柱形图
                frame.add(new BarChart1().getChartPanel(), BorderLayout.CENTER);
                frame.setSize(600, 500);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });





    }


    public void add(Department department) {

    }
}
