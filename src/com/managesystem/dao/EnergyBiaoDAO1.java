package com.managesystem.dao;

import com.managesystem.model.EnergyBiao1;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by lenovo on 2017/12/26.
 */
public interface EnergyBiaoDAO1 {
    List<EnergyBiao1> getAll() throws SQLException;
}
