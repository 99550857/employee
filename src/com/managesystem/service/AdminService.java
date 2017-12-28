package com.managesystem.service;



import com.managesystem.model.AdminCheck;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 李爽
 *  on 2017/12/20.
 */
public interface AdminService {

    List<AdminCheck> getCheckList() throws SQLException;

    List<AdminCheck> getCheck(String workID)throws SQLException;

    int insertCheck(AdminCheck adminCheck) throws SQLException;

    List<AdminCheck> getStaff()throws SQLException;
}
