package kz.epam.spring.hometask1.dao.impl;

import kz.epam.spring.hometask1.dao.Dao;
import kz.epam.spring.hometask1.domain.Auditorium;

import java.util.*;
import java.util.stream.Collectors;

public class AuditoriumDao implements Dao<Auditorium> {
    private static Map<Long, Auditorium> auditoriums = new HashMap<>();

    public AuditoriumDao() {
    }

    @Override
    public Auditorium addObject(Auditorium auditorium) {
        return auditoriums.put(auditorium.getId(), auditorium);
    }

    @Override
    public void removeObject(Auditorium auditorium) {
        auditoriums.remove(auditorium.getId());
    }

    @Override
    public Auditorium getById(Long id) {
        return auditoriums.get(id);
    }

    @Override
    public Collection<Auditorium> getAll() {
        return auditoriums.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    public Auditorium getByName(String name) {
        Auditorium auditorium = null;
        List<Auditorium> auditoriumList = new ArrayList<>(auditoriums.values());

        for (Auditorium anAudList : auditoriumList) {

            if (name.equals(anAudList.getName()))
                auditorium = anAudList;
        }

        return auditorium;
    }
}