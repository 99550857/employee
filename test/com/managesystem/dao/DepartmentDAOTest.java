package com.managesystem.dao;

import com.managesystem.factory.DAOFactory;
import com.managesystem.model.Department;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DepartmentDAOTest {

    DepartmentDAO departmentDAO;

    @Before
    public void setUp() throws Exception {
        departmentDAO = DAOFactory.getDepartmentDAOInstance();
    }

    @Test
    public void insert() throws Exception {
        Department department = new Department(4,"总经理","总经理","15475896325");
        int n = departmentDAO.insert(department);
        assertEquals(1,n);
    }

    @Test
    public void batchDelete() throws Exception {
        List<Integer> id = new ArrayList<>();
        id.add(4);
        int[] result = departmentDAO.batchDelete(id);
        assertEquals(1,result.length);
    }

    @Test
    public void update() throws Exception {
        Department department = departmentDAO.getDepartment(3);
        department.setIntroduction("hahaha");
        int n = departmentDAO.update(department);
        assertEquals(3,n);
    }

    @Test
    public void getDepartment() throws Exception {

    }

    @Test
    public void getName() throws Exception {
        String name = departmentDAO.getName(1);
        System.out.println(name);
    }

    @Test
    public void getAllName() throws Exception {
        List<String> nameList = departmentDAO.getAllName();
        nameList.forEach(s -> System.out.println(s));
    }

}