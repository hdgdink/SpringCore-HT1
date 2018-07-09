package kz.epam.spring.hometask1.aspect;

import kz.epam.spring.hometask1.dao.aspectDao.CounterAspectDaoImpl;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@Aspect
@ComponentScan("kz.epam.spring.hometask1")
public class CounterAspect {

    @Autowired
    private CounterAspectDaoImpl counterAOPDao;

    @Before("execution(* *.getById(..))")
    public void accessByNameCount() {
        counterAOPDao.addRequestbyName();
    }

    @Before("execution(* *.getPrice(..))")
    public void priceAccessCount() {
        counterAOPDao.addRequestbyPrice();
        System.out.println("Price request");
    }

    @Before("execution(* *.buyChosenTickets(..))")
    public void buyRequestsCount() {
        counterAOPDao.addBuyRequest();
        System.out.println("buy ticket request");
    }

}
