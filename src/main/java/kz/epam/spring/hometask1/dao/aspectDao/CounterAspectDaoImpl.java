package kz.epam.spring.hometask1.dao.aspectDao;

import kz.epam.spring.hometask1.dao.CounterAspectDao;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@ComponentScan("kz.epam.spring.hometask1")
@Component
public class CounterAspectDaoImpl implements CounterAspectDao {
    private static Map<Long, Long> requests = new HashMap<>();

    public void addRequestbyName() {
        Long requestByNameId = 0L;
        requests.put(requestByNameId, requests.get(requestByNameId) + 1);
    }

    public void addRequestbyPrice() {
        Long requestByPriceId = 1L;
        requests.put(requestByPriceId, requests.get(requestByPriceId) + 1);
    }

    public void addBuyRequest() {
        Long buyRequestId = 2L;
        requests.put(buyRequestId, requests.get(buyRequestId) + 1);
    }
}
