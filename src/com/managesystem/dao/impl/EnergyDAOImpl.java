package com.managesystem.dao.impl;

import com.managesystem.dao.EnergyBiaoDAO;
import com.managesystem.model.EnergyBiao;
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
public class EnergyDAOImpl implements EnergyBiaoDAO {
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
    @Override
    public List<EnergyBiao> getAll() throws SQLException {
        List<EnergyBiao> list = new ArrayList<>();
        String sql = "SELECT * FROM t_baobiao";
        Connection connection = jdbcUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String departmentName = rs.getString(2);
            int n = rs.getInt(3);
            EnergyBiao dsc = new EnergyBiao(departmentName, n);
            list.add(dsc);
        }
        return list;
    }
}
