package kz.epam.spring.hometask1.service.impl;

import kz.epam.spring.hometask1.domain.Event;
import kz.epam.spring.hometask1.domain.User;
import kz.epam.spring.hometask1.service.DiscountService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

public class DiscountServiceImpl implements DiscountService {
    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        return 0;
    }
}
