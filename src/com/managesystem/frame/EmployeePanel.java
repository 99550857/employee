package com.managesystem.frame;

import com.managesystem.service.UserService;
import com.managesystem.service.impl.UserServiceImpl;
import com.managesystem.model.*;
import com.managesystem.ui.CWCheckBoxRenderer;
import com.managesystem.ui.CheckBoxCellEditor;
import utils.ExcelUtil;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author  lihui
 * 员工管理面板
 * 使用from继承jpanel
 * 方便简单
 *
 */
public class EmployeePanel extends JPanel{
    private JPanel panel1;
    private JTable table1;
    private JButton deleteButton;
    private JPanel topPanel;
    private JPanel topLeftPanel;
    private JPanel topRightPanel;
    private JButton addEmployeeButton;
    private JButton updateButton;
    private JComboBox departmentComboBox;
    private JComboBox areaComboBox;
    private JTextField textField1;
    private JButton searchButton;
    private JPanel bottomPanel;
    private JScrollPane scrollPane;
    private JButton 批量导入Button;
    private List<String> function;
    private DefaultTableModel dtm;
    private String []s={"增加员工","删除员工","修改员工","查看员工"};
    private UserService userService = new UserServiceImpl();
    private List<EmployeeInfo>  users ;
    private DefaultComboBoxModel deb1;
    private DefaultComboBoxModel deb2;
    private String address= null;
    private StringBuffer condition = new StringBuffer();
    private List<Integer> rows = new ArrayList<>();
    public EmployeePanel(java.util.List<String> function) {
        this.function=function;
        setLayout(new BorderLayout());
        onPanel();
        add(panel1);


    }

    public void onPanel(){
        deb1=new DefaultComboBoxModel();
        departmentComboBox.setModel(deb1);
        deb1.addElement("");
        userService.getAllDepartment().forEach(department -> deb1.addElement(department.getName()));

        deb2=new DefaultComboBoxModel();
        areaComboBox.setModel(deb2);
        deb2.addElement("");
        userService.getAllArea().forEach(a-> deb2.addElement(a));

        if(function.contains(s[0])){
            addEmployeeButton.setVisible(true);
            addEmployeeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new NewEmployeeDialog().setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                }
            });
        }
        if (function.contains(s[1])){
            updateButton.setVisible(true);
        }
        if (function.contains(s[2])){
            deleteButton.setVisible(true);
        }
        if(function.contains(s[3])){
            showTable();
            topRightPanel.setVisible(true);
            departmentComboBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if(e.getStateChange() == ItemEvent.SELECTED){
                        String departmentid=null;
                        if(!"".equals(departmentComboBox.getSelectedItem().toString())){
                            departmentid = userService.getDepartmentid(departmentComboBox.getSelectedItem().toString());
                        }else {
                            departmentid = String.valueOf("");
                        }
                        address=areaComboBox.getSelectedItem().toString();
                        if (condition.length() == 0) {
                            if(!"".equals(departmentid)) {
                                condition.append("WHERE departmentID = '" + departmentid + "'");
                            }
                        } else if(!condition.toString().contains("departmentID")){
                            if(!"".equals(departmentid)) {
                                condition.append(" AND departmentID = '" + departmentid + "'");
                            }
                        }else if(!condition.toString().contains("address")&&condition.toString().contains("departmentID")){
                            condition=new StringBuffer();
                            if(!"".equals(departmentid)) {
                                condition.append("WHERE departmentID = '" + departmentid + "'");
                            }
                        }else {
                            condition=new StringBuffer();
                            condition.append("WHERE address = '" + address + "'");
                            if(!"".equals(departmentid)) {
                                condition.append(" AND departmentID = '" + departmentid + "'");
                            }
                        }
                        updateModel();
                    }
                }
            });
            areaComboBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if(e.getStateChange() == ItemEvent.SELECTED){
                        String departmentid=null;
                        if(!"".equals(departmentComboBox.getSelectedItem().toString())){
                            departmentid = userService.getDepartmentid(departmentComboBox.getSelectedItem().toString());
                        }else {
                            departmentid = String.valueOf("");
                        }
                        address=areaComboBox.getSelectedItem().toString();
                        if (condition.length() == 0) {
                            condition=new StringBuffer();
                            if(!"".equals(address)) {
                                condition.append("WHERE province = '" + address + "'");
                            }
                        } else if(!condition.toString().contains("province")){
                            if(!"".equals(address)) {
                                condition.append(" AND province = '" + address + "'");
                            }
                        }else if(!condition.toString().contains("province")&&condition.toString().contains("departmentid")){
                            condition=new StringBuffer();
                            if(!"".equals(address)) {
                                condition.append("WHERE province = '" + address + "'");
                            }
                        }else {
                            condition=new StringBuffer();
                            if(!"".equals(departmentid)) {
                                condition.append("WHERE departmentid = '" + departmentid + "'");
                            }
                            if(!"".equals(address)) {
                                condition.append(" AND province = '" + address + "'");
                            }
                        }
                        updateModel();
                    }
                }
            });
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    List<String> list = new ArrayList<>();
                    for (int i:rows) {
                        list.add(dtm.getValueAt(i,1).toString());
                    }
                    int[] result = new int[users.size()];
                        //调用批量删除方法，得到一个整型数组（受影响的记录行）
                        result = userService.batchDelete(list);
                    if (result.length != 0) {
                        JOptionPane.showMessageDialog(null, "删除成功");
                        //从表格模型中移除掉已经删除的记录
                        for (int i = rows.size() - 1; i >= 0; i--) {
                            dtm.removeRow(rows.get(i));
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "删除失败");
                    }
                }
            });
            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String keyword = textField1.getText();
                    users = userService.queryLike(keyword);
                    int count = dtm.getRowCount();
                    for (int i = count-1; i >= 0 ; i--) {
                        dtm.removeRow(i);
                    }
                    for(EmployeeInfo employee : users){
                        add(employee);
                    }
                }
            });

            批量导入Button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser jFileChooser = new JFileChooser();
                    int n = jFileChooser.showOpenDialog(EmployeePanel.this);
                    if(n ==JFileChooser.APPROVE_OPTION){
                        File file = jFileChooser.getSelectedFile();
                        InputStream inputStream=null;
                        try {
                            inputStream = new FileInputStream(file);
                        } catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        }
                        List<EmployeeInfo> list = new ExcelUtil().readExcelContent(inputStream);
                        int []result = userService.batchInsert(list);
                        if (result.length != 0) {
                            JOptionPane.showMessageDialog(null, "批量导入成功！");
                            showTable();
                        } else {
                            JOptionPane.showMessageDialog(null, "批量导入失败！");
                        }
                    }
                }
            });
        }
    }

    public void showTable(){
        dtm = new DefaultTableModel();
        users = new ArrayList<>();
        users = userService.getAll();
        String []title={" ","工号","部门编号","姓名","性别","名族","详细地址","学历","职务","入职时间","电话","邮箱"};
        dtm.setColumnIdentifiers(title);
        table1.setModel(dtm);
        TableColumnModel tcm= table1.getColumnModel();
        tcm.getColumn(0).setCellEditor(new DefaultCellEditor(new JCheckBox()));
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        // 设置水平方向居中
        r.setHorizontalAlignment(JLabel.CENTER);
        table1.setDefaultRenderer(Object.class, r);
        // 将表头居中
        DefaultTableCellRenderer r1 = new DefaultTableCellRenderer();
        r1.setHorizontalAlignment(JLabel.CENTER);
        r1.setBackground(Color.LIGHT_GRAY);
        table1.getTableHeader().setDefaultRenderer(r1);
        table1.getColumnModel().getColumn(0).setCellEditor(new CheckBoxCellEditor());
        table1.getColumnModel().getColumn(0).setCellRenderer(new CWCheckBoxRenderer());
        Object []content=new Object[12];
        for (EmployeeInfo e:users) {
            content[0] = new Boolean(false);
            content[1] = e.getEmployeeid();
            content[2] = e.getDepartmentid();
            content[3] = e.getName();
            content[4] = e.getGender();
            content[5] = e.getNation();
            content[6] = e.getAddress();
            content[7] = e.getEduback();
            content[8] = e.getPost();
            content[9] = e.getEntry_time().toString();
            content[10] = e.getPhone();
            content[11] = e.getEmail();
            dtm.addRow(content);
        }
        panel1.revalidate();
        table1.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row= e.getFirstRow();
                int col= e.getColumn();
                if(row<table1.getRowCount()){
                    if(table1.getValueAt(row,col).toString()=="true"){
                        rows.add(row);
                    }else {
                        rows.remove(row);
                    }
                }
            }
        });
    }

    public void updateModel(){

        users = userService.queryBy(condition.toString());
        int count = dtm.getRowCount();
        for (int i = count-1; i >= 0 ; i--) {
            dtm.removeRow(i);
            rows.removeAll(rows);
        }
        for(EmployeeInfo e : users){
            add(e);
        }
    }

    public void add(EmployeeInfo employeeInfo){
        Object []rowdata = {new Boolean(false),employeeInfo.getEmployeeid(),employeeInfo.getDepartmentid(),employeeInfo.getName(),
                            employeeInfo.getGender(),employeeInfo.getName(),employeeInfo.getAddress(),
                            employeeInfo.getEduback(),employeeInfo.getPost(),employeeInfo.getEntry_time(),
                            employeeInfo.getPhone(),employeeInfo.getEmail()
                            };
        dtm.addRow(rowdata);
    }
}
