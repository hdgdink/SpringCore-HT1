package kz.epam.spring.hometask1.dao.impl;

import kz.epam.spring.hometask1.dao.Dao;
import kz.epam.spring.hometask1.domain.Ticket;
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
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Qualifier("TicketDao")
@ComponentScan("kz.epam.spring.hometask1")
@Component
public class TicketDao implements Dao<Ticket> {
    private static final String ADD_TICKET = "INSERT INTO Tickets (id,user_id, event_id, auditorium_id, " +
            " date, seat) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_TICKET = "update Tickets set userId = ?, eventId=?, auditoriumId=?, date=?," +
            "seat=? where id = ?";
    private static final String REMOVE_TICKET_BY_ID = "remove * from Tickets where id = ?";
    private static final String GET_TICKET_BY_ID = "select * from Tickets where id = ?";
    private static final String GET_ALL_TICKETS = "select * from Tickets";
    private static final String GET_TICKET_BY_AUDITORUIUM_EVENT_DATE = "SELECT * from Tickets WHERE " +
            "(auditoriumId = ? AND eventId = ? AND date = ?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public TicketDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Ticket addObject(Ticket ticket) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(ADD_TICKET,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, ticket.getId());
            ps.setLong(2, ticket.getUserId());
            ps.setLong(3, ticket.getEventId());
            ps.setLong(4, ticket.getAuditoriumId());
            ps.setTimestamp(5, Timestamp.valueOf(ticket.getDateTime()));
            ps.setLong(6, ticket.getSeat());
            return ps;
        }, holder);
        ticket.setId(holder.getKey().longValue());
        return ticket;
    }

    @Override
    public void updateObject(Ticket ticket) {
        jdbcTemplate.update(UPDATE_TICKET, ticket.getUserId(), ticket.getEventId(), ticket.getAuditoriumId(), ticket.getDateTime(),
                ticket.getSeat(), ticket.getId());
    }

    @Override
    public void removeObject(Ticket ticket) {
        jdbcTemplate.update(REMOVE_TICKET_BY_ID, ticket.getId());
    }

    @Override
    public Ticket getById(Long id) {
        try {
            return jdbcTemplate.queryForObject(GET_TICKET_BY_ID, new Object[]{id}, new BeanPropertyRowMapper<>(Ticket.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Collection<Ticket> getAll() {
        return jdbcTemplate.query(GET_ALL_TICKETS, rowMapper);
    }

    private RowMapper<Ticket> rowMapper = (rs, rowNum) -> {
        Ticket ticket = new Ticket();
        ticket.setId(rs.getLong("id"));
        ticket.setUserId(rs.getLong("userId"));
        ticket.setEventId(rs.getLong("eventId"));
        ticket.setAuditoriumId(rs.getLong("auditoriumId"));
        ticket.setDateTime(LocalDateTime.ofInstant(rs.getTimestamp("date").toInstant(), ZoneOffset.ofHours(6)));
        ticket.setSeat(rs.getLong("seat"));
        return ticket;
    };

    private List<Ticket> getTicketsByAuditorium(Long auditoriumId, Long eventId, LocalDateTime dateTime) {
        try {
            return jdbcTemplate.query(GET_TICKET_BY_AUDITORUIUM_EVENT_DATE + " ",
                    new Object[]{auditoriumId, eventId, Timestamp.valueOf(dateTime)}, new BeanPropertyRowMapper<>(Ticket.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Long> getUsedSeatsInAuditorium(Long auditoriumId, Long eventId, LocalDateTime dateTime) {
        List<Ticket> tickets = getTicketsByAuditorium(auditoriumId, eventId, dateTime);

        if ((tickets != null ? tickets.size() : 0) > 0) {
            return tickets.stream().map(Ticket::getSeat).collect(Collectors.toList());
        }

        return null;
    }
}