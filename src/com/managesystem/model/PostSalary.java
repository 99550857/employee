package com.managesystem.model;

/**
 * Created by lenovo on 2017/12/24.
 */
public class PostSalary {
    private Integer id;
    private String post;
    private String employeeid;
    private String name;
    private Integer baseSalary;
    private Integer biandongSalary;
    private Integer endSalary;
    public PostSalary(){}

    public PostSalary(String post, String employeeid, String name,
                      Integer baseSalary, Integer biandongSalary, Integer endSalary) {
        this.post = post;
        this.employeeid = employeeid;
        this.name = name;
        this.baseSalary = baseSalary;
        this.biandongSalary = biandongSalary;
        this.endSalary = endSalary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Integer baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Integer getBiandongSalary() {
        return biandongSalary;
    }

    public void setBiandongSalary(Integer biandongSalary) {
        this.biandongSalary = biandongSalary;
    }

    public Integer getEndSalary() {
        return endSalary;
    }

    public void setEndSalary(Integer endSalary) {
        this.endSalary = endSalary;
    }

    @Override
    public String
    toString() {
        return "PostSalary{" +
                "id=" + id +
                ", post='" + post + '\'' +
                ", employeeid='" + employeeid + '\'' +
                ", name='" + name + '\'' +
                ", baseSalary=" + baseSalary +
                ", biandongSalary=" + biandongSalary +
                ", endSalary=" + endSalary +
                '}';
    }
}
