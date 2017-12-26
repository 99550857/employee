package com.managesystem.service.impl;

import com.managesystem.dao.DepartmentDAO;
import com.managesystem.factory.DAOFactory;
import com.managesystem.service.DepartmentService;

import java.sql.SQLException;
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
}
