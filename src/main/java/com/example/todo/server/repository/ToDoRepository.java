package com.example.todo.server.repository;

import com.example.todo.server.model.ToDo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ToDoRepository {
    private final JdbcTemplate jdbcTemplate;
    public ToDoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<ToDo> findAll() {

        return jdbcTemplate.query("select id, name , status from todo", (resultSet, rowNumber) ->
                new ToDo(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        ToDo.Status.valueOf(resultSet.getString("status")))
        );
    }

    public ToDo findById(int id) {
        return jdbcTemplate.query("select id, name, status from todo where id=?",
                (resultSet, rowNumber) -> new ToDo(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        ToDo.Status.valueOf(resultSet.getString("status"))), id).get(0);
    }

    @Transactional
    public int createTodo(ToDo newTodo) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement("insert into todo (name , status )values( ? , ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, newTodo.getName());
            ps.setString(2, newTodo.getStatus().name());
            return ps;
        }, keyHolder);

        return ((BigInteger) keyHolder.getKey()).intValue();

    }

    public void updateTodo(ToDo toDo) {
        jdbcTemplate.update("update todo set name=? , status=? where id=?",
                toDo.getName(), toDo.getStatus().name(), toDo.getId());

    }

    public void deleteById(Integer id) {
        jdbcTemplate.update("delete from todo where id=?", id);
    }

}
