package com.persons.dao;
import com.persons.beans.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.sql.SQLException;

@Repository
public class PersonDAO {

    @Autowired
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

    public Person getPersonByConsumedUnits(int units) throws SQLException {
        String query = "select * from personsdetails where personsdetails.consumedUnits = ?";
        return jdbcTemplate.queryForObject(query,
                new Object[]{units},
                new BeanPropertyRowMapper<>(Person.class));
    }
}
