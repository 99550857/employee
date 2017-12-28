package com.managesystem.factory;

import com.managesystem.dao.CheckOnDAO;
import com.managesystem.dao.DepartmentDAO;
import com.managesystem.dao.impl.CheckOnDAOImpl;
import com.managesystem.dao.impl.DepartmentDAOImpl;

public class DAOFactory {
    public static DepartmentDAO getDepartmentDAOInstance(){
        return new DepartmentDAOImpl();
    }
    public static CheckOnDAO getCheckOnDAOInstance(){
        return new CheckOnDAOImpl();
    }
}
