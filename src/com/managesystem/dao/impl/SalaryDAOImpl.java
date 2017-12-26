package com.managesystem.dao.impl;

import com.managesystem.dao.SalaryDAO;
import com.managesystem.model.PostSalary;
import utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2017/12/24.
 */
public class SalaryDAOImpl implements SalaryDAO
{
    private JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
    @Override
    public PostSalary get(int id) throws SQLException {
        String sql="SELECT * FROM t_postsalary WHERE id = ?";
        Map<String, Object> map = jdbcUtil.executeQuerySingle(sql, new Object[]{id});
        if (map.size() != 0){
            PostSalary salary=new PostSalary(map.get("employeeid").toString(),map.get("name").toString(),map.get("post").toString(),(Integer)map.get("basesalary"),(Integer)map.get("biandongsalary"),
                    (Integer)map.get("endsalary"));
            salary.setId((Integer)map.get("id"));
            return  salary;
        }else {
            return null;
        }
    }

    @Override
    public List<PostSalary> getAll() throws SQLException {
        String sql = "SELECT * FROM t_postsalary ";
        List<Object> list = jdbcUtil.excuteQuery(sql, null);
        List<PostSalary> postSalaries = new ArrayList<>();
        for (Object object : list) {
            Map<String, Object> map = (Map<String, Object>) object;
           PostSalary postSalary=new PostSalary(map.get("employeeid").toString(),map.get("name").toString(),map.get("post").toString(),
                   (Integer) map.get("basesalary"),(Integer)map.get("biandongsalary"),
                   (Integer)map.get("endsalary"));
           postSalary.setId((Integer)map.get("id"));
           postSalaries.add(postSalary);
        }
        return postSalaries;
    }

    @Override
    public int[] batchInsert(List<PostSalary> salaries) throws SQLException {
        int[] result;
        Connection connection = jdbcUtil.getConnection();
        String sql = "INSERT INTO t_postsalary VALUES (?,?,?,?,?,?) ";
        PreparedStatement ps = connection.prepareStatement(sql);
        for (PostSalary postSalary:salaries)
        {
            ps.setString(1, String.valueOf(postSalary.getId()));
            ps.setString(1,postSalary.getPost());
            ps.setString(2,postSalary.getEmployeeid());
            ps.setString(3,postSalary.getName());
            ps.setString(4, String.valueOf(postSalary.getBaseSalary()));
            ps.setString(5, String.valueOf(postSalary.getBiandongSalary()));
            ps.setString(6, String.valueOf(postSalary.getEndSalary()));
            ps.addBatch();
        }
        result = ps.executeBatch();
        jdbcUtil.closeAll();
        return result;
    }
}
