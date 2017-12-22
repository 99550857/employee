package com.managesystem.dao;

import com.managesystem.model.Admin;
import java.sql.SQLException;

/**
 * Created by 99550 on 2017/12/18.
 */
public interface AdminDAO {

    int setAdmin(Admin admin) throws SQLException;

    Admin get(String account) throws  SQLException;

    String getInfo(String account) throws SQLException;


}
