package kz.epam.spring.hometask1.dao;

import java.util.Collection;

public interface Dao<T> {
    T addObject(T t);

    void removeObject(T t);

    T getById(Long id);

    Collection<T> getAll();
}