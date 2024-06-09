package com.example.todo.server.repository;

import com.example.todo.server.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getAllUsers() {
        return jdbcTemplate.query("select * from user", (resultSet, number) -> {
            return new User(
                    resultSet.getString("name"),
                    resultSet.getString("username"),
                    resultSet.getInt("id"));
        });
    }

    public int createUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement("insert into user (name,username) values(?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getUserName());
            return ps;
        }, keyHolder);

        return (Objects.requireNonNull(keyHolder.getKey())).intValue();
    }

    public void updateUser(User user) {
            jdbcTemplate.update("update user set name=? ,  username=? where id=?",
                user.getName(), user.getUserName(), user.getId());
    }

    public void deleteUser(Integer id) {
        jdbcTemplate.update("delete from user where id=?", id);
    }
}
