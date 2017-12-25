package com.managesystem.factory;

import com.managesystem.service.DepartmentService;
import com.managesystem.service.impl.DepartmentServiceImpl;

public class ServiceFactory {
    public static DepartmentService getDepartmentService(){
        return new DepartmentServiceImpl();
    }

}
