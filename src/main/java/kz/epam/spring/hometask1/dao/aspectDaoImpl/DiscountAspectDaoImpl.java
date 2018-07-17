package kz.epam.spring.hometask1.dao.aspectDaoImpl;

import kz.epam.spring.hometask1.dao.DiscountAspectDao;
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
public class DiscountAspectDaoImpl implements DiscountAspectDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void addUserDiscounts(long userId) {
        jdbcTemplate.update("INSERT INTO Discount_Aspect (user_id) VALUES (?)", userId);
    }
}