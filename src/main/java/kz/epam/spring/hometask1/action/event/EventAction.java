package kz.epam.spring.hometask1.action.event;

import kz.epam.spring.hometask1.domain.Event;
import kz.epam.spring.hometask1.service.EventService;
import kz.epam.spring.hometask1.service.impl.EventServiceImpl;

import java.util.Collection;

public class EventAction {
    private EventService eventService = new EventServiceImpl();

    public Collection<Event> showAllEvents() {
        return eventService.getAll();
    }

    public Event buyTicket(Long eventId) {
        Event event = null;
        return event;
    }


}
