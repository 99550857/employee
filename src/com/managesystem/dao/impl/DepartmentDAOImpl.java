package com.managesystem.dao.impl;

import com.managesystem.dao.DepartmentDAO;
import com.managesystem.model.Department;
import utils.JDBCUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DepartmentDAOImpl implements DepartmentDAO{
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();

    @Override
    public int insert(Department department) throws SQLException {
        String sql = "INSERT INTO t_department VALUES (?,?,?,?) ";
        Object[] params = {department.getId(),department.getName(),department.getIntroduction(),department.getContactway()};
        int n = jdbcUtil.executeUpdate(sql,params);
        return n;
    }

    @Override
    public int[] batchDelete(List<Integer> list) throws SQLException {
        int[] result;
        Connection connection = jdbcUtil.getConnection();
        String sql = "DELETE FROM t_department WHERE id = ? ";
        PreparedStatement ps = connection.prepareStatement(sql);
        for (int id : list){
            ps.setInt(1,id);
            ps.addBatch();
        }
        //执行批量更新操作
        result = ps.executeBatch();
        jdbcUtil.closeAll();
        return result;
    }

    @Override
    public int update(Department department) throws SQLException {
        String sql = "UPDATE t_department SET introduction = ? ";
        Object[] params = {department.getIntroduction()};
        int n = jdbcUtil.executeUpdate(sql,params);
        return n;
    }

    @Override
    public Department getDepartment(Integer id) throws SQLException {
        String sql = "SELECT * FROM t_department WHERE id = ? ";
        Map<String,Object> map = jdbcUtil.executeQuerySingle(sql,new Object[]{id});
        if (map.size() != 0) {
            Department department = new Department((Integer)map.get("id"),
                                                    map.get("name").toString(),
                                                    map.get("introduction").toString(),
                                                    map.get("contactway").toString());
            return department;
        }else {
            return null;
        }
    }

    @Override
    public List<Department> getAll() throws SQLException {
        String sql = "SELECT * FROM t_department ";
        List<Object> list = jdbcUtil.excuteQuery(sql,null);
        return getDepartmentList(list);
    }

    @Override
    public String getName(Integer id) throws SQLException {
        String sql = "SELECT * FROM t_department WHERE id = ? ";
        Map<String,Object> map = jdbcUtil.executeQuerySingle(sql,new Object[]{id});
        if (map.size() != 0){
            String name = new String(map.get("name").toString());
            return name;
        }else {
            return null;
        }
    }

    @Override
    public List<String> getAllName() throws SQLException {
        String sql = "SELECT * FROM t_department ";
        List<Object> list = jdbcUtil.excuteQuery(sql,null);
        return getNameList(list);

    }


    private List<String> getNameList(List<Object> list){
        List<String> names = new ArrayList<>();
        for (Object object : list){
            Map<String, Object> map = (Map<String, Object>) object;
            String name = new String(map.get("name").toString());
            names.add(name);
        }
        return names;
    }

    /**
     * 封装
     * @param list
     * @return
     */
    private List<Department> getDepartmentList(List<Object> list){
        List<Department> departments = new ArrayList<>();
        for (Object object : list){
            Map<String, Object> map = (Map<String, Object>) object;
            Department department = new Department((Integer) map.get("id"),
                                                    map.get("name").toString(),
                                                    map.get("introduction").toString(),
                                                    map.get("contactway").toString());
            departments.add(department);
        }
        return departments;
    }


}
