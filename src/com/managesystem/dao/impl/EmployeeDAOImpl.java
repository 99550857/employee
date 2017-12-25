package com.managesystem.dao.impl;
import com.managesystem.dao.EmployeeDAO;
import com.managesystem.model.Employee;
import com.managesystem.model.EmployeeInfo;
import utils.JDBCUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

/**
 * @author lihui
 *员工层方法
 *实现各个操作员工数据的底层方法
 *  2017/12/18.
 */
public class EmployeeDAOImpl implements EmployeeDAO {
    JDBCUtil jdbcUtil=JDBCUtil.getInitJDBCUtil();

    @Override
    public Employee get(String account) throws SQLException {
        String sql="SELECT * FROM t_staff_login WHERE employeeid = ?";
        Map<String,Object> map =jdbcUtil.executeQuerySingle(sql,new Object[]{account});
        if (map.size()!=0){
            Employee employee=new Employee(map.get("employeeid").toString(),map.get("password").toString());
            return employee;
        }
        return null;
    }

    @Override
    public int setEmployee(Employee employee) throws SQLException {
        String sql="INSERT INTO t_staff_login VALUES(?,?)";
        Object []params = {employee.getEmployeeid(),employee.getPassword()};
        int n= jdbcUtil.executeUpdate(sql,params);
        return n;
    }

    @Override
    public EmployeeInfo getInfo(String account) throws SQLException {
        String sql="SELECT * FROM t_staff_info WHERE employeeid = ? ";
        Map<String,Object> map = jdbcUtil.executeQuerySingle(sql,new Object[]{account});
        if (map.size()!=0){
            EmployeeInfo employeeInfo=new EmployeeInfo(map.get("employeeid").toString(),
                    map.get("departmentid").toString(),map.get("name").toString(),
                    map.get("gender").toString(),map.get("nation").toString(),
                    map.get("province").toString(),map.get("municipality").toString(),
                    map.get("address").toString(),map.get("eduback").toString(),
                    map.get("post").toString(),map.get("tectitle").toString(),
                    map.get("marital_status").toString(),map.get("political_status").toString(),
                    (Date) map.get("entry_time"),map.get("phone").toString(),
                    map.get("email").toString(), (byte[]) map.get("avatar"));
            return employeeInfo;
        }
        return null;
    }

    @Override
    public List<EmployeeInfo> getAll() throws SQLException {
        String sql = "SELECT * FROM t_staff_info ";
        return getList(jdbcUtil.excuteQuery(sql,null));
    }

    @Override
    public List<EmployeeInfo> getList(List<Object> list) throws SQLException {
        List<EmployeeInfo> employeeInfoList = new ArrayList<>();
        for (Object object: list) {
            Map<String,Object> map = (Map<String,Object>) object;
            EmployeeInfo employeeInfo=new EmployeeInfo(map.get("employeeid").toString(),
                    map.get("departmentid").toString(),map.get("name").toString(),
                    map.get("gender").toString(),map.get("nation").toString(),
                    map.get("province").toString(),map.get("municipality").toString(),
                    map.get("address").toString(),map.get("eduback").toString(),
                    map.get("post").toString(),map.get("tectitle").toString(),
                    map.get("marital_status").toString(),map.get("political_status").toString(),
                    (Date) map.get("entry_time"),map.get("phone").toString(),
                    map.get("email").toString(), (byte[]) map.get("avatar"));
            employeeInfoList.add(employeeInfo);
        }
        return employeeInfoList;
    }

    @Override
    public Set<String> getAllArea() throws SQLException {
        Set<String> set = new HashSet<>();
        getAll().forEach(employeeInfo -> set.add(employeeInfo.getProvince()));
        return set;
    }

    @Override
    public List<EmployeeInfo> queryBy(String condition) throws SQLException {
        String sql = "SELECT * FROM t_staff_info " + condition;
        return getList(jdbcUtil.excuteQuery(sql,null));
    }

    @Override
    public String getDepartmentid(String name) throws SQLException {
        String sql = "SELECT * FROM t_department WHERE name=? ";
        Map<String,Object> map= jdbcUtil.executeQuerySingle(sql,new Object[]{name});
        if(map.size()!=0){
            return map.get("id").toString();
        }
        return null;
    }

    @Override
    public List<EmployeeInfo> queryLike(String keyword) throws SQLException {
        String sql = "SELECT * FROM t_staff_info WHERE CONCAT(employeeid,name,gender,nation,eduback,marital_status,political_status) LIKE ? ";
        return getList(jdbcUtil.excuteQuery(sql,new Object[]{"%"+keyword+"%"}));
    }

    @Override
    public int[] batchDelete(List<String> s) throws SQLException {
        int[] result;
        Connection connection = jdbcUtil.getConnection();
        String sql="DELETE FROM t_staff_info WHERE employeeid = ? ";
        PreparedStatement ps=connection.prepareStatement(sql);
        for (String employeeid: s) {
            ps.setString(1,employeeid);
            ps.addBatch();
        }
        result = ps.executeBatch();
        jdbcUtil.closeAll();
        return result;
    }

    @Override
    public int[] batchInsert(List<EmployeeInfo> list) throws SQLException {
        int[] result;
        Connection connection =jdbcUtil.getConnection();
        String sql = "INSERT INTO t_staff_info VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps= connection.prepareStatement(sql);
        for (EmployeeInfo e : list) {
            ps.setString(1, e.getEmployeeid());
            ps.setString(2, e.getDepartmentid());
            ps.setString(3, e.getName());
            ps.setString(4, e.getGender());
            ps.setString(5, e.getNation());
            ps.setString(6, e.getProvince());
            ps.setString(7, e.getMunicipality());
            ps.setString(8, e.getEduback());
            ps.setString(9, e.getPost());
            ps.setString(10, e.getTectitle());
            ps.setString(11, e.getMarital_status());
            ps.setString(12, e.getPolitical_status());
            ps.setDate(13, e.getEntry_time());
            ps.setString(12, e.getPhone());
            ps.setString(12, e.getEmail());
            ps.addBatch();
        }
        result=ps.executeBatch();
        jdbcUtil.closeAll();
        return result;
    }

    @Override
    public String getDepartmentname(String id) throws SQLException {
        String sql="SELECT * FROM t_department WHERE id=?";
        Map<String,Object> map = jdbcUtil.executeQuerySingle(sql,new Object[]{id});
        if(map.size()!=0){
            return map.get("name").toString();
        }
        return null;
    }
}
