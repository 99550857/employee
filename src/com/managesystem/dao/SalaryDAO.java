package com.managesystem.dao;

import com.managesystem.model.PostSalary;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by lenovo on 2017/12/24.
 */
public interface SalaryDAO {
    PostSalary get(int id)throws SQLException;

    List<PostSalary> getAll()throws SQLException;

    int[] batchInsert(List<PostSalary> salaries) throws SQLException;
}
