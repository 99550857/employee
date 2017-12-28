package com.managesystem.frame;

import com.managesystem.factory.ServiceFactory;
import com.managesystem.model.Department;
import com.managesystem.service.DepartmentService;
import utils.FileUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class NewDepartmentFrame extends JFrame{
    private JPanel mainPanel;
    private JTextField nameField;
    private JTextField countField;
    private JTextField introductionField;
    private JTextField contactwayField;
    private JButton addButton;
    private JTextField idField;
    private JButton 选择Button;
    private JLabel logoLabel;
    private byte[] b;
    private DepartmentService departmentService = ServiceFactory.getDepartmentService();
    private DepartmentPanel departmentPanel;

    public NewDepartmentFrame(){
        setTitle("新增部门");
        setSize(600,700);
        this.getContentPane().add(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);
        setLocationRelativeTo(null);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Integer id = Integer.valueOf(idField.getText());
                String nameString = nameField.getText();
                Integer count = Integer.valueOf(countField.getText());
                String introductionString = introductionField.getText();
                String contactwayString = contactwayField.getText();
                Department department = new Department(id,nameString,introductionString,contactwayString,b,count);
                int n = 0;
                n = departmentService.insert(department);
                if (n != 0) {
                    JOptionPane.showMessageDialog(null,"新增学生成功");
                    NewDepartmentFrame.this.dispose();
                    departmentPanel.add(department);
                } else {
                    JOptionPane.showMessageDialog(null,"新增学生失败");
                }

            }
        });
        选择Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int n = chooser.showOpenDialog(NewDepartmentFrame.this);
                if (n == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    b = FileUtils.fileToBytes(file);
                    logoLabel.setIcon(new ImageIcon(b));
                }
            }
        });
    }
}
