package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Contact implements Serializable {

    private Long id;
    private String firstname;

    private String email;
    private String tel;



    @Override
    public String toString() {
        return String.format("%-3d %-50s %-4d",
                getId(), getFirstname(), getTel());
    }

}
