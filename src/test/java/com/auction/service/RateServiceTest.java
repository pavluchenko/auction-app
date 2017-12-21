package com.auction.service;

import com.auction.converter.entity.LotConverter;
import com.auction.converter.form.LotFormConverter;
import com.auction.model.entity.Lot;
import com.auction.model.entity.Rate;
import com.auction.model.entity.User;
import com.auction.model.form.LotCreateForm;
import com.auction.model.form.RateCreateForm;
import com.auction.model.form.UserCreateForm;
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
public class RateServiceTest {

    public RateCreateForm rateCreateForm;

    public LotCreateForm lot;

    @Autowired
    private LotFormConverter lotConveter;

    @Autowired
    private LotConverter converter;

    @Autowired
    private RateService rateService;

    @Autowired
    private LotService lotService;

    @Autowired
    private UserService userService;

    @Before
    public void addRateTest() throws Exception {
        rateCreateForm = new RateCreateForm();
        lot = new LotCreateForm();
        rateCreateForm.setPrice(new Double("123"));
        rateCreateForm.setDate(new Date());
        Lot dbLot = lotService.create(lot);
        rateCreateForm.setLotId(dbLot.getId());
        User user = userService.create(new UserCreateForm());
        rateCreateForm.setUserId(user.getId());
        Rate rate = rateService.create(rateCreateForm);
        rateCreateForm.setId(rate.getId());
    }

    @Test
    public void getRateByIdTest() {
        assertNotNull(rateService.getRateById(rateCreateForm.getId()));
    }

    @Test
    public void getRateByLotTest() {
        assertNotNull(rateService.getRateByLot(lot.getId()));
    }
/*
    @Test
    public void getLastRateTest() {
        assertNotNull(rateService.getLastRate(converter.convert(lot)));
    }*/
}
