package kz.epam.spring.hometask1.dao.impl;

import kz.epam.spring.hometask1.dao.Dao;
import kz.epam.spring.hometask1.domain.Role;
import kz.epam.spring.hometask1.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;

@Repository
@Qualifier("userDao")
@ComponentScan("kz.epam.spring.hometask1")
@Component
public class UserDao implements Dao<User> {
    private static final String GET_USER_BY_ID = "select * from Users where id = ?";
    private static final String GET_USER_BY_EMAIL = "select * from Users where email = ?";
    private static final String REMOVE_USER_BY_ID = "remove * from Users where id = ?";
    private static final String ADD_USER = "insert into Users " +
            "(id,firstName, lastName, email, pass, balance, role) values(?,?,?,?,?,?,?)";
    private static final String UPDATE_USER_BALANCE = "update Users set balance = ? where id = ?";
    private static final String UPDATE_USER = "update Users set firstName = ?, lastName=?, email=?, pass=?," +
            "balance=? where id = ?";
    private static final String GET_ALL_USERS = "select * from Users";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<User> rowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setFirstName(rs.getString("firstName"));
        user.setLastName(rs.getString("lastName"));
        user.setEmail(rs.getString("email"));
        user.setPass(rs.getString("pass"));
        user.setBalance(rs.getDouble("balance"));
        user.setRole(Role.valueOf(rs.getString("role")));
        return user;
    };

    @Override
    public User addObject(User user) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(ADD_USER,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, user.getId());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPass());
            ps.setDouble(6, user.getBalance());
            ps.setString(7, user.getRole().toString());
            return ps;
        }, holder);
        user.setId(holder.getKey().longValue());
        return user;
    }

    @Override
    public void updateObject(User user) {
        jdbcTemplate.update(UPDATE_USER, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPass(),
                user.getBalance(), user.getId());
    }

    @Override
    public void removeObject(User user) {
        jdbcTemplate.update(REMOVE_USER_BY_ID, user.getId());
    }

    @Override
    public User getById(Long id) {
        try {
            return jdbcTemplate.queryForObject(GET_USER_BY_ID, new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Collection getAll() {
        return jdbcTemplate.query(GET_ALL_USERS, rowMapper);
    }

    public User getUserByEmail(String email) {
        try {
            return jdbcTemplate.queryForObject(GET_USER_BY_EMAIL,
                    new Object[]{email}, new BeanPropertyRowMapper<>(User.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void setBalance(Long id, Double balance) {
        jdbcTemplate.update(UPDATE_USER_BALANCE, id, balance);
    }
}