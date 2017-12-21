package com.auction.service;

import com.auction.converter.form.LotFormConverter;
import com.auction.model.entity.Feature;
import com.auction.model.entity.Lot;
import com.auction.model.form.FeatureCreateForm;
import com.auction.model.form.LotCreateForm;
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
public class FeatureServiceTest {

    public FeatureCreateForm featureCreateForm;

    @Autowired
    private FeatureService featureService;

    @Autowired
    private LotService lotService;

    @Autowired
    private LotFormConverter lotFormConverter;

    @Before
    public void addFeatureTest() throws Exception {
        featureCreateForm = new FeatureCreateForm();
        Lot lot = lotService.create(new LotCreateForm());
        featureCreateForm.setLotId(lot.getId());
        featureCreateForm.setName("name");
        featureCreateForm.setDescription("description");
        Feature feature = featureService.create(featureCreateForm);
        featureCreateForm.setId(feature.getId());
    }

    @Test
    public void getFeatureByIdTest() {
        assertNotNull(featureService.getFeatureById(featureCreateForm.getId()));

    }

    @Test
    public void getFeatureByNameTest() {
        //assertNotNull(featureService.getFeatureByName(featureCreateForm.getName(), featureCreateForm.getLot()));
    }
}
