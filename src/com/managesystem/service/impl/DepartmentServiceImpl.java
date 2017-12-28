package com.managesystem.service.impl;

import com.managesystem.dao.DepartmentDAO;
import com.managesystem.factory.DAOFactory;
import com.managesystem.model.Department;
import com.managesystem.service.DepartmentService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentServiceImpl implements DepartmentService{
    private DepartmentDAO departmentDAO = DAOFactory.getDepartmentDAOInstance();

    @Override
    public int[] batchDelete(List<Integer> ids) {
        int[] result = new int[ids.size()];
        try {
            result = departmentDAO.batchDelete(ids);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    @Override
    public int deleteDepartment(int id) {
        int n = 0;
        try {
            n = departmentDAO.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        try {
            departments = departmentDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    @Override
    public int insert(Department department) {
        int n = 0;
        try {
            n = departmentDAO.insert(department);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public int update(Department department) {
        int n = 0;
        try {
            n = departmentDAO.update(department);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public Department get(Integer id) {
        Department department = null;
        try {
            department = departmentDAO.getDepartment(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;
    }
}
