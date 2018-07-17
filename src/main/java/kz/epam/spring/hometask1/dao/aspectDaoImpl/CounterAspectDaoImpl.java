package kz.epam.spring.hometask1.dao.aspectDaoImpl;

import kz.epam.spring.hometask1.dao.CounterAspectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by HdgDink} on 08.07.2018.
 */
@Repository
@ComponentScan("kz.epam.spring.hometask1")
@Component
public class CounterAspectDaoImpl implements CounterAspectDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void addRequestbyName() {
        jdbcTemplate.update("UPDATE CounterAspect SET ReqByName = ReqByName + 1");
    }

    @Override
    public void addRequestbyPrice() {
        jdbcTemplate.update("UPDATE CounterAspect SET ReqByPrice = ReqByPrice + 1");
    }

    @Override
    public void addBuyRequest() {
        jdbcTemplate.update("UPDATE CounterAspect SET BuyReq = BuyReq + 1");
    }
}