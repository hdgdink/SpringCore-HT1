package kz.epam.spring.hometask1.action.event;

import kz.epam.spring.hometask1.dao.impl.TicketDao;
import kz.epam.spring.hometask1.domain.Auditorium;
import kz.epam.spring.hometask1.domain.Event;
import kz.epam.spring.hometask1.domain.Ticket;
import kz.epam.spring.hometask1.domain.User;
import kz.epam.spring.hometask1.service.impl.BookingServiceImpl;
import kz.epam.spring.hometask1.service.impl.EventServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

public class EventAction {
    private EventServiceImpl eventService;
    private BookingServiceImpl bookingService;

    public EventAction() {
    }

    public EventAction(EventServiceImpl eventService, BookingServiceImpl bookingService ) {
        this.eventService = eventService;
        this.bookingService = bookingService;
    }

    public Collection<Event> showAllEvents() {
        return eventService.getAll();
    }

    public Event getEventById(Long id) {
        return eventService.getById(id);
    }

    public Boolean buyTicket(Event event, LocalDateTime dateTime, Long seatNumber, User user) {
        Ticket ticket = new Ticket(user, event, dateTime, seatNumber);
        Double finalPrice = bookingService.getTicketPrice(event, dateTime, user, seatNumber);
        return bookingService.buyTicket(ticket, user, finalPrice);
    }

    public TreeSet showDates(Event event) {
        return new TreeSet(event.getAirDates());

    }

    public Auditorium showAuditorium(LocalDateTime date, Event event) {
        return event.getAuditoriums().get(date);
    }

    public void showSeats(LocalDateTime date, Event event) {
        Auditorium auditorium = event.getAuditoriums().get(date);
        List<Long> vipSeats = new ArrayList<>(auditorium.getVipSeats());

        System.out.println("Vip seats");
        System.out.println(vipSeats);
        System.out.println("All seats");
        showSeatsMatrix(auditorium, event, date);
    }

    private void showSeatsMatrix(Auditorium auditorium, Event event, LocalDateTime dateTime) {
        int count = 1;
        int rowSize = auditorium.getRowSize();
        List<Long> seats = new ArrayList<>(auditorium.getAllSeats());

        for (int i = 0; i < seats.size(); i++) {

            if (count <= rowSize) {

                if (eventService.checkBookedPlace(auditorium, event, dateTime, (long) i)) {
                    System.out.print("X");
                    continue;
                } else System.out.print(seats.get(i));

                if (i < 9)
                    System.out.print("  ");
                else
                    System.out.print(" ");

                count++;
            } else {
                count = 1;
                i--;
                System.out.println("");
            }
        }
    }

    public Boolean bookPlace(User loggedUser, Event event, LocalDateTime dateTime, Long seatNumber) {
        Auditorium auditorium = showAuditorium(dateTime, event);

        if (eventService.checkBookedPlace(auditorium, event, dateTime, seatNumber)) {
            System.out.println("Seat is busy");
            return false;
        } else {
            System.out.println("Price of chosen tickets: " + bookingService.getTicketPrice(event, dateTime, loggedUser, seatNumber));
            return true;
        }
    }
}