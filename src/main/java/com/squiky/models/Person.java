package com.squiky.models;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
@NoArgsConstructor
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "fullname")
    @Size(min = 5, max = 200, message = "Full name length should be 5 - 200 chars")
    private String fullName;

    @Column(name = "year_of_birth")
    @Min(value = 1901, message = "Minimal year of birth is 1901")
    private int yearOfBirth;

    @OneToMany(mappedBy = "person")
    private List<Book> bookList;

    public Person(String fullName, int yearOfBirth) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
        this.bookList = new ArrayList<>();
    }
}
