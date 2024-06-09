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
}
