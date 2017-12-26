package com.managesystem.frame;

import com.managesystem.dao.DepartmentDAO;
import com.managesystem.factory.DAOFactory;
import com.managesystem.factory.ServiceFactory;
import com.managesystem.model.Department;
import com.managesystem.service.DepartmentService;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static javax.swing.SwingConstants.CENTER;

public class DepartmentPanel extends JPanel {
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel dtm;
    private DefaultTableCellRenderer renderer,renderer1;
    private java.util.List<Department> departments;
    Iterator<Department> iterater = null;
    private DepartmentDAO departmentDAO = DAOFactory.getDepartmentDAOInstance();
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JComboBox departmentComboBox;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private List<String> names;
    Iterator<String> iterator = null;
    private int[] rows;
    private DepartmentService departmentService = ServiceFactory.getDepartmentService();


    public DepartmentPanel() {
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());
        table = new JTable();
        scrollPane = new JScrollPane();
        dtm = new DefaultTableModel();
        renderer = new DefaultTableCellRenderer();
        renderer1 = new DefaultTableCellRenderer();
        scrollPane.setViewportView(table);

        topPanel = new JPanel();
        bottomPanel = new JPanel();


        init();
        add(scrollPane, CENTER);

        topPanel.setLayout(new FlowLayout());
        bottomPanel.setLayout(new FlowLayout());
        topPanel.setSize(1766,100);
        bottomPanel.setSize(1766,100);
        add(topPanel,BorderLayout.NORTH);
        add(bottomPanel,BorderLayout.SOUTH);

    }
    private void init(){
        departmentTable();
        departmentComboBox();

        addButton = new JButton("增加");
        deleteButton = new JButton("删除");
        updateButton = new JButton("修改");
        //departmentComboBox = new JComboBox();
        topPanel.add(addButton);
        topPanel.add(departmentComboBox);
        bottomPanel.add(updateButton);
        bottomPanel.add(deleteButton);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //选中多行
                rows = table.getSelectedRows();
            }
        });

        /**
         * 为删除按钮添加监听
         */
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.List<Integer> ids = new ArrayList<>();
                //遍历选中的多行
                for (int i : rows) {
                    System.out.println(i);
                    ids.add((Integer) table.getValueAt(i,0));
                }
                //result容量最大就是记录总数
                int[] result = new int[departments.size()];
                //调用批量处理删除方法，得到一个整型数组（受影响的记录行）
                result = departmentService.batchDelete(ids);
                if (result.length != 0){
                    JOptionPane.showMessageDialog(null,"删除成功");
                    //从表格模型中移除已经删掉的记录
                    for (int i = rows.length - 1; i >= 0; i--){
                        dtm.removeRow(rows[i]);
                    }
                }else {
                    JOptionPane.showMessageDialog(null,"删除失败");
                }
            }
        });



        /**
         * 新增部门
         */
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewDepartmentFrame();
            }
        });





    }

    private void departmentComboBox() {
        try {
            names = departmentDAO.getAllName();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(names);
        departmentComboBox = new JComboBox();
        names.forEach(strings -> departmentComboBox.addItem(strings));
    }

    private void departmentTable() {
        dtm = new DefaultTableModel();
        String[] titles = {"部门编号","部门名称","部门描述","联系电话"};
        dtm.setColumnIdentifiers(titles);
        table.setModel(dtm);
        renderer.setHorizontalAlignment(CENTER);
        table.setDefaultRenderer(Object.class,renderer);
        renderer1.setHorizontalAlignment(CENTER);
        renderer.setBackground(Color.LIGHT_GRAY);
        table.getTableHeader().setDefaultRenderer(renderer1);
        String[] content = new String[4];
        try {
            departments = departmentDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        iterater = departments.iterator();
        while (iterater.hasNext()){
            Department department = iterater.next();
            content[0] = String.valueOf(department.getId());
            content[1] = department.getName();
            content[2] = department.getIntroduction();
            content[3] = department.getContactway();
            dtm.addRow(content);
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("部门管理");
        frame.setSize(1766,600);
        frame.setLocationRelativeTo(null);
        frame.add(new DepartmentPanel());
        frame.setVisible(true);
    }



}
