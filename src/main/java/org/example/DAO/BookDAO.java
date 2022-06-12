package org.example.DAO;

import org.example.model.Book;
import org.example.model.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * created by dmitry.fateev
 * 09.06.2022
 */

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> indexBook(){
        BeanPropertyRowMapper<Book> myMapper = new BeanPropertyRowMapper<>(Book.class);
        myMapper.setPrimitivesDefaultedForNullValue(true);
        return jdbcTemplate.query("SELECT * FROM Book", myMapper/*new RowMapper<Book>() {
            @Override
            public Book mapRow(ResultSet resultSet, int i) throws SQLException {
                Book book = new Book();
                book.setBook_id(resultSet.getInt("book_id"));
                book.setUser_id(resultSet.getInt("user_id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setYear(resultSet.getInt("year"));
                return book;
            }
        }*/);
    }

    public Book showBook(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }



    public void saveBook(Book book){
        jdbcTemplate.update("INSERT INTO Book(title, author, year) VALUES (?,?,?)",
                book.getTitle(), book.getAuthor(), book.getYear());
    }

    public void updateBook(Book book, int id){
        jdbcTemplate.update("UPDATE Person SET title=?, author=?, year=? WHERE book_id=?",
                book.getTitle(), book.getAuthor(), book.getYear(), id);
    }

    public void deleteBook(int id){
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
    }

    public Person getOwner(int id){
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.user_id = Person.user_id WHERE Book.book_id = ?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void release(int id){
        jdbcTemplate.update("UPDATE Book SET user_id=NULL WHERE book_id=?", id);
    }

    public void assign(int id, Person person){
        jdbcTemplate.update("UPDATE Book SET user_id=? WHERE book_id=?", person.getUser_id(), id);
    }


}
