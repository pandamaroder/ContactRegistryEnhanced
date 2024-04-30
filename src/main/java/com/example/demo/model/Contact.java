package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Contact implements Serializable {

    private Long id;
    private String first_name;
    private String last_name;
    private String middle_name;
    private String phone;
    private String email;




    @Override
    public String toString() {
        return String.format("%-3d %-50s %-4s",
                getId(), getFirst_name(), getPhone());
    }

}
