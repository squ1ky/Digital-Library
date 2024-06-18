package com.squiky.models;

import javax.persistence.*;
import lombok.*;

import javax.validation.constraints.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "book")
@NoArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    @Size(min = 2, max = 200, message = "Title length should be 2 - 200 chars")
    private String title;

    @Column(name = "author")
    @Size(min = 2, max = 200, message = "Author name should be 2 - 200 chars")
    private String author;

    @Column(name = "year_of_publishing")
    @Max(value = 2024, message = "Year of publishing should be 1 - 2024")
    @Min(value = 1, message = "Year of publishing should be 1 - 2024")
    private int yearOfPublishing;

    @ManyToOne
    @JoinColumn(name = "person_owner_id", referencedColumnName = "id")
    private Person person;

    @Column(name = "taken_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenAt;

    @Transient
    private boolean expired;

    public Book(String title, String author, int yearOfPublishing) {
        this.title = title;
        this.author = author;
        this.yearOfPublishing = yearOfPublishing;
        this.person = null;
        this.takenAt = null;
    }

    public Book(String title, String author, int yearOfPublishing, Person person) {
        this.title = title;
        this.author = author;
        this.yearOfPublishing = yearOfPublishing;
        this.person = person;
        this.takenAt = new Date();
    }

    public boolean isExpired() {
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DAY_OF_YEAR, -10);
        return this.takenAt.before(calendar.getTime());
    }
}
