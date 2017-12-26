package com.managesystem.model;

/**
 * Created by lenovo on 2017/12/26.
 */
public class EnergyBiao1 {
    private Integer id;
    private String type;
    private Integer energy;

    public EnergyBiao1(String type, Integer energy) {
        this.type = type;
        this.energy = energy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getEnergy() {
        return energy;
    }

    public void setEnergy(Integer energy) {
        this.energy = energy;
    }

    @Override
    public String toString() {
        return "EnergyBiao1{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", energy=" + energy +
                '}';
    }
}
