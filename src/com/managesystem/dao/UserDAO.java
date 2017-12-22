package com.managesystem.dao;

import com.managesystem.model.Department;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by 99550 on 2017/12/19.
 */
public interface UserDAO {

    Map<String, List<String>> getUserPermission(String account) throws SQLException;

    List<Department> getAllDepartment() throws SQLException;

    List<String> getLogInAccount() throws SQLException;
}
