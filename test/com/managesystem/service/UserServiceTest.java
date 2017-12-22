package com.managesystem.service;

import com.managesystem.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by 99550 on 2017/12/20.
 */
public class UserServiceTest {
    private UserService s= new UserServiceImpl();
    @Test
    public void getAllArea() throws Exception {
        s.getAllArea().forEach(s1 -> System.out.println(s1));
    }
    @Test
    public void getAllDepartment() throws Exception {
        s.getAllDepartment().forEach(s1 -> System.out.println(s1));
    }
    @Test
    public void queryLike() throws Exception {
        s.queryLike("男").forEach(employeeInfo -> System.out.println(employeeInfo));
    }
}