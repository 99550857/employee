package com.managesystem.dao;

import com.managesystem.dao.impl.UserDAOImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by 99550 on 2017/12/22.
 */
public class UserDAOTest {
    private UserDAO userDAO=new UserDAOImpl();
    @Test
    public void getLogInAccount() throws Exception {
        userDAO.getLogInAccount().forEach( s -> System.out.println(s));
    }

}