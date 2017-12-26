package com.managesystem.model;

/**
 * @author
 * 生成报表，按部门型
 * Created by lenovo on 2017/12/25.
 */
public class EnergyBiao {
    private Integer id;
    private String department;
    private Integer departmentEnergy;

    public EnergyBiao(String department, Integer departmentEnergy) {
        this.department = department;
        this.departmentEnergy = departmentEnergy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getDepartmentEnergy() {
        return departmentEnergy;
    }

    public void setDepartmentEnergy(Integer departmentEnergy) {
        this.departmentEnergy = departmentEnergy;
    }

    @Override
    public String toString() {
        return "EnergyBiao{" +
                "id=" + id +
                ", department='" + department + '\'' +
                ", departmentEnergy=" + departmentEnergy +
                '}';
    }
}
