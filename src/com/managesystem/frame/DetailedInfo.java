package com.managesystem.frame;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import com.managesystem.model.*;
import com.managesystem.service.UserService;
import com.managesystem.service.impl.UserServiceImpl;

import java.awt.*;
import java.awt.event.*;

/**
 * Created by 99550 on 2017/12/27.
 */
public class DetailedInfo extends JPanel{
    private JPanel mainPanel;
    private JLabel postLabel;
    private JLabel phoneLabel;
    private JLabel emailLabel;
    private JLabel areaLabel;
    private JLabel nameLabel;
    private JButton 编辑个人信息Button;
    private JLabel avatarLabel;
    private JLabel backLabel;
    private JLabel idLabel;
    private JLabel departLabel;
    private JTextField nameTextField;
    private JTextField idTextField;
    private JTextField phoneTextField;
    private JComboBox departmentComboBox;
    private JButton confirmButtton;
    private JTextField emailTextField;
    private JPanel Card1;
    private JPanel Card2;
    private JLabel closeLabel;
    private JButton cancleButton;
    private DefaultComboBoxModel dcb ;
    private UserService userService = new UserServiceImpl();
    private Document []documents;
    public DetailedInfo(EmployeeInfo employeeInfo,JFrame jFrame) {

        setPreferredSize(new Dimension(600,500));
        CardLayout card = new CardLayout();
        mainPanel.setLayout(card);
        mainPanel.add(Card1,"card1");
        mainPanel.add(Card2,"card2");
        avatarLabel.setIcon(new ImageIcon(employeeInfo.getAvatar()));
        nameLabel.setText(employeeInfo.getName());
        idLabel.setText(employeeInfo.getEmployeeid());
        departLabel.setText(userService.getDepartmentname(employeeInfo.getDepartmentid()));
        postLabel.setText(employeeInfo.getPost());
        phoneLabel.setText(employeeInfo.getEmail());
        emailLabel.setText(employeeInfo.getEmail());
        areaLabel.setText(employeeInfo.getProvince()+" "+employeeInfo.getMunicipality());
        编辑个人信息Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(mainPanel,"card2");
            }
        });
        backLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                card.show(mainPanel,"card1");
            }
        });
        dcb = new DefaultComboBoxModel();
        for (Department department : userService.getAllDepartment()) {
            dcb.addElement(department.getName());
        }
        departmentComboBox.setModel(dcb);
        dcb.setSelectedItem(departLabel.getText());
        documents = new Document[4];
        nameTextField.setText(employeeInfo.getName());
        idTextField.setText(employeeInfo.getEmployeeid());
        phoneTextField.setText(employeeInfo.getEmail());
        emailTextField.setText(employeeInfo.getEmail());
        documents[0] = nameTextField.getDocument();
        documents[1] = idTextField.getDocument();
        documents[2] = phoneTextField.getDocument();
        documents[3] = emailTextField.getDocument();
        for (int i = 0; i < documents.length; i++) {
            documents[i].addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    confirmButtton.setEnabled(true);
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    confirmButtton.setEnabled(true);
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    confirmButtton.setEnabled(true);
                }
            });
        }
        mainPanel.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                jFrame.dispose();
            }
        });
        add(mainPanel);
        closeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jFrame.dispose();
            }
        });
        cancleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameTextField.setText(employeeInfo.getName());
                idTextField.setText(employeeInfo.getEmployeeid());
                phoneTextField.setText(employeeInfo.getEmail());
                emailTextField.setText(employeeInfo.getEmail());
                dcb.setSelectedItem(departLabel.getText());
            }
        });
    }
}
