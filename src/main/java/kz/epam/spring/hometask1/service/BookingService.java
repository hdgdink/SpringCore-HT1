package kz.epam.spring.hometask1.service;

import kz.epam.spring.hometask1.domain.Event;
import kz.epam.spring.hometask1.domain.Ticket;
import kz.epam.spring.hometask1.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Set;

public interface BookingService {

    /**
     * Getting price when buying all supplied seats for particular event
     *
     * @param event    Event to get base ticket price, vip seats and other
     *                 information
     * @param dateTime Date and time of event air
     * @param user     User that buys ticket could be needed to calculate discount.
     *                 Can be <code>null</code>
     * @param seat     Seat number that user wants to buy
     * @return total price
     */
    public double getTicketPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user,
                                 @Nonnull Long seat);

    /**
     * Buy ticket in internal system. If user is not <code>null</code>
     *
     * @param ticket ticket
     */
    public Boolean buyTicket(@Nonnull Ticket ticket, User user, Double finalPrice);

    /**
     * Getting all purchased tickets for event on specific air date and time
     *
     * @param event    Event to get tickets for
     * @param dateTime Date and time of airing of event
     * @return set of all purchased tickets
     */
    public @Nonnull
    Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime);
}