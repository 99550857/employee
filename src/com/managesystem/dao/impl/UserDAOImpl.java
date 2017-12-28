package com.managesystem.dao.impl;

import com.managesystem.dao.UserDAO;
import com.managesystem.model.Department;
import utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author lishuang
 * 2017/12/18.
 * 此类是用来获取权限信息的
 * 在建此类之前我们综合考虑了一些问题
 * 比如：管理员也是员工，管理员也可以用自己的工号来登陆查看个人的一些信息
 * 同时用工号和管理员账号登陆时个人信息是同一个
 * 管理员和员工相似的操作都会在此类中实现
 *
 */
public class UserDAOImpl implements UserDAO {
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
    @Override
    public Map<String, List<String>> getUserPermission(String account) throws SQLException {
        Map<String, List<String>> permissionMap = new HashMap<>();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t1.`groupname`,t2.`itemname` FROM t_power_group t1\n" +
                "INNER JOIN t_power_item  t2\n" +
                "ON t1.`groupid` = t2.`groupid`\n" +
                "INNER JOIN t_power_cast t3\n" +
                "ON t2.`itemid` = t3.`itemid`\n" +
                "WHERE t3.`account`= ? ";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, account);
        ResultSet rs = ps.executeQuery();
        List<String> itemList1 = new ArrayList<>();
        List<String> itemList2 = new ArrayList<>();
        List<String> itemList3 = new ArrayList<>();
        List<String> itemList4 = new ArrayList<>();
        Set<String> nameSet = new HashSet<>();
        while (rs.next()) {
            String group_name = rs.getString("groupname");
            nameSet.add(group_name);
            String item_name = rs.getString("itemname");
            if (item_name.contains("考勤")) {
                itemList1.add(item_name);
            }
            if (item_name.contains("通知")) {
                itemList2.add(item_name);
            }
            if (item_name.contains("员工")) {
                itemList3.add(item_name);
            }
            if (item_name.contains("部门")){
                itemList4.add(item_name);
            }
        }
        List<String>[] lists = new List[]{itemList1, itemList2, itemList4,itemList3};
        for (int i = 0, j = 0; i < lists.length; i++) {
            if (lists[i].size() != 0) {
                String name = nameSet.toArray()[j].toString();
                permissionMap.put(name, lists[i]);
                j++;
            } else {
                continue;
            }
        }
        return permissionMap;
    }

    @Override
    public List<Department> getAllDepartment() throws SQLException {
        String sql = "SELECT * FROM t_department ";
        List<Object> list = jdbcUtil.excuteQuery(sql,null);
        List<Department> allDepartmentList = new ArrayList<>();
        for (Object o:list) {
            Map<String,Object> map = (Map<String,Object>) o;
            allDepartmentList.add(
                new Department(
                        (Integer) map.get("id"),
                    map.get("name").toString(),
                    map.get("introduction").toString(),
                    map.get("contactway").toString(),
                        (byte[])map.get("logo"),
                        (Integer)map.get("count")
                )
            );
        }
        return allDepartmentList;
    }

    @Override
    public List<String> getLogInAccount() throws SQLException {
        String sql = "SELECT account FROM t_login_record ";
        List<String> accountList = new ArrayList<>();
        List<Object> list = jdbcUtil.excuteQuery(sql,null);
        for (Object o:list) {
            Map<String,Object> map = (Map<String,Object>)o;
            accountList.add(map.get("account").toString());
        }
        return accountList;
    }


}
