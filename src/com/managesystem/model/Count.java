package com.managesystem.model;

public class Count {
    private String name;
    private Integer count;

    public Count() {
    }

    public Count(String name, Integer count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Count{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
