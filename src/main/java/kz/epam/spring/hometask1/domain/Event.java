package kz.epam.spring.hometask1.domain;

import java.time.LocalDateTime;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class Event extends DomainObject {
    private String name;
    private NavigableSet<LocalDateTime> airDates = new TreeSet<>();
    private double basePrice;
    private EventRating rating;
    private NavigableMap<LocalDateTime, Auditorium> auditoriums = new TreeMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NavigableSet<LocalDateTime> getAirDates() {
        return airDates;
    }

    public void setAirDates(NavigableSet<LocalDateTime> airDates) {
        this.airDates = airDates;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public EventRating getRating() {
        return rating;
    }

    public void setRating(EventRating rating) {
        this.rating = rating;
    }

    public NavigableMap<LocalDateTime, Auditorium> getAuditoriums() {
        return auditoriums;
    }

    public void setAuditoriums(NavigableMap<LocalDateTime, Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
    }
}
