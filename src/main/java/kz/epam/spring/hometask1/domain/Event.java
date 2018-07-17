package kz.epam.spring.hometask1.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Event extends DomainObject {
    private String name;
    private LocalDateTime airDates;
    private double basePrice;
    private EventRating rating;
    private Long auditoriumId;

    public Event() {
        setId(ThreadLocalRandom.current().nextLong(0, 100));
    }

    public Event(String name, LocalDateTime airDates, double basePrice, EventRating rating, Long auditoriumId) {
        this.name = name;
        this.airDates = airDates;
        this.basePrice = basePrice;
        this.rating = rating;
        this.auditoriumId = auditoriumId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public LocalDateTime getAirDates() {
        return airDates;
    }

    public void setAirDates(LocalDateTime airDates) {
        this.airDates = airDates;
    }

    public Long getAuditoriumId() {
        return auditoriumId;
    }

    public void setAuditoriumId(Long auditoriumId) {
        this.auditoriumId = auditoriumId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Double.compare(event.basePrice, basePrice) == 0 &&
                Objects.equals(name, event.name) &&
                Objects.equals(airDates, event.airDates) &&
                rating == event.rating &&
                Objects.equals(auditoriumId, event.auditoriumId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, airDates, basePrice, rating, auditoriumId);
    }

    @Override
    public String toString() {
        return "Event №" + getId() + " " + name + ", basePrice=" + basePrice + ", rating=" + rating;
    }
}