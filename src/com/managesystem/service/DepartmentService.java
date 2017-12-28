package com.managesystem.service;

import com.managesystem.model.Department;

import java.util.List;

public interface DepartmentService {
    /**
     * 批量删除
     * @param ids
     * @return
     */
    int[] batchDelete(List<Integer> ids);

    /**
     * 单个删除
     * @param id
     * @return
     */
    int deleteDepartment(int id);

    /**
     * 获得所有部门
     * @return
     */
    List<Department> getAllDepartments();

    /**
     * 插入部门
     * @param department
     * @return
     */
    int insert(Department department);

    /**
     * 更新部门信息
     * @param department
     * @return
     */
    int update(Department department);

    /**
     * 获得一个部门信息
     * @param id
     * @return
     */
    Department get(Integer id);
}
