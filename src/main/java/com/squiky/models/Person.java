package com.squiky.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person {
    private int id;
    @Size(min = 5, max = 200, message = "Full name length should be 5 - 200 chars")
    @NotEmpty(message = "Full name should not be empty")
    private String fullName;
    @Min(value = 1901, message = "Minimal year of birth is 1901")
    private int yearOfBirth;
}
