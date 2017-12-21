package com.auction.repository;

import com.auction.model.entity.Feature;
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
public class FeatureRepositoryTest {

    private Feature feature;

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private LotRepository lotRepository;

    @Before
    public void addFeatureTest() throws Exception {
        feature = new Feature();
        feature.setName("1");
        feature.setDescription("2");
        Lot lot = new Lot();
        lotRepository.saveAndFlush(lot);
        feature.setLot(lot);
        featureRepository.saveAndFlush(feature);
    }


    @Test
    public void getFeatureByIdTest() {
        assertNotNull(featureRepository.findOne(feature.getId()));
    }

    @Test
    public void getFeatureByNameTest() {
        assertNotNull(featureRepository.getFeatureByName(feature.getName(), feature.getLot()));
    }
}
