package com.managesystem.frame;

import utils.DialogDatePicker;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shenwenwu
 * 弹出的新增员工窗口
 */
public class NewEmployeeDialog extends JDialog {
    private JPanel contentPane;
    private JButton 取消Button;
    private JButton 确定Button;
    private JPanel cneterPanel;
    private JTextField employeeIDTextField;
    private JTextField passwordTextField;
    private JTextField nameTextField;
    private JTextField nationTextField;
    private JComboBox departmentComboBox;
    private JButton 入职时间Button;
    private JTextField dateTextField;
    private JComboBox comboBox2;
    private JTextField emailTextField;
    private JLabel avatarLabel;
    private JComboBox provinceComboBox;
    private JComboBox cityComboBox;
    private JTextField addressTextField;
    private JTextField postTextField;
    private JComboBox eduBackComboBox;
    private JTextField tectitleTextField;
    private JComboBox politicalStatusComboBox;
    private JComboBox maritalStatusComboBox;
    private JTextField phoneTextField;
    private JComboBox comboBox1;
    private DefaultComboBoxModel provinceDCB;
    private DefaultComboBoxModel cityDCB;
    private String[] provinces={"江苏","浙江","安徽"};
    private String[] jiangsuCity = {"徐州","连云港","南京"};
    private String[] zhejiangCity = {"杭州","建德","温州"};
    private String[] anhuiCity = {"芜湖","蚌埠","合肥"};
    private Map<String,String[]> provinceMap=new HashMap<>();
    private String dateString;
    public NewEmployeeDialog() {
        setModal(true);
        init();
        pack();
        setVisible(true);
        setSize(500, 300);
        setLocationRelativeTo(null);
        add(contentPane);
        取消Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewEmployeeDialog.this.dispose();
            }
        });
        provinceComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (provinceComboBox.getSelectedItem().toString()==provinces[0]){
                    cityDCB.removeAllElements();
                    cityDCB.addElement(jiangsuCity);
                }
                if (provinceComboBox.getSelectedItem().toString()==provinces[1]){
                    cityDCB.removeAllElements();
                    cityDCB.addElement(zhejiangCity);
                }
                if (provinceComboBox.getSelectedItem().toString()==provinces[0]){
                    cityDCB.removeAllElements();
                    cityDCB.addElement(anhuiCity);
                }
            }
        });
        入职时间Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField txtDate = new JTextField();
                //弹出时间选择框
                new DialogDatePicker(true, txtDate, 750, 450);
                //获得日期
                dateString = txtDate.getText();
                dateTextField.setText(dateString);
            }
        });
    }
    public void init(){
        provinceMap.put(provinces[0],jiangsuCity);
        provinceMap.put(provinces[1],zhejiangCity);
        provinceMap.put(provinces[2],anhuiCity);
        provinceDCB = new DefaultComboBoxModel();
        for (String s:provinces) {
            provinceDCB.addElement(s);
        }
        provinceComboBox.setModel(provinceDCB);
        cityDCB = new DefaultComboBoxModel();
        cityDCB.addElement(jiangsuCity);
        cityComboBox.setModel(cityDCB);
    }
}
