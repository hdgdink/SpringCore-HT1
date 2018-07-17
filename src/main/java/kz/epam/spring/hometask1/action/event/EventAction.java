package kz.epam.spring.hometask1.action.event;

import kz.epam.spring.hometask1.domain.Auditorium;
import kz.epam.spring.hometask1.domain.Event;
import kz.epam.spring.hometask1.domain.Ticket;
import kz.epam.spring.hometask1.domain.User;
import kz.epam.spring.hometask1.service.impl.AuditoriumServiceImpl;
import kz.epam.spring.hometask1.service.impl.BookingServiceImpl;
import kz.epam.spring.hometask1.service.impl.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@ComponentScan
public class EventAction {
    @Autowired
    private EventServiceImpl eventService;
    @Autowired
    private BookingServiceImpl bookingService;
    @Autowired
    private AuditoriumServiceImpl auditoriumService;

    public EventAction() {
    }

    public EventAction(EventServiceImpl eventService, BookingServiceImpl bookingService, AuditoriumServiceImpl auditoriumService) {
        this.eventService = eventService;
        this.auditoriumService = auditoriumService;
        this.bookingService = bookingService;
    }

    public Collection<Event> showAllEvents() {
        return eventService.getAll();
    }

    public Event getEventById(Long id) {
        return eventService.getById(id);
    }

    public Boolean buyTicket(Event event, LocalDateTime dateTime, Long seatNumber, User user) {
        Ticket ticket = new Ticket(user.getId(), event.getId(), dateTime, seatNumber, event.getAuditoriumId());
        Double finalPrice = bookingService.getTicketPrice(event, dateTime, user, seatNumber);
        return bookingService.buyTicket(ticket, user, finalPrice);
    }

    public List<LocalDateTime> showDates(Event event) {
        return eventService.getAirDates(event.getName());
    }

    public Auditorium showAuditorium(Event event) {
        return auditoriumService.getById(event.getAuditoriumId());
    }

    public void showSeats(LocalDateTime date, Event event) {
        Auditorium auditorium = auditoriumService.getById(event.getAuditoriumId());
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
        Auditorium auditorium = showAuditorium(event);

        if (eventService.checkBookedPlace(auditorium, event, dateTime, seatNumber)) {
            System.out.println("Seat is busy");
            return false;
        } else {
            System.out.println("Price of chosen tickets: " + bookingService.getTicketPrice(event, dateTime,
                    loggedUser, seatNumber));
            return true;
        }
    }
}