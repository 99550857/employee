package com.managesystem.model;

import java.sql.Date;

/**
 * Created by 17297 on 2017/12/20.
 */
public class CheckOn {

    private String workID;
    private Date date;
    private String check_or_no;



    public CheckOn(Date date, String check_or_no) {
        this.date = date;
        this.check_or_no = check_or_no;
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

    public String getCheck_or_no() {
        return check_or_no;
    }

    public void setCheck_or_no(String check_or_no) {
        this.check_or_no = check_or_no;
    }


    @Override
    public String toString() {
        return "CheckOn{" +
                "workID='" + workID + '\'' +
                ", date=" + date +
                ", check_or_no='" + check_or_no + '\'' +
                '}';
    }
}
