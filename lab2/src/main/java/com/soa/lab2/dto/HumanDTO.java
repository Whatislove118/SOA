package com.soa.lab2.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.soa.lab2.exceptions.ValidationException;
import org.springframework.http.HttpStatus;


import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HumanDTO implements Serializable {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    private Long id;

    private Double height;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private String birthday;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Date getBirthday() throws ValidationException {
        try {
            return this.formatter.parse(birthday);
        }catch (ParseException e){
            e.printStackTrace();
            throw new ValidationException("Wrong format of date. Should be - yyyy-MM-dd", HttpStatus.BAD_REQUEST);
        }

    }

    public void setBirthday(Date birthday) {
        this.birthday = this.formatter.format(birthday);
    }
}
