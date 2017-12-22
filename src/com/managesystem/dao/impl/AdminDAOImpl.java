package com.managesystem.dao.impl;

import com.managesystem.dao.AdminDAO;
import com.managesystem.model.Admin;
import java.sql.SQLException;
import java.util.Map;

import com.managesystem.model.Employee;
import utils.JDBCUtil;

/**
 * @author shidongyang
 * 2017/12/18.
 *管理员底层方法
 *实现各个操作管理员数据的底层方法
 */
public class AdminDAOImpl implements AdminDAO {
    JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();

    public AdminDAOImpl() {
    }

    public int setAdmin(Admin admin) throws SQLException {
        String sql = "INSERT INTO t_admin_login VALUES (?,?)";
        Object[] params = new Object[]{admin.getAccount(), admin.getPassword()};
        int n = this.jdbcUtil.executeUpdate(sql, params);
        return n;
    }

    @Override
    public Admin get(String account) throws SQLException {
        String sql="SELECT * FROM t_admin_login WHERE account = ? ";
        Map<String,Object> map = jdbcUtil.executeQuerySingle(sql,new Object []{account});
        if (map.size()!=0){
            Admin admin = new Admin(map.get("account").toString(),map.get("password").toString());
            return admin;
        }
        return null;
    }

    @Override
    public String getInfo(String account) throws SQLException {
        String sql="SELECT * FROM t_admin_info WHERE account = ? ";
        Map<String, Object> map = jdbcUtil.executeQuerySingle(sql,new Object[]{account});
        if (map.size()!=0){
           return map.get("employeeid").toString();
        }
        return null;
    }
}
