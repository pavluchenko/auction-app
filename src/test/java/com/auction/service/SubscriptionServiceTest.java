package com.auction.service;

import com.auction.model.entity.Subscription;
import com.auction.model.entity.User;
import com.auction.model.form.SubscriptionCreateForm;
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
public class SubscriptionServiceTest {

    public SubscriptionCreateForm subscriptionCreateForm;

    @Autowired
    private UserService userService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Before
    public void addFeatureTest() throws Exception {
        subscriptionCreateForm = new SubscriptionCreateForm();
        subscriptionCreateForm.setDate(new Date());

        UserCreateForm user = new UserCreateForm();
        user.setEmail("name");
        user.setPassword("password");
        user.setPasswordConfirm("password");
        user.setRating("5");
        User user1 = userService.create(user);
        user.setId(user1.getId());
        subscriptionCreateForm.setUserId(user1.getId());

        UserCreateForm creator = new UserCreateForm();
        creator.setEmail("creator");
        creator.setPassword("password");
        creator.setPasswordConfirm("password");
        creator.setRating("5");
        User userNew = userService.create(creator);
        creator.setId(userNew.getId());

        subscriptionCreateForm.setCreatorId(userNew.getId());
        Subscription subscription = subscriptionService.create(subscriptionCreateForm);
        subscriptionCreateForm.setId(subscription.getId());

    }

    @Test
    public void getSubscriptionByIdTest() {
        assertNotNull(subscriptionService.getSubscriptionById(subscriptionCreateForm.getId()));
    }
}
