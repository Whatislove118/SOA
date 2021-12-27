package com.lab2es.demo.dto;

import java.io.Serializable;

public class Detail implements Serializable {

    private String detail;


    public Detail(String detail) {
        this.detail = detail;

    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


}
