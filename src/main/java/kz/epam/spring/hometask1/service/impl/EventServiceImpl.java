package kz.epam.spring.hometask1.service.impl;

import kz.epam.spring.hometask1.dao.impl.EventDao;
import kz.epam.spring.hometask1.domain.Event;
import kz.epam.spring.hometask1.service.EventService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

public class EventServiceImpl implements EventService {
    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
        return null;
    }

    public Collection<Event> getAll() {
        Collection<Event> eventList;
        EventDao eventDao = new EventDao();
        eventList = eventDao.getAll();
        return eventList;
    }
}
