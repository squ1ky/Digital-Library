package com.squiky.service;

import com.squiky.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PeopleService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> findAll() {
        String SQL = "SELECT * FROM person";
        return jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(Person.class));
    }

    public Person findById(int id) {
        String SQL = "SELECT * FROM person WHERE id = ?";
        return jdbcTemplate.queryForObject(
                SQL,
                new BeanPropertyRowMapper<>(Person.class),
                id
        );
    }

    public void save(Person person) {
        String SQL = "INSERT INTO person(fullname, year_of_birth) VALUES(?, ?)";
        jdbcTemplate.update(SQL, person.getFullName(), person.getYearOfBirth());
    }

    public void update(int id, Person updatedPerson) {
        String SQL = "UPDATE person SET fullname = ?, year_of_birth = ? WHERE id = ?";
        jdbcTemplate.update(
                SQL,
                updatedPerson.getFullName(),
                updatedPerson.getYearOfBirth(),
                id
        );
    }

    public void deleteById(int id) {
        String SQL = "DELETE FROM person WHERE id = ?";
        jdbcTemplate.update(SQL, id);
    }
}
