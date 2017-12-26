package com.managesystem.dao;

import com.managesystem.dao.impl.EnergyDAOImpl;
import com.managesystem.model.EnergyBiao;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by lenovo on 2017/12/26.
 */
public class EnergyBiaoDAOTest {
    private EnergyBiaoDAO energyBiaoDAO;
    @Before
    public void setUp() throws Exception {
     energyBiaoDAO=new EnergyDAOImpl();
    }

    @Test
    public void get() throws Exception {
        List<EnergyBiao> list=energyBiaoDAO.getAll();
       list.forEach(departmentStudentCount -> System.out.println(departmentStudentCount));
    }

}