package com.managesystem.dao.impl;

import com.managesystem.dao.EnergyBiaoDAO1;
import com.managesystem.model.EnergyBiao1;
import utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/12/26.
 */
public class EnergyDAOImpl1 implements EnergyBiaoDAO1 {
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
    @Override
    public List<EnergyBiao1> getAll() throws SQLException {
        List<EnergyBiao1> list1 = new ArrayList<>();
        String sql = "SELECT * FROM t_baobiao1";
        Connection connection = jdbcUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String type = rs.getString(2);
            int n = rs.getInt(3);
            EnergyBiao1 dsc = new EnergyBiao1(type, n);
            list1.add(dsc);
        }
        return list1;
    }
}
