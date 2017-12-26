package com.managesystem.dao;


import com.managesystem.model.Employee;
import com.managesystem.model.EmployeeInfo;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * Created by 99550 on 2017/12/18.
 */
public interface EmployeeDAO {
    Employee get(String account) throws  SQLException;

    int setEmployee(Employee employee) throws SQLException;

    EmployeeInfo getInfo(String account) throws  SQLException;

    List<EmployeeInfo> getAll() throws SQLException;

    List<EmployeeInfo> getList(List<Object> list) throws SQLException;

    Set<String> getAllArea() throws SQLException;

    /**
     * 分类
     * @param condition
     * @return
     * @throws SQLException
     */
    List<EmployeeInfo> queryBy(String condition) throws SQLException;

    String getDepartmentid(String name) throws  SQLException;

    /**
     * 关键字搜索
     * @param keyword
     * @return
     * @throws SQLException
     */
    List<EmployeeInfo> queryLike(String keyword) throws SQLException;

    /**
     * 删除
     * @param s
     * @return
     * @throws SQLException
     */
    int[] batchDelete(List<String> s) throws SQLException;

    /**
     * 批量导入
     * @param list
     * @return
     * @throws SQLException
     */
    int[] batchInsert(List<EmployeeInfo> list) throws SQLException;


    String getDepartmentname(String id) throws SQLException;

    List<EmployeeInfo> getDepartmentEmployee(String deaprtmentid) throws SQLException;
}
