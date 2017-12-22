package com.managesystem.model;

import java.util.Date;

/**
 * @author shenwenwu
 * Created by lenovo on 2017/12/21.
 */
public class GongGao {
    private Integer number;
    private String faburen;
    private Date date;
    private String info;
    private Integer departmentid;
    private String biaoti;

    public GongGao(Integer number, String faburen, Date date, String info, Integer departmentid, String biaoti) {
        this.number = number;
        this.faburen = faburen;
        this.date = date;
        this.info = info;
        this.departmentid = departmentid;
        this.biaoti = biaoti;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getFaburen() {
        return faburen;
    }

    public void setFaburen(String faburen) {
        this.faburen = faburen;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
    }

    public String getBiaoti() {
        return biaoti;
    }

    public void setBiaoti(String biaoti) {
        this.biaoti = biaoti;
    }

    @Override
    public String toString() {
        return "GongGao{" +
                "number=" + number +
                ", faburen='" + faburen + '\'' +
                ", date=" + date +
                ", info='" + info + '\'' +
                ", departmentid=" + departmentid +
                ", biaoti='" + biaoti + '\'' +
                '}';
    }
}
