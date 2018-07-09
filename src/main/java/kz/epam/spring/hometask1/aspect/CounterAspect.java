package kz.epam.spring.hometask1.aspect;

import kz.epam.spring.hometask1.dao.aspectDaoImpl.CounterAspectDaoImpl;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * Created by HdgDink} on 08.07.2018.
 */
@Component
@Aspect
@ComponentScan("kz.epam.spring.hometask1")
public class CounterAspect {
    @Autowired
    private CounterAspectDaoImpl counterAOPDao;

    @Before("execution(* *.getEventById(..))")
    public void accessByNameCount() {
        counterAOPDao.addRequestbyName();
    }

    @Before("execution(* *.getTicketPrice(..))")
    public void priceAccessCount() {
        counterAOPDao.addRequestbyPrice();
    }

    @Before("execution(* *.buyTicket(..))")
    public void buyRequestsCount() {
        counterAOPDao.addBuyRequest();
    }

}
