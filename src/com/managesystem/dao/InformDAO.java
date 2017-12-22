package com.managesystem.dao;


import com.managesystem.model.GongGao;
import com.managesystem.model.Inform;

import java.sql.SQLException;

/**
 * Created by lenovo on 2017/12/21.
 */
public interface InformDAO {
    int InsertInform(Inform inform) throws SQLException;
}
