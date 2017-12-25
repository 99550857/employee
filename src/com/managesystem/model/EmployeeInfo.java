package com.managesystem.model;

import java.sql.Date;
import java.util.Arrays;

/**
 * @author lihui
 * Created by 99550 on 2017/12/18.
 */
public class EmployeeInfo {
    private String employeeid;
    private String departmentid;
    private String name;
    private String gender;
    private String nation;
    private String province;
    private String municipality;
    private String address;
    private String eduback;
    private String post;
    private String tectitle;
    private String marital_status;
    private String political_status;
    private Date entry_time;
    private String phone;
    private String email;
    private byte []avatar;

    public EmployeeInfo() {
    }

    public EmployeeInfo(String employeeid, String departmentid, String name, String gender, String nation, String province, String municipality, String address, String eduback, String post, String tectitle, String marital_status, String political_status, Date entry_time, String phone, String email, byte[] avatar) {
        this.employeeid = employeeid;
        this.departmentid = departmentid;
        this.name = name;
        this.gender = gender;
        this.nation = nation;
        this.province = province;
        this.municipality = municipality;
        this.address = address;
        this.eduback = eduback;
        this.post = post;
        this.tectitle = tectitle;
        this.marital_status = marital_status;
        this.political_status = political_status;
        this.entry_time = entry_time;
        this.phone = phone;
        this.email = email;
        this.avatar = avatar;
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEduback() {
        return eduback;
    }

    public void setEduback(String eduback) {
        this.eduback = eduback;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getTectitle() {
        return tectitle;
    }

    public void setTectitle(String tectitle) {
        this.tectitle = tectitle;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public String getPolitical_status() {
        return political_status;
    }

    public void setPolitical_status(String political_status) {
        this.political_status = political_status;
    }

    public Date getEntry_time() {
        return entry_time;
    }

    public void setEntry_time(Date entry_time) {
        this.entry_time = entry_time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "EmployeeInfo{" +
                "employeeid='" + employeeid + '\'' +
                ", departmentid='" + departmentid + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", nation='" + nation + '\'' +
                ", province='" + province + '\'' +
                ", municipality='" + municipality + '\'' +
                ", address='" + address + '\'' +
                ", eduback='" + eduback + '\'' +
                ", post='" + post + '\'' +
                ", tectitle='" + tectitle + '\'' +
                ", marital_status='" + marital_status + '\'' +
                ", political_status='" + political_status + '\'' +
                ", entry_time=" + entry_time +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", avatar=" + Arrays.toString(avatar) +
                '}';
    }
}
