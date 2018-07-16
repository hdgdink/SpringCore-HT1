package kz.epam.spring.hometask1.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Ticket extends DomainObject {
    private Long userId;
    private Long eventId;
    private LocalDateTime dateTime;
    private long seat;
    private Long auditoriumId;

    public Ticket(Long userId, Long eventId, LocalDateTime dateTime, long seat, Long auditoriumId) {
        this.userId = userId;
        this.eventId = eventId;
        this.dateTime = dateTime;
        this.seat = seat;
        this.auditoriumId = auditoriumId;
    }

    public Ticket() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public long getSeat() {
        return seat;
    }

    public void setSeat(long seat) {
        this.seat = seat;
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
        Ticket ticket = (Ticket) o;
        return seat == ticket.seat &&
                Objects.equals(userId, ticket.userId) &&
                Objects.equals(eventId, ticket.eventId) &&
                Objects.equals(dateTime, ticket.dateTime) &&
                Objects.equals(auditoriumId, ticket.auditoriumId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, eventId, dateTime, seat, auditoriumId);
    }
}