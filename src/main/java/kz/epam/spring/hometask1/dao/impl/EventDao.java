package kz.epam.spring.hometask1.dao.impl;

import kz.epam.spring.hometask1.dao.Dao;
import kz.epam.spring.hometask1.domain.Event;

import java.util.Collection;

public class EventDao implements Dao<Event> {
    @Override
    public Event addObject(Event event) {
        return null;
    }

    @Override
    public void removeObject(Event event) {

    }

    @Override
    public Event getById(Long id) {
        return null;
    }

    @Override
    public Collection<Event> getAll() {
        return null;
    }
}
