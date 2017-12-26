package com.managesystem.dao;

import com.managesystem.dao.impl.SalaryDAOImpl;
import com.managesystem.model.PostSalary;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by lenovo on 2017/12/24.
 */
public class SalaryDAOTest {

    SalaryDAO salaryDAO=new SalaryDAOImpl();
    @Test
    public void get() throws Exception {
        System.out.println(salaryDAO.get(1));
    }

    @Test
    public void getAll() throws Exception {
        System.out.println(salaryDAO.getAll());
    }

    @Test
    public void batchInsert() throws Exception {
        List<PostSalary> postSalaryList=new ArrayList<>();
        postSalaryList.add(new PostSalary("经理","111","沈文武",5000
        ,500,5500));
        postSalaryList.add(new PostSalary("经理","111","沈文武",5000
                ,500,5500));
        int[] result=salaryDAO.batchInsert(postSalaryList);
        assertEquals(2,result.length);
    }


}