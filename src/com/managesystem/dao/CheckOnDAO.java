package com.managesystem.dao;


import com.managesystem.model.AdminCheck;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 17297 on 2017/12/20.
 */
public interface CheckOnDAO {
    /**
     * 得到所有考勤
     * @return
     * @throws SQLException
     */
    List<AdminCheck> getCheckList() throws SQLException;

    /**
     * 根据工号得到个人考勤
     * @param workID
     * @return
     * @throws SQLException
     */
    List<AdminCheck> getCheck(String workID)throws SQLException;

    /**
     * 增加考勤
     * @param adminCheck
     * @return
     * @throws SQLException
     */
    int insertCheck(AdminCheck adminCheck) throws SQLException;

    /**
     * 得到员工信息进行考勤
     * @return
     * @throws SQLException
     */
    List<AdminCheck> getStaff()throws SQLException;



}
