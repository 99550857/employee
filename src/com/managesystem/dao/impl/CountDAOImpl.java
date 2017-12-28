package com.managesystem.dao.impl;

import com.managesystem.dao.CountDAO;
import com.managesystem.model.Count;
import utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountDAOImpl implements CountDAO {
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
    @Override
    public List<Count> getCount() throws SQLException {

        List<Count> list = new ArrayList<>();
        String sql = "SELECT t2.name,COUNT(*) FROM t_employee t1,t_department t2  WHERE t1.departmentid = t2.id GROUP BY t2.id ";
        Connection connection = jdbcUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            // 取出结果集的第一列数据，就是院系名称
            String departmentName = rs.getString(1);
            System.out.println(departmentName);
            // 取出结果集的第二列数据，就是院系人数
            int n = rs.getInt(2);
            System.out.println(n);
            Count dsc = new Count(departmentName, n);
            list.add(dsc);
        }
        return list;

    }
}
