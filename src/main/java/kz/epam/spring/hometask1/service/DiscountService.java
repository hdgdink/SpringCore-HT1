package kz.epam.spring.hometask1.service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

public interface DiscountService {
    /**
     * Getting discount based on some rules for user that buys some number of
     * tickets for the specific date time of the event
     *
     * @param userId          User that buys tickets. Can be <code>null</code>
     * @param eventId         Event that tickets are bought for
     * @param airDateTime     The date and time event will be aired
     * @param numberOfTickets Number of tickets that user buys
     * @return discount value from 0 to 100
     */
    byte getDiscount(@Nullable Long userId, @Nonnull Long eventId, @Nonnull LocalDateTime airDateTime, long numberOfTickets);
}