package com.managesystem.dao;

import com.managesystem.model.EnergyBiao;

import java.sql.SQLException;
import java.util.List;

/**
 * 获取每个部门能源消费信息
 * Created by lenovo on 2017/12/26.
 */
public interface EnergyBiaoDAO {
   List<EnergyBiao> getAll() throws SQLException;
}
