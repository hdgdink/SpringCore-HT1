package kz.epam.spring.hometask1.service.impl;

import kz.epam.spring.hometask1.domain.Event;
import kz.epam.spring.hometask1.domain.User;
import kz.epam.spring.hometask1.service.DiscountService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Service
@ComponentScan
public class DiscountServiceImpl implements DiscountService {
    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long ticketNumber) {
        int luckyNumber = 25;

        if (ticketNumber % 5 == 0) {
            return 50;
        }

        if (ThreadLocalRandom.current().nextInt(0, 100) == luckyNumber) {
            System.out.println("It's a lucky ticket, free for you");
            return 100;
        }
        return 0;
    }
}