package kz.epam.spring.hometask1.dao.impl;

import kz.epam.spring.hometask1.dao.Dao;
import kz.epam.spring.hometask1.domain.Event;

import java.util.*;
import java.util.stream.Collectors;

public class EventDao implements Dao<Event> {
    private static Map<Long, Event> events = new HashMap<>();

    @Override
    public Event addObject(Event event) {
        events.put(event.getId(), event);
        return event;
    }

    @Override
    public void removeObject(Event event) {
        events.remove(event.getId());
    }

    @Override
    public Event getById(Long id) {
        return events.get(id);
    }

    @Override
    public Collection<Event> getAll() {
        return events.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    public Event getEventByName(String name) {
        Event event = null;
        List<Event> eventList = new ArrayList<>(events.values());

        for (Event anEventList : eventList) {

            if (name.equals(anEventList.getName()))
                event = anEventList;
        }

        return event;
    }
}