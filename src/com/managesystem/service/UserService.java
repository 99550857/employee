package com.managesystem.service;


import com.managesystem.model.Department;
import com.managesystem.model.EmployeeInfo;
import com.managesystem.model.Inform;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by 99550 on 2017/12/18.
 */
public interface UserService {

    int[] batchInsert(List<EmployeeInfo> list) ;

    /**
     * 通过管理员账号查找对应员工信息
     * @param account
     * @return
     */
    EmployeeInfo getInfo(String account);


    Map<String,List<String>> getPower(String account);

    /**
     * 登录
     * @param adminNumber
     * @param password
     * @return
     */
    Map<String,Object> login(String adminNumber, String password);

    List<EmployeeInfo> getAll();

    /**
     * 获取所有部门信息
     * @return
     */
    List<Department> getAllDepartment();

    /**
     * 获取所有地区
     * @return
     */
    Set<String> getAllArea();

    String getDepartmentid(String departmentname);

    /**
     * comboBox分组
     * @param condition
     * @return
     */
    List<EmployeeInfo> queryBy(String condition);

    /**
     * 关键字搜索
     * @param keyword
     * @return
     */
    List<EmployeeInfo> queryLike(String keyword);

    int[] batchDelete(List<String> list);

    List<String> getLogInAccount();

    int InsertInform(Inform inform) ;

    List<Inform> getAllInform() throws SQLException;

    List<Inform> getPartInform(int departmentid) throws SQLException;

    String  getDepartmentname(String id) ;

    List<EmployeeInfo> getDepartmentEmployee(String deaprtmentid) throws SQLException;

    Department getDepartment(Integer id) ;
}
