package kz.epam.spring.hometask1.service.impl;

import kz.epam.spring.hometask1.dao.impl.EventDao;
import kz.epam.spring.hometask1.dao.impl.TicketDao;
import kz.epam.spring.hometask1.domain.Auditorium;
import kz.epam.spring.hometask1.domain.Event;
import kz.epam.spring.hometask1.service.AbstractDomainService;
import kz.epam.spring.hometask1.service.EventService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public class EventServiceImpl implements EventService, AbstractDomainService<Event> {
    private EventDao eventDao;
    private TicketDao ticketDao;

    public EventServiceImpl(EventDao eventDao, TicketDao ticketDao) {
        this.eventDao = eventDao;
        this.ticketDao = ticketDao;
    }

    public EventServiceImpl() {
    }

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
        return eventDao.getEventByName(name);
    }

    @Override
    public Event save(@Nonnull Event object) {
        return eventDao.addObject(object);
    }

    @Override
    public void remove(@Nonnull Event object) {
        eventDao.removeObject(object);
    }

    @Override
    public Event getById(@Nonnull Long id) {
        return eventDao.getById(id);
    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        return eventDao.getAll();
    }

    private List getUsedSeats(Auditorium auditorium, Event event) {
        return ticketDao.getUsedSeatsInAuditorium(auditorium.getName(), event);
    }

    public Boolean checkBookedPlace(Auditorium auditorium, Event event, LocalDateTime dateTime, Long seatNumber) {
        System.out.println(ticketDao.getAll());
        return getUsedSeats(auditorium, event).contains(seatNumber) &&
                event.getAuditoriums().get(dateTime).equals(auditorium);
    }
}
