package org.example.DAO;

import org.example.model.Book;
import org.example.model.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * created by dmitry.fateev
 * 09.06.2022
 */

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> indexPeople(){
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person showPerson(int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE user_id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public Person showPerson(String name){
        return jdbcTemplate.query("SELECT * FROM Person WHERE name=?", new Object[]{name},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public List<Book> showBooks(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE user_id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }

    public void savePerson(Person person){
        jdbcTemplate.update("INSERT INTO Person(name, year) VALUES (?,?)",
                person.getName(),person.getYear());
    }

    public void updatePerson(Person person, int id){
        jdbcTemplate.update("UPDATE Person SET name=?, year=? WHERE user_id=?",
                person.getName(),person.getYear(),id);
    }

    public void deletePerson(int id){
        jdbcTemplate.update("DELETE FROM Person WHERE user_id=?", id);
    }
}
