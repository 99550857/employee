package com.managesystem.frame;

import com.managesystem.factory.ServiceFactory;
import com.managesystem.model.AdminCheck;
import com.managesystem.ui.MyComboBoxEditor;
import com.managesystem.ui.TableNew;
import utils.DialogDatePicker;

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
import com.managesystem.service.*;
import static java.awt.BorderLayout.CENTER;

/**
 * Created by 99550 on 2017/12/19.
 */
public class AttendancePanel extends JPanel implements ActionListener{
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JPanel card1,card2;
    private JButton jButton,jButton1;
    private JButton testButton;
    private JScrollPane scrollPane;
    private TableNew table;
    private DefaultTableModel dtm;
    private JPanel topPanel;
    private JPanel checkPanel;
    private JComboBox jComboBox;
    private int row;
    private JButton dateButton;
    private JLabel dateLabel;
    private JButton checkButton;
    private JTextField txtDate;
    private String dateString;
    private String typeString;
    private String numberString;
    private String  workID;
    private String dept;
    private String name;

    private List<AdminCheck> adminChecks;
    private AdminService adminService = ServiceFactory.getAdminServiceInstance();
    public AttendancePanel(List<String> function) {
        super();
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());


        scrollPane = new JScrollPane();
        table = new TableNew();
        dtm = new DefaultTableModel();
        txtDate = new JTextField();
        jComboBox = new JComboBox();

        jButton = new JButton("考勤");
        jButton1 = new JButton("统计");
        testButton = new JButton("测试");

        dateLabel = new JLabel();
        checkButton = new JButton("完成考勤");
        checkButton.addActionListener(this);
        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        // checkPanel= new JPanel(new FlowLayout(RIGHT));
        dateButton = new JButton("选择日期");
        dateButton.addActionListener(this);

        cardLayout = new CardLayout();
        cardPanel = new JPanel();
        cardPanel.setLayout(cardLayout);
        add(cardPanel,BorderLayout.CENTER);

        card1 = new JPanel();
        card2 = new JPanel();
        cardPanel.add("1",card1);
        cardPanel.add("2",card2);
        jButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel,"1");
            }
        });
        jButton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel,"2");
            }
        });
        topPanel.add(jButton);
        topPanel.add(jButton1);
        function.forEach(s -> topPanel.add(new JLabel(s)));
        add(topPanel,BorderLayout.NORTH);

        //card1添加组件
        card1.add(dateButton);
        card1.add(dateLabel);
        scrollPane.add(table);
        card1.add(checkButton);
        scrollPane.setViewportView(table);
        card1.add(scrollPane,CENTER);

        //card2添加组件
        card2.add(testButton);
//        add(checkPanel,BorderLayout.SOUTH);



        init();
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
            adminChecks = adminService.getStaff();
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
                int row = 0;
                int n =0;
                while (iterater.hasNext()){
                    AdminCheck adminCheck = iterater.next();
                    dept = adminCheck.getDept();
                    name = adminCheck.getName();
                    workID = adminCheck.getWorkID();
                    typeString = (String) table.getValueAt(row,2);

                    row++;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = null;
                    try {
                        date = sdf.parse(dateString);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                    java.sql.Date checkDate = new java.sql.Date(date.getTime());
                    adminCheck = new AdminCheck(dept,name,workID,checkDate,typeString);
                    try {
                        System.out.println(adminCheck);
                        n = adminService.insertCheck(adminCheck);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                }

                if (n != 0) {
                    JOptionPane.showMessageDialog(null, "今日考勤成功！！");
                } else {
                    JOptionPane.showMessageDialog(null, "今日考勤失败！！");
                }
                row = 0;
            }
        });

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dateButton){
            new DialogDatePicker(true, txtDate, 500, 100);
            //获得日期
            dateString = txtDate.getText();
            dateLabel.setText(dateString);
        }

    }
}
