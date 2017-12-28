package com.managesystem.dao.impl;


import com.managesystem.dao.CheckOnDAO;
import com.managesystem.model.AdminCheck;
import utils.JDBCUtil;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 17297 on 2017/12/20.
 */
public class CheckOnDAOImpl implements CheckOnDAO {
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();

    @Override
    public List<AdminCheck> getCheckList() throws SQLException {
        String sql = "SELECT * FROM t_check_log  ";
        List<Object> list = jdbcUtil.excuteQuery(sql, null);
        List<AdminCheck> adminChecks = new ArrayList<>();
        for (Object object : list) {
            Map<String, Object> map = (Map<String, Object>) object;
            AdminCheck adminCheck = new AdminCheck(map.get("dept").toString(),
                    map.get("name").toString(),
                    map.get("workID").toString(),
                    (Date) map.get("date"),
                    map.get("type").toString());
            adminChecks.add(adminCheck);
        }
        return adminChecks;
    }

    @Override
    public List<AdminCheck> getCheck(String workID) throws SQLException {
        String sql = "SELECT * FROM t_check_log WHERE workID = ? ";
        List<Object> list = jdbcUtil.excuteQuery(sql, new Object[]{workID});
        List<AdminCheck> adminChecks = new ArrayList<>();
        for (Object object : list) {
            Map<String, Object> map = (Map<String, Object>) object;
            AdminCheck adminCheck= new AdminCheck((Date) map.get("date"),
                    map.get("type").toString());
                adminChecks.add(adminCheck);
        }
        return adminChecks;
    }

    @Override
    public int insertCheck(AdminCheck adminCheck) throws SQLException {
        int n;
        String sql = "INSERT INTO t_check_log (dept,name,workID,date,type) VALUES(?,?,?,?,?)";
        Object[] params = {
                adminCheck.getDept(),
                adminCheck.getName(),
                adminCheck.getWorkID(),
                adminCheck.getDate(),
                adminCheck.getType()
        };
        n = jdbcUtil.executeUpdate(sql, params);
        return n;
    }

    @Override
    public List<AdminCheck> getStaff() throws SQLException {
        String sql = "SELECT employeeid,departmentid,name FROM t_staff_info ";
        List<Object> list = jdbcUtil.excuteQuery(sql, null);
        List<AdminCheck> adminChecks = new ArrayList<>();
        for (Object object : list) {
            Map<String, Object> map = (Map<String, Object>) object;
            AdminCheck adminCheck = new AdminCheck(
                    map.get("employeeid").toString(),
                    map.get("departmentid").toString(),
                    map.get("name").toString());
            adminChecks.add(adminCheck);

        }
        return adminChecks;
    }

    @Override
    public List<AdminCheck> getDate() throws SQLException {
        String sql = "SELECT * FROM t_check_log  WHERE TO_DAYS(date) = TO_DAYS(now()) ";
        List<Object> list = jdbcUtil.excuteQuery(sql, null);
        List<AdminCheck> adminChecks = new ArrayList<>();
        for (Object object : list) {
            Map<String, Object> map = (Map<String, Object>) object;
            AdminCheck adminCheck = new AdminCheck((Date) map.get("date"));
            adminChecks.add(adminCheck);
        }

        return adminChecks;
    }

}
