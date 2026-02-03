package com.persons.dao;
import com.persons.beans.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


import java.sql.SQLException;

public class PersonDAO {


    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Person getPersonByName(String name) throws SQLException {
        String query = "select * from personsdetails where personsdetails.Name = ?";
        return jdbcTemplate.queryForObject(query,
                new Object[]{name},
                new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getPersonById(int id) throws SQLException {
        String query = "select * from personsdetails where personsdetails.id = ?";
        return jdbcTemplate.queryForObject(query,
                new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getPersonByServiceNumber(String serviceNumber) throws SQLException {
        String query = "select * from personsdetails where personsdetails.serviceNumber = ?";
        return jdbcTemplate.queryForObject(query,
                new Object[]{serviceNumber},
                new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getPersonByConsumedUnits(String units) throws SQLException {
        String query = "select * from personsdetails where personsdetails.consumedUnits = ?";
        return jdbcTemplate.queryForObject(query,
                new Object[]{units},
                new BeanPropertyRowMapper<>(Person.class));
    }
}
