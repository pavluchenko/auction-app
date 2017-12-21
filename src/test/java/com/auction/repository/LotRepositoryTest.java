package com.auction.repository;

import com.auction.model.entity.Lot;
import com.auction.model.entity.User;
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
public class LotRepositoryTest {

    private Lot lot;

    @Autowired
    private LotRepository lotRepository;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void addLotTest() throws Exception {
        lot = new Lot();
        lot.setName("name");
        lot.setMinPrice(new Double("123"));
        lot.setBayoutPrice(new Double("11111"));
        lot.setDescription("Description");
        lot.setPhoto("123");
        lot.setDisable(false);
        User user = new User();
        userRepository.saveAndFlush(user);
        lot.setUser(user);
        lotRepository.saveAndFlush(lot);
    }

    @Test
    public void getLotByNameTest() {
        assertNotNull(lotRepository.getLotByName(lot.getName()));
    }

    @Test
    public void searchLotByNameTest() {
        assertNotNull(lotRepository.searchLotByName(lot.getName()));
    }

    @Test
    public void getLotByUserTest() {
        assertNotNull(lotRepository.getLotByUser(lot.getUser()));
    }

    @Test
    public void getLotByIdTest() {
        assertNotNull(lotRepository.findOne(lot.getId()));
    }

    @Test
    public void getPhotoPathTest() {
        assertNotNull(lotRepository.getPhotoPath(lot.getId()));
    }

    @Test
    public void updateTest() {

    }

    @Test
    public void updateMinPriceTest() {

    }

    @Test
    public void updateNameTest() {

    }

    @Test
    public void disableLotTest() {

    }

    @Test
    public void setPhotoTest() {
        lotRepository.setPhoto("1234", lot.getId());
    }
}
