package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
public class Contact implements Serializable {
    //мапинги json (Jackson)
    private Long id;
    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("middle_name")
    private String middleName;
    private String phone;
    private String email;




    @Override
    public String toString() {
        return String.format("%-3d %-50s %-9s",
                getId(), this.getFirstName(), getPhone());
    }

}
