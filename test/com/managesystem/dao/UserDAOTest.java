package com.managesystem.dao;

import com.managesystem.dao.impl.EmployeeDAOImpl;
import com.managesystem.dao.impl.UserDAOImpl;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by 99550 on 2017/12/22.
 */
public class UserDAOTest {


    private UserDAO userDAO=new UserDAOImpl();

    private EmployeeDAO employeeDAO=new EmployeeDAOImpl();

    @Test
    public void getLogInAccount() throws Exception {
        userDAO.getLogInAccount().forEach( s -> System.out.println(s));
    }
    @Test
    public void getUserPermission() throws Exception {
        Map<String,List<String>> map=userDAO.getUserPermission("111");
        for (Map.Entry<String,List<String>> entry:map.entrySet()) {
            System.out.println(entry.getKey());
            entry.getValue().forEach(s -> System.out.println(s));
        }
    }
}