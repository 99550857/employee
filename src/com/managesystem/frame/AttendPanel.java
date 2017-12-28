package com.managesystem.frame;

import com.managesystem.factory.ServiceFactory;
import com.managesystem.model.AdminCheck;
import com.managesystem.service.Adminservice;
import com.managesystem.ui.MyComboBoxEditor;
import com.managesystem.ui.TableNew;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 17297 on 2017/12/28.
 */
public class AttendPanel extends JPanel{
    private JPanel mainPanel;
    private JButton 考勤Button;
    private JButton toatalButton;
    private JPanel cardPanel;
    private JPanel card1;
    private JPanel card2;
    private JButton checkButton;
    private JPanel topPanel;
    private JScrollPane scrollPane;
    private JLabel dateLabel;
    private TableNew table;
    private JComboBox jComboBox;
    private int row;
    private String typeString;
    private String  workID;
    private String dept;
    private String name;
    private List<AdminCheck> adminChecks;
    private DefaultTableModel dtm;
    private CardLayout cardLayout;
    private Adminservice adminservice = ServiceFactory.getAdminServiceInstance();
    private CalendarPanel cp;
    public AttendPanel(List<String> function){
        super();
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());

        table = new TableNew();
        dtm = new DefaultTableModel();
        jComboBox = new JComboBox();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String  date = sdf.format(new Date());

        dateLabel.setText(String.valueOf(date));
        scrollPane.setViewportView(table);


        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        cardPanel.add("1",card1);
        cardPanel.add("2",card2);
        考勤Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,"1");
            }
        });
        toatalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,"card2");
                cp = new CalendarPanel();
                cardPanel.add("card2",cp);
            }
        });
        init();
        add(mainPanel);
    }
    public void init(){
        showTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                row = table.getSelectedRow();
                String[] jb ={"正常","缺勤","迟到","早退","请假"};
                MyComboBoxEditor abc1 = new MyComboBoxEditor(jb);
                MyComboBoxEditor abc2 = new MyComboBoxEditor(jb);
                table.setComboCell(row,2,abc1);
            }
        });
        //对下拉框监听
        jComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    typeString = String.valueOf(jComboBox.getSelectedIndex());
                }
            }
        });

    }
    public void showTable(){

        dtm =new DefaultTableModel();
        String[] titles = {"工号","部门","状态"};
        dtm.setColumnIdentifiers(titles);
        table.setModel(dtm);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();

        // 设置水平方向居中
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,r);
        DefaultTableCellRenderer r1 = new DefaultTableCellRenderer();
        r1.setHorizontalAlignment(JLabel.CENTER);
        r1.setBackground(Color.LIGHT_GRAY);
        table.getTableHeader().setDefaultRenderer(r1);

        String[] content = new String[3];
        try {
            adminChecks = adminservice.getStaff();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Iterator<AdminCheck> iterator = adminChecks.iterator();
        while (iterator.hasNext()){
            AdminCheck adminCheck = iterator.next();
            content[0] = adminCheck.getDept();
            content[1] = adminCheck.getWorkID();

            dtm.addRow(content);
        } for(int i =0;i<table.getRowCount();i++)
        {
            table.setValueAt("正常",i,2);
        }
        //考核按钮监听
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Iterator<AdminCheck> iterater = adminChecks.iterator();
                int n = 0;
                int row = 0;
                while (iterater.hasNext()){
                    AdminCheck adminCheck1 = iterater.next();
                    dept = adminCheck1.getDept();
                    name = adminCheck1.getName();
                    workID = adminCheck1.getWorkID();
                    typeString = (String) table.getValueAt(row,2);
                    row++;
                }
                try {
                    adminChecks = adminservice.getCheckList();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                if (adminChecks != null){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String  date = sdf.format(new Date());
                    Iterator<AdminCheck> iterator1 = adminChecks.iterator();
                    while (iterator1.hasNext()){
                        AdminCheck adminCheck = iterator1.next();
                        String beforeTime = sdf.format(adminCheck.getDate());
                        Date bf = null;
                        Date now = null;
                        try {
                            bf = sdf.parse(beforeTime);
                            now = sdf.parse(date);
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }

                        if (bf.getTime() != now.getTime()){
                            Date dt = null;
                            try {
                                dt= sdf.parse(date);
                            } catch (ParseException e1) {
                                e1.printStackTrace();
                            }
                            java.sql.Date checkDate = new java.sql.Date(dt.getTime());
                            adminCheck = new AdminCheck(dept,name,workID,checkDate,typeString);
                            try {
                                n =adminservice.insertCheck(adminCheck);

                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                        }
                        if (bf.getTime() == now.getTime()){
                            JOptionPane.showMessageDialog(null,"今日已打卡");
                        }
                    }

                }

                if (n != 0){
                    JOptionPane.showMessageDialog(null,"考勤成功");
                }

            }
        });
    }


}
