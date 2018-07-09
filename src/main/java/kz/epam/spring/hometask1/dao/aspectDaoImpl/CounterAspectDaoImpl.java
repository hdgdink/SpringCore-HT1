package kz.epam.spring.hometask1.dao.aspectDaoImpl;

import kz.epam.spring.hometask1.dao.CounterAspectDao;
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
public class CounterAspectDaoImpl implements CounterAspectDao {
    private static Map<Long, Long> requests = new HashMap<>();
    private static Long reqByNameCounter = 0L;
    private static Long reqByPriceCounter = 0L;
    private static Long buyReqCounter = 0L;

    @Override
    public void addRequestbyName() {
        Long requestByNameId = 0L;
        reqByNameCounter++;
        requests.put(requestByNameId, reqByNameCounter);
        System.out.println("Event requested by Id");
    }

    @Override
    public void addRequestbyPrice() {
        Long requestByPriceId = 1L;
        reqByPriceCounter++;
        requests.put(requestByPriceId, reqByPriceCounter);
        System.out.println("Event price is requested");
    }

    @Override
    public void addBuyRequest() {
        Long buyRequestId = 2L;
        buyReqCounter++;
        requests.put(buyRequestId, buyReqCounter);
        System.out.println("Buy ticket request");
    }
}