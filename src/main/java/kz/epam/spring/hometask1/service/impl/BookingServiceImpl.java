package kz.epam.spring.hometask1.service.impl;

import kz.epam.spring.hometask1.dao.impl.TicketDao;
import kz.epam.spring.hometask1.dao.impl.UserDao;
import kz.epam.spring.hometask1.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
@ComponentScan
public class BookingServiceImpl implements kz.epam.spring.hometask1.service.BookingService {
    @Autowired
    private TicketDao ticketDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private DiscountServiceImpl discountService;
    @Autowired
    private AuditoriumServiceImpl auditoriumService;

    public BookingServiceImpl(TicketDao ticketDao, UserDao userDao, DiscountServiceImpl discountService,
                              AuditoriumServiceImpl auditoriumService) {
        this.ticketDao = ticketDao;
        this.userDao = userDao;
        this.auditoriumService = auditoriumService;
        this.discountService = discountService;
    }

    public BookingServiceImpl() {
    }

    @Override
    public double getTicketPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user,
                                 @Nonnull Long seat) {
        double price;
        price = event.getBasePrice();
        Auditorium auditorium = auditoriumService.getById(event.getAuditoriumId());

        if (auditorium.getVipSeats().contains(seat))
            price *= 2;

        if (event.getRating().equals(EventRating.HIGH))
            price *= 1.2;

        if (event.getRating().equals(EventRating.LOW))
            price *= 0.8;


        return price;
    }

    @Override
    public Boolean buyTicket(@Nonnull Ticket ticket, User user, Double finalPrice) {
        Byte discount = discountService.getDiscount(ticket.getUserId(), ticket.getEventId(),
                ticket.getDateTime(), ticket.getSeat());

        if (discount > 0) {
            System.out.println("You have discount: " + discount + "%");
            finalPrice = finalPrice - finalPrice / 100 * discount;
        }

        if (user.getBalance() >= finalPrice) {
            user.setBalance(user.getBalance() - finalPrice);
            ticketDao.addObject(ticket);
            userDao.setBalance(user.getId(), user.getBalance());
            System.out.println("You buy the ticket, you balance now is: " + user.getBalance());
            return true;
        } else {
            System.out.println("Not enough money for buying the ticket");
            return false;
        }
    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        Set<Ticket> tickets = new HashSet<>();

        for (Ticket ticket : ticketDao.getAll()) {

            if (ticket.getEventId().equals(event.getId()) && ticket.getDateTime().equals(dateTime)) {
                tickets.add(ticket);
            }
        }
        return tickets;
    }
}