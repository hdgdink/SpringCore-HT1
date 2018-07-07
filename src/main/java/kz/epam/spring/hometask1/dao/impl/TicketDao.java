package kz.epam.spring.hometask1.dao.impl;

import kz.epam.spring.hometask1.dao.Dao;
import kz.epam.spring.hometask1.domain.Event;
import kz.epam.spring.hometask1.domain.Ticket;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class TicketDao implements Dao<Ticket> {
    private static Map<Long, Ticket> tickets = new HashMap<>();

    @Override
    public Ticket addObject(Ticket ticket) {
        return tickets.put(ticket.getId(), ticket);
    }

    @Override
    public void removeObject(Ticket ticket) {
        tickets.remove(ticket.getId());
    }

    @Override
    public Ticket getById(Long id) {
        return tickets.get(id);
    }

    @Override
    public Collection<Ticket> getAll() {
        return tickets.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    private List<Ticket> getTicketsByAuditorium(String auditoriumName, Event event) {
        List<Ticket> auditoriumTickets = new ArrayList<>();

        for (Ticket ticket : tickets.values()) {

            if (ticket.getEvent().equals(event) &&
                    auditoriumName.equals(ticket.getEvent().getAuditoriums().get(ticket.getDateTime()).getName())) {
                auditoriumTickets.add(ticket);
            }
        }

        return auditoriumTickets;
    }

    public List<Long> getUsedSeatsInAuditorium(String name, Event event) {
        List<Ticket> tickets = getTicketsByAuditorium(name, event);
        List<Long> usedSeats = new ArrayList<>();

        for (Ticket ticket : tickets) {
            usedSeats.add(ticket.getSeat());
        }

        return usedSeats;
    }
}
