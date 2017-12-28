package com.managesystem.model;

import java.sql.Date;

/**
 * Created by 17297 on 2017/12/24.
 */
public class AdminCheck {
    private String dept;
    private String name;
    private String workID;
    private Date date;
    private String type;

    public AdminCheck(Date date, String type) {

        this.date = date;
        this.type = type;
    }

    public AdminCheck(String dept, String name, String workID, Date date, String type) {
        this.dept = dept;
        this.name = name;
        this.workID = workID;
        this.date = date;
        this.type = type;
    }

    public AdminCheck(String dept, String name, String workID) {
        this.dept = dept;
        this.name = name;
        this.workID = workID;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkID() {
        return workID;
    }

    public void setWorkID(String workID) {
        this.workID = workID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AdminCheck{" +
                "dept='" + dept + '\'' +
                ", name='" + name + '\'' +
                ", workID='" + workID + '\'' +
                ", date=" + date +
                ", type='" + type + '\'' +
                '}';
    }
}
