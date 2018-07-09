package kz.epam.spring.hometask1.aspect;

import kz.epam.spring.hometask1.dao.aspectDaoImpl.DiscountAspectDaoImpl;
import kz.epam.spring.hometask1.service.impl.MenuServiceImpl;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * Created by HdgDink} on 08.07.2018.
 */
@Component
@Aspect
@ComponentScan
public class DiscountAspect {
    @Autowired
    DiscountAspectDaoImpl discountAspectDao;

    @AfterReturning(
            value = "execution(* kz.epam.spring.hometask1.service.impl.DiscountServiceImpl.getDiscount(..))",
            returning = "result"
    )

    public void getDiscountsCount(Byte result) {
        if (result > 0) {
            System.out.println("Discount was given for user");
            discountAspectDao.addUserDiscounts(MenuServiceImpl.loggedUser.getId());
        }

        if (result == 100) {
            System.out.println("Lucky ticket");
        }
    }
}