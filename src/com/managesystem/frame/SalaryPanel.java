package com.managesystem.frame;

import com.managesystem.dao.SalaryDAO;
import com.managesystem.dao.impl.SalaryDAOImpl;
import com.managesystem.model.PostSalary;
import utils.MyDemoScrollBarUI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.Iterator;

/**
 * 工资
 * Created by 99550 on 2017/12/22.
 */
public class SalaryPanel extends JPanel{
    private JPanel MainPanel;
    private JTable table1;
    private JButton 添加Button;
    private JButton 删除Button;
    private JScrollPane scrollPane;
    private DefaultTableModel dtm;
    protected java.util.List<PostSalary> postSalaries;

    public SalaryPanel(){
        add(MainPanel);
        showTable();
        scrollPane.getVerticalScrollBar().setUI(new MyDemoScrollBarUI());
    }
    public void showTable(){
        dtm = new DefaultTableModel();
        String[] titles = {"姓名", "职务", "工号", "基本工资","变动工资","最终薪资"};
        dtm.setColumnIdentifiers(titles);
        table1.setModel(dtm);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table1.setDefaultRenderer(Object.class, r);
        DefaultTableCellRenderer r1 = new DefaultTableCellRenderer();
        r1.setHorizontalAlignment(JLabel.CENTER);
        r1.setBackground(Color.LIGHT_GRAY);
        table1.getTableHeader().setDefaultRenderer(r1);
        String[] content = new String[6];
        try {
            SalaryDAO salaryDAO=new SalaryDAOImpl();
            postSalaries=salaryDAO.getAll();
            Iterator<PostSalary> iterator1=postSalaries.iterator();
            while(iterator1.hasNext()){
                PostSalary postSalary=iterator1.next();
                content[0]=postSalary.getEmployeeid();
                content[1]=postSalary.getName();
                content[2]=postSalary.getPost();
                content[3]= String.valueOf(postSalary.getBaseSalary());
                content[4]= String.valueOf(postSalary.getBiandongSalary());
                content[5]= String.valueOf(postSalary.getEndSalary());
                dtm.addRow(content);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("SalaryPanel");
        frame.setContentPane(new SalaryPanel().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.pack();
        frame.setVisible(true);
    }
}
