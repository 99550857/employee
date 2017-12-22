package com.managesystem.dao.impl;

import com.managesystem.dao.InformDAO;
import com.managesystem.model.GongGao;
import com.managesystem.model.Inform;
import utils.JDBCUtil;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Map;


/**
 * @author shenwenwu
 * 2017/12/18.
 * 公告相关底层方法
 * 实现各个操作公告数据的底层方法
 */
public class InformImpl implements InformDAO {
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();

    @Override
    public int InsertInform(Inform inform) throws SQLException {
        String sql= "INSERT INTO t_inform (departmentid,account,title,content,name,date) VALUES (?,?,?,?,?,?) ";
        int n = jdbcUtil.executeUpdate(sql,new Object[]{
                inform.getDepartmentid(),
                inform.getAccount(),
                inform.getTitle(),
                inform.getContent(),
                inform.getName(),
                inform.getDate()
        });
        return n;
    }
}
