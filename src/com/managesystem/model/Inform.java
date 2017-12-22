package com.managesystem.model;

import java.sql.Timestamp;

/**
 * Created by 99550 on 2017/12/22.
 */
public class Inform {
    private String departmentid;
    private String account;
    private String title;
    private String content;
    private String name;
    private Timestamp date;

    public Inform(String departmentid, String account, String title, String content, String name, Timestamp date) {
        this.departmentid = departmentid;
        this.account = account;
        this.title = title;
        this.content = content;
        this.name = name;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Inform{" +
                "departmentid='" + departmentid + '\'' +
                ", account='" + account + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
