package com.auction.service;

import com.auction.converter.entity.LotConverter;
import com.auction.converter.form.LotFormConverter;
import com.auction.model.entity.Lot;
import com.auction.model.form.LotCreateForm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static com.auction.ApplicationConstantsTest.LOT_TEST_DESCRIPTION;
import static com.auction.ApplicationConstantsTest.LOT_TEST_NAME;
import static com.auction.ApplicationConstantsTest.LOT_TEST_NEW_NAME;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Helga on 06.11.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appconfig-root-test.xml"})
@Transactional
//@TestPropertySource("/property/h2.properties")
public class LotServiceTest {

    @Autowired
    LotService lotService;
    @Autowired
    LotConverter lotConverter;
    @Autowired
    LotFormConverter lotFormConverter;

    private LotCreateForm lotCreateForm;

    @Before
    public void addLotTest() throws Exception {
        lotCreateForm = new LotCreateForm();
        lotCreateForm.setName(LOT_TEST_NAME);
        lotCreateForm.setDescription(LOT_TEST_DESCRIPTION);
        Lot lot = lotService.create(lotCreateForm);
        lotCreateForm.setId(lot.getId());
    }

    @Test
    public void getLastLotsTest() {
        assertNotNull(lotService.getLastLots(10));

    }

    @Test
    public void getLotByNameTest() {
        assertNotNull(lotService.getLotByName(lotCreateForm.getName()));
    }

    @Test
    public void getAllLotsTest() {
        assertNotNull(lotService.getAll());
    }

    @Test
    public void editLotTest() throws Exception {
        lotCreateForm.setName(LOT_TEST_NEW_NAME);
        lotService.updateLotByName(lotCreateForm);
        assertEquals(lotCreateForm.getName(), lotService.getLotById(lotCreateForm.getId()).getName());
    }
}
