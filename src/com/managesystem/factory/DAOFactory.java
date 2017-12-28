package com.managesystem.factory;

import com.managesystem.dao.CountDAO;
import com.managesystem.dao.DepartmentDAO;
import com.managesystem.dao.impl.CountDAOImpl;
import com.managesystem.dao.impl.DepartmentDAOImpl;

public class DAOFactory {
    public static DepartmentDAO getDepartmentDAOInstance(){
        return new DepartmentDAOImpl();
    }

    public static CountDAO getCountDAOInstance(){
        return new CountDAOImpl();
    }

}
