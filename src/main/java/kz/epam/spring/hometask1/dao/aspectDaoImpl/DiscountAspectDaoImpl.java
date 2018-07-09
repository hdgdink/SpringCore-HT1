package kz.epam.spring.hometask1.dao.aspectDaoImpl;

import kz.epam.spring.hometask1.dao.DiscountAspectDao;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HdgDink} on 08.07.2018.
 */
@Repository
@ComponentScan("kz.epam.spring.hometask1")
@Component
public class DiscountAspectDaoImpl implements DiscountAspectDao {
    private static Map<Long, Long> userDiscounts = new HashMap<>();
    private static Map<Long, Long> totalDiscounts = new HashMap<>();
    private static Long userDiscountCounter = 0L;
    private static Long totalDiscountCounter = 0L;

    @Override
    public void addUserDiscounts(long userId) {
        userDiscountCounter++;
        totalDiscountCounter++;
        userDiscounts.put(userId, userDiscountCounter);
        Long totalDiscountCounterID = 0L;
        totalDiscounts.put(totalDiscountCounterID, totalDiscountCounter);
    }
}