package com.soa.lab2.dto;

import java.io.Serializable;

public class CoordinatesDTO implements Serializable {

    private Long id;

    private Long x;

    private Double y;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
}
