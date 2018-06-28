package kz.epam.spring.hometask1.service.impl;

import kz.epam.spring.hometask1.dao.impl.EventDao;
import kz.epam.spring.hometask1.domain.Event;
import kz.epam.spring.hometask1.service.EventService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

public class EventServiceImpl implements EventService {
    private EventDao eventDao = new EventDao();

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
}
