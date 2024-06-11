package com.squiky.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private int id;
    @Size(min = 2, max = 200, message = "Title length should be 2 - 200 chars")
    @NotEmpty(message = "Title should not be empty")
    private String title;
    @Size(min = 2, max = 200, message = "Author name should be 2 - 200 chars")
    @NotEmpty(message = "Author should not be empty")
    private String author;
    @Max(value = 2024, message = "Year of publishing should be 1 - 2024")
    @Min(value = 1, message = "Year of publishing should be 1 - 2024")
    private int yearOfPublishing;
    private Integer personOwnerId;
}
