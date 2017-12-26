package com.managesystem.dao.impl;

import com.managesystem.dao.InformDAO;
import com.managesystem.model.GongGao;
import com.managesystem.model.Inform;
import utils.JDBCUtil;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author shenwenwu
 * 2017/12/18.
 * 公告相关底层方法
 * 实现各个操作公告数据的底层方法
 */
public class InformImpl implements InformDAO {
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();

    private List<Inform> getInformList(List<Object> list) {
        List<Inform> informList = new ArrayList<>();
        for (Object o:list) {
            Map<String,Object> map = (Map<String,Object>)o;
            informList.add(new Inform(map.get("departmentid").toString(),
                    map.get("account").toString(),
                    map.get("title").toString(),
                    map.get("content").toString(),
                    map.get("name").toString(),
                    (Timestamp) map.get("date"))
            );
        }
        return informList;
    }

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

    @Override
    public List<Inform> getAllInform() throws SQLException {
        String sql = "SELECT * FROM t_inform ";
        List<Object> list = jdbcUtil.excuteQuery(sql,null);
        return getInformList(list);
    }

    @Override
    public List<Inform> getPartInform(int departmentid) throws SQLException {
        String sql = "SELECT * FROM t_inform WHERE departmentid = ? ";
        List<Object> list = jdbcUtil.excuteQuery(sql,new Object[]{departmentid});
        return getInformList(list);
    }
}
