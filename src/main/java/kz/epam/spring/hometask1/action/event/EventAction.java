package kz.epam.spring.hometask1.action.event;

import kz.epam.spring.hometask1.domain.Auditorium;
import kz.epam.spring.hometask1.domain.Event;
import kz.epam.spring.hometask1.service.EventService;
import kz.epam.spring.hometask1.service.impl.EventServiceImpl;

import java.time.LocalDateTime;
import java.util.*;

public class EventAction {
    private EventService eventService = new EventServiceImpl();

    public Collection<Event> showAllEvents() {
        return eventService.getAll();
    }


    public Event buyTicket(Long eventId) {
        Event event = eventService.getById(eventId);

        return event;
    }

    public TreeSet showDates(Event event) {
        return new TreeSet (event.getAirDates());

    }

    public TreeMap showAuditoriums(Event event) {
        return new TreeMap<>(event.getAuditoriums());
    }


}
