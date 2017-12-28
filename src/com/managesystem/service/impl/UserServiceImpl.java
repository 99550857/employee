package com.managesystem.service.impl;

import com.managesystem.dao.*;


import com.managesystem.dao.impl.*;
import com.managesystem.model.*;
import com.managesystem.service.UserService;
import utils.MD5;

import java.sql.SQLException;
import java.util.*;

/**
 * @author lihui
 * service层连接底层各个方法
 */
public class UserServiceImpl implements UserService {
    AdminDAO adminDAO=new AdminDAOImpl();
    UserDAO userDAO =new UserDAOImpl();
    EmployeeDAO employeeDAO=new EmployeeDAOImpl();
    InformDAO informDAO = new InformImpl();
    DepartmentDAO departmentDAO = new DepartmentDAOImpl();

    @Override
    public int[] batchInsert(List<EmployeeInfo> list) {
        try {
            return employeeDAO.batchInsert(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new int[0];
    }

    @Override
    public EmployeeInfo getInfo(String account) {
        EmployeeInfo employeeInfo = null;
        try {
            if(adminDAO.getInfo(account)!=null) {
                try {
                    employeeInfo = employeeDAO.getInfo(adminDAO.getInfo(account));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else {
                employeeInfo=employeeDAO.getInfo(account);
            }
            return employeeInfo;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 权限获取
     * @param account
     * @return
     */
    @Override
    public Map<String, List<String>> getPower(String account) {
        try {
            return userDAO.getUserPermission(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 登录
     * @param account
     * @param password
     * @return
     */
    @Override
    public Map<String, Object> login(String account, String password) {
        Map<String, Object> map = new HashMap<>();
        password= MD5.getMD5(password);
        Admin admin = null;
        Employee employee=null;
        try {
            admin = adminDAO.get(account);
            employee=employeeDAO.get(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //找到了指定工号的管理员
        if (admin != null) {
            //比对密码
            if (admin.getPassword().equals(password)) {
                //登录成功
                map.put("info", "管理员");
                map.put("admin", admin);
            } else {
                //工号对，密码不对
                map.put("info", "密码错误");
            }
        } else if (employee!=null){
            if (employee.getPassword().equals(password)) {
                //登录成功
                map.put("info", "员工");
                map.put("employee", employee);
            } else {
                //工号对，密码不对
                map.put("info", "密码错误");
            }
        } else {
            map.put("info", "账号不存在");
        }
        return map;
    }

    /**
     * 获取所有员工信息
     * @return
     */
    @Override
    public List<EmployeeInfo> getAll() {
        try {
            return employeeDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *获取所有部门
     * @return
     */
    @Override
    public List<Department> getAllDepartment() {
        try {
            return userDAO.getAllDepartment();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取所有员工地区
     * @return
     */
    @Override
    public Set<String> getAllArea() {
        try {
            return employeeDAO.getAllArea();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据部门名称获取编号
     * @param departmentname
     * @return
     */
    @Override
    public String getDepartmentid(String departmentname) {

        try {
            return employeeDAO.getDepartmentid(departmentname);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 分组
     * @param condition
     * @return
     */
    @Override
    public List<EmployeeInfo> queryBy(String condition) {
        try {
            return employeeDAO.queryBy(condition);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 搜索
     * @param keyword
     * @return
     */
    @Override
    public List<EmployeeInfo> queryLike(String keyword) {
        try {
            return employeeDAO.queryLike(keyword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 批量删除
     * @param list
     * @return
     */
    @Override
    public int[] batchDelete(List<String> list) {
        try {
            return employeeDAO.batchDelete(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new int[0];
    }

    /**
     * 获取曾经登陆过人员的账号
     * @return
     */
    @Override
    public List<String> getLogInAccount() {
        try {
            return userDAO.getLogInAccount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * 将公告信息存入数据库
     * @param inform
     * @return
     */
    @Override
    public int InsertInform(Inform inform) {
        try {
            return informDAO.InsertInform(inform);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Inform> getAllInform() {
        try {
            return informDAO.getAllInform();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Inform> getPartInform(int departmentid)  {
        try {
            return informDAO.getPartInform(departmentid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getDepartmentname(String id) {
        try {
            return employeeDAO.getDepartmentname(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<EmployeeInfo> getDepartmentEmployee(String deaprtmentid) throws SQLException {
        return employeeDAO.getDepartmentEmployee(deaprtmentid);
    }

    @Override
    public Department getDepartment(Integer id) {
        try {
            return departmentDAO.getDepartment(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
