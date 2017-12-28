package com.managesystem.frame;

import com.managesystem.factory.ServiceFactory;
import com.managesystem.model.Department;
import com.managesystem.service.DepartmentService;
import utils.FileUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class UpdateDepartmentFrame extends JFrame{
    private JPanel mainPanel;
    private JTextField nameField;
    private JTextField introductionField;
    private JTextField countField;
    private JTextField contactwayField;
    private JButton 确认修改Button;
    private JButton 选择图片Button;
    private JLabel logoLabel;
    private byte[] b;
    private Department department = null;
    private DepartmentService departmentService = ServiceFactory.getDepartmentService();

    public UpdateDepartmentFrame(){
        setTitle("新增部门");
        setSize(600,700);
        this.getContentPane().add(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);
        setLocationRelativeTo(null);


        确认修改Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameString = nameField.getText();
                String introductionString = introductionField.getText();
                String countString = countField.getText();
                String contactwayString = contactwayField.getText();
                //将部门信息更新
                department.setLogo(b);
                department.setName(nameString);
                department.setIntroduction(introductionString);
                department.setCount(Integer.valueOf(countString));
                department.setContactway(contactwayString);
                int n = 0;
                n = departmentService.update(department);
                if (n != 0){
                    JOptionPane.showMessageDialog(null,"修改成功");
                } else {
                    JOptionPane.showMessageDialog(null,"修改失败");
                }

            }
        });
        选择图片Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //点击头像，更换头像
                JFileChooser chooser = new JFileChooser();
                int n = chooser.showOpenDialog(UpdateDepartmentFrame.this);
                if (n == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    b = FileUtils.fileToBytes(file);
                    logoLabel.setIcon(new ImageIcon(b));
                }
            }
        });
    }


}
