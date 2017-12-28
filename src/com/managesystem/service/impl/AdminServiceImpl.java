package com.managesystem.service.impl;


import com.managesystem.dao.CheckOnDAO;
import com.managesystem.model.AdminCheck;
import com.managesystem.service.AdminService;
import com.managesystem.factory.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 17297 on 2017/12/24.
 */
public class AdminServiceImpl implements AdminService {
    private CheckOnDAO checkOnDAO = DAOFactory.getCheckOnDAOInstance();
    @Override
    public List<AdminCheck> getCheckList() throws SQLException {
        return checkOnDAO.getCheckList();
    }

    @Override
    public List<AdminCheck> getCheck(String workID) throws SQLException {
        return checkOnDAO.getCheck(workID);
    }

    @Override
    public int insertCheck(AdminCheck adminCheck) throws SQLException {
        return checkOnDAO.insertCheck(adminCheck);
    }

    @Override
    public List<AdminCheck> getStaff() throws SQLException {
        return checkOnDAO.getStaff();
    }
}
