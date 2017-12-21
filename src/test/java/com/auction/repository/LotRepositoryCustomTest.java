package com.auction.repository;

import com.auction.model.entity.Lot;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Helga on 05.12.2017.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appconfig-root-test.xml"})
@Transactional
public class LotRepositoryCustomTest {

    private Lot lot;

    @Autowired
    private LotRepositoryCustom lotRepositoryCustom;

    @Autowired
    private LotRepository lotRepository;

    @Before
    public void addLotTest() throws Exception {
        lot = new Lot();
        lot.setName("name");
        lot.setMinPrice(new Double("123"));
        lot.setBayoutPrice(new Double("11111"));
        lot.setDescription("Description");
        lot.setPhoto("123");
        lot.setDisable(false);
        lotRepository.saveAndFlush(lot);
    }

    @Test
    public void getLastLotsTest() {
        assertNotNull(lotRepositoryCustom.getLastLots(10));
    }

    @Test
    public void getLotsByPageTest() {
        assertNotNull(lotRepositoryCustom.getByPage(new Lot(), 1, 5));
    }
}
