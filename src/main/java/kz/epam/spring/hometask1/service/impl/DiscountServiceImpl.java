package kz.epam.spring.hometask1.service.impl;

import kz.epam.spring.hometask1.domain.Event;
import kz.epam.spring.hometask1.domain.User;
import kz.epam.spring.hometask1.service.DiscountService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

@Service
@ComponentScan
public class DiscountServiceImpl implements DiscountService {
    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long ticketNumber) {
        if (ticketNumber % 5 == 0) {
            return 50;
        }
        return 0;
    }
}