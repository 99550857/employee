package com.managesystem.dao;

import com.managesystem.model.Count;

import java.sql.SQLException;
import java.util.List;

public interface CountDAO {
    /**
     * 院系人数
     * @return
     * @throws SQLException
     */
    List<Count> getCount() throws SQLException;
}
