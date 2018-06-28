package kz.epam.spring.hometask1.service;

import kz.epam.spring.hometask1.domain.Event;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface EventService extends AbstractDomainService<Event>{
    /**
     * Finding event by name
     *
     * @param name Name of the event
     * @return found event or <code>null</code>
     */
    public @Nullable
    Event getByName(@Nonnull String name);
}