package com.managesystem.dao;


import com.managesystem.model.GongGao;
import com.managesystem.model.Inform;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by lenovo on 2017/12/21.
 */
public interface InformDAO {
    int InsertInform(Inform inform) throws SQLException;

    List<Inform> getAllInform() throws SQLException;

    List<Inform> getPartInform(int departmentid) throws SQLException;
}
