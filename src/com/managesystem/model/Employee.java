package com.managesystem.model;

/**
 * @author lishuang
 * Created by 99550 on 2017/12/18.
 */
public class Employee {
    private String employeeid;
    private String password;

    public Employee(String employeeid, String password) {
        this.employeeid = employeeid;
        this.password = password;
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeid='" + employeeid + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
