package com.managesystem.model;

import java.util.Arrays;

/**
 * @author lihui
 * Created by 99550 on 2017/12/20.
 */
public class Department {
    private Integer id;
    private String name;
    private String introduction;
    private String contactway;
    private byte[] logo;
    private Integer count;


    public Department(Integer id, String name, String introduction, String contactway, byte[] logo, Integer count) {
        this.id = id;
        this.name = name;
        this.introduction = introduction;
        this.contactway = contactway;
        this.logo = logo;
        this.count = count;
    }

    public Department() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getContactway() {
        return contactway;
    }

    public void setContactway(String contactway) {
        this.contactway = contactway;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", introduction='" + introduction + '\'' +
                ", contactway='" + contactway + '\'' +
                ", logo=" + Arrays.toString(logo) +
                ", count=" + count +
                '}';
    }
}