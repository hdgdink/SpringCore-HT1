package kz.epam.spring.hometask1.dao.impl;

import kz.epam.spring.hometask1.dao.Dao;
import kz.epam.spring.hometask1.domain.Auditorium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;

@Repository
@Qualifier("auditoriumDao")
@ComponentScan("kz.epam.spring.hometask1")
@Component
public class AuditoriumDao implements Dao<Auditorium> {
    private static final String ADD_AUDITORIUM = "insert into Auditoriums (id,name, numberOfSeats, rowSize)" +
            " values(?,?,?,?)";
    private static final String UPDATE_AUDITORIUM = "update Auditoriums set name = ?, numberOfSeats=?, " +
            "rowSize=? where id = ?";
    private static final String GET_AUDITORIUM_BY_ID = "select * from Auditoriums where id = ?";
    private static final String GET_AUDITORIUM_BY_NAME = "select * from Auditoriums where name = ?";
    private static final String REMOVE_AUDITORIUM_BY_ID = "remove * from Auditoriums where id = ?";
    private static final String GET_ALL_AUDITORIUMS = "select * from Auditoriums";

    public AuditoriumDao() {
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public AuditoriumDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Auditorium addObject(Auditorium auditorium) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(ADD_AUDITORIUM,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, auditorium.getId());
            ps.setString(2, auditorium.getName());
            ps.setLong(3, auditorium.getNumberOfSeats());
            ps.setInt(4, auditorium.getRowSize());
            return ps;
        }, holder);
        auditorium.setId(holder.getKey().longValue());
        return auditorium;
    }

    @Override
    public void updateObject(Auditorium auditorium) {
        jdbcTemplate.update(UPDATE_AUDITORIUM, auditorium.getName(), auditorium.getNumberOfSeats(),
                auditorium.getRowSize(), auditorium.getId());
    }

    @Override
    public void removeObject(Auditorium auditorium) {
        jdbcTemplate.update(REMOVE_AUDITORIUM_BY_ID, auditorium.getId());
    }

    @Override
    public Auditorium getById(Long id) {
        try {
            return jdbcTemplate.queryForObject(GET_AUDITORIUM_BY_ID, new Object[]{id},
                    new BeanPropertyRowMapper<>(Auditorium.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Collection<Auditorium> getAll() {
        return jdbcTemplate.query(GET_ALL_AUDITORIUMS, rowMapper);
    }

    public Auditorium getByName(String name) {
        try {
            return jdbcTemplate.queryForObject(GET_AUDITORIUM_BY_NAME,
                    new Object[]{name}, new BeanPropertyRowMapper<>(Auditorium.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private RowMapper<Auditorium> rowMapper = (rs, rowNum) -> {
        Auditorium auditorium = new Auditorium();
        auditorium.setId(rs.getLong("id"));
        auditorium.setName(rs.getString("name"));
        auditorium.setNumberOfSeats(rs.getLong("NumberOfSeats"));
        auditorium.setRowSize(rs.getInt("rowSize"));
        return auditorium;
    };
}