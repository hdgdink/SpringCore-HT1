package kz.epam.spring.hometask1.dao.impl;

import kz.epam.spring.hometask1.dao.Dao;
import kz.epam.spring.hometask1.domain.Event;
import kz.epam.spring.hometask1.domain.EventRating;
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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
@Qualifier("eventDao")
@ComponentScan("kz.epam.spring.hometask1")
@Component
public class EventDao implements Dao<Event> {
    private static final String ADD_EVENT = "INSERT INTO Events (id,name, basePrice, rating, date, auditoriumId) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_EVENT = "update Events set name = ?, basePrice=?, rating=?, date=?, " +
            "auditoriumId=?  where id = ?";
    private static final String REMOVE_EVENT_BY_ID = "remove * from Events where id = ?";
    private static final String GET_EVENT_BY_ID = "select * from Events where id = ?";
    private static final String GET_ALL_EVENTS = "select * from Events";
    private static final String GET_EVENT_BY_NAME = "SELECT * from Events where name = ?";
    private static final String GET_DATES_FOR_EVENT_BY_NAME = "SELECT date from Events WHERE name = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public EventDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Event addObject(Event event) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(ADD_EVENT,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, event.getId());
            ps.setString(2, event.getName());
            ps.setDouble(3, event.getBasePrice());
            ps.setString(4, event.getRating().toString());
            ps.setTimestamp(5, Timestamp.valueOf(event.getAirDates()));
            ps.setLong(6, event.getAuditoriumId());
            return ps;
        }, holder);
        event.setId(holder.getKey().longValue());
        return event;
    }

    @Override
    public void updateObject(Event event) {
        jdbcTemplate.update(UPDATE_EVENT, event.getName(), event.getBasePrice(), event.getRating(), event.getId()
                , event.getAirDates(), event.getAuditoriumId());
    }

    @Override
    public void removeObject(Event event) {
        jdbcTemplate.update(REMOVE_EVENT_BY_ID, event.getId());
    }

    @Override
    public Event getById(Long id) {
        try {
            return jdbcTemplate.queryForObject(GET_EVENT_BY_ID, new Object[]{id}, new BeanPropertyRowMapper<>(Event.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Collection<Event> getAll() {
        return jdbcTemplate.query(GET_ALL_EVENTS, rowMapper);
    }

    public List<LocalDateTime> getAirDates(String name) {
        List<LocalDateTime> dates = new ArrayList<>();
        List<Timestamp> timestamps = jdbcTemplate.queryForList(GET_DATES_FOR_EVENT_BY_NAME,
                new Object[]{name}, Timestamp.class);

        for (Timestamp timestamp : timestamps) {
            dates.add(LocalDateTime.ofInstant(timestamp.toInstant(), ZoneOffset.ofHours(6)));
        }
        return dates;
    }

    public Event getEventByName(String name) {
        Event event = jdbcTemplate.queryForObject(GET_EVENT_BY_NAME,
                new Object[]{name}, new BeanPropertyRowMapper<>(Event.class));
        return event;
    }

    private RowMapper<Event> rowMapper = (rs, rowNum) -> {
        Event event = new Event();
        event.setId(rs.getLong("id"));
        event.setName(rs.getString("name"));
        event.setBasePrice(rs.getDouble("basePrice"));
        event.setRating(EventRating.valueOf(rs.getString("rating")));
        return event;
    };
}