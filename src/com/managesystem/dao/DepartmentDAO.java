package com.managesystem.dao;

import com.managesystem.model.Department;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentDAO {
    /**
     * 增加新部门
     * @param department
     * @return
     * @throws SQLException
     */
    int insert(Department department) throws SQLException;

    /**
     * 批量删除部门信息
     * @param list
     * @return
     * @throws SQLException
     */
    int[] batchDelete(List<Integer> list) throws SQLException;

    /**
     * 修改部门信息
     * @param department
     * @return
     * @throws SQLException
     */
    int update(Department department) throws SQLException;

    /**
     * 查找部门信息
     * @param id
     * @return
     * @throws SQLException
     */
    Department getDepartment(Integer id) throws SQLException;

    /**
     * 查找所有部门信息
     * @return
     * @throws SQLException
     */
    List<Department> getAll() throws SQLException;

    /**
     * 通过部门id获取部门名称
     * @param id
     * @return
     * @throws SQLException
     */
    String getName(Integer id) throws SQLException;


    /**
     * 查询所有部门名称
     * @return
     * @throws SQLException
     */
    List<String> getAllName() throws SQLException;

    /**
     * 单个删除部门
     * @param id
     * @return
     * @throws SQLException
     */
    int delete(int id) throws SQLException;


}
