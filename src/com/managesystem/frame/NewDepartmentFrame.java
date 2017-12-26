package com.managesystem.frame;

import javax.swing.*;

public class NewDepartmentFrame extends JFrame{
    private JPanel mainPanel;
    private JTextField idField;
    private JTextField nameField;
    private JTextField introductionField;
    private JTextField contactwayField;

    public NewDepartmentFrame(){
        setTitle("新增部门");
        setSize(700,800);
        this.getContentPane().add(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);
        setLocationRelativeTo(null);

    }


}
