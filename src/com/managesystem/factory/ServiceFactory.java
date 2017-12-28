package com.managesystem.factory;

import com.managesystem.service.AdminService;
import com.managesystem.service.DepartmentService;
import com.managesystem.service.impl.AdminServiceImpl;
import com.managesystem.service.impl.DepartmentServiceImpl;

public class ServiceFactory {
    public static DepartmentService getDepartmentService(){
        return new DepartmentServiceImpl();
    }
    public static AdminService getAdminServiceInstance(){
        return new AdminServiceImpl();
    }

}
