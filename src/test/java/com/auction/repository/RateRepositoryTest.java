package com.auction.repository;

import com.auction.model.entity.Lot;
import com.auction.model.entity.Rate;
import com.auction.model.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Helga on 05.12.2017.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appconfig-root-test.xml"})
@Transactional
public class RateRepositoryTest {

    private Rate rate;

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private LotRepository lotRepository;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void addRateTest() throws Exception {
        rate = new Rate();
        rate.setDate(new Date());
        Lot lot = new Lot();
        User user = new User();
        lotRepository.saveAndFlush(lot);
        userRepository.saveAndFlush(user);
        rate.setUser(user);
        rate.setLot(lot);
        rateRepository.saveAndFlush(rate);
    }

    @Test
    public void getRateById() {
        assertNotNull(rateRepository.findOne(rate.getId()));
    }

    @Test
    public void getRateByLot() {
        assertNotNull(rateRepository.getRateByLot(rate.getLot()));
    }
}
