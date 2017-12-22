package com.managesystem.model;

/**
 * @author lihui
 * Created by 99550 on 2017/12/20.
 */
public class Department {
    private String id;
    private String name;
    private String introduction;
    private String contactway;

    public Department(String id,String name, String introduction, String contactway) {
        this.id = id;
        this.name = name;
        this.introduction = introduction;
        this.contactway = contactway;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    @Override
    public String toString() {
        return "Department{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", introduction='" + introduction + '\'' +
                ", contactway='" + contactway + '\'' +
                '}';
    }
}
