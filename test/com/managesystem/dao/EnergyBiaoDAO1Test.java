package com.managesystem.dao;

import com.managesystem.dao.impl.EnergyDAOImpl1;
import com.managesystem.model.EnergyBiao1;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by lenovo on 2017/12/26.
 */
public class EnergyBiaoDAO1Test {
    private EnergyBiaoDAO1 energyBiaoDAO1;
    @Before
    public void setUp() throws Exception {
        energyBiaoDAO1=new EnergyDAOImpl1();
    }

    @Test
    public void getAll() throws Exception {
        List<EnergyBiao1> list=energyBiaoDAO1.getAll();
        list.forEach(departmentStudentCount -> System.out.println(departmentStudentCount));
    }

}