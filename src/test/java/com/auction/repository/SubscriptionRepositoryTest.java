package com.auction.repository;

import com.auction.model.entity.Subscription;
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
public class SubscriptionRepositoryTest {

    public Subscription subscription;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void addSubscriptionTest() throws Exception {
        subscription = new Subscription();
        subscription.setDate(new Date());
        User user = new User();
        userRepository.saveAndFlush(user);

        subscription.setUser(user);

        User creator = new User();
        userRepository.saveAndFlush(creator);
        subscription.setCreator(creator);

        subscriptionRepository.saveAndFlush(subscription);
    }

    @Test
    public void getSubscriptionByIdTest() {
        assertNotNull(subscriptionRepository.findOne(subscription.getId()));
    }

    @Test
    public void getSubscriptionByUserTest() {
        assertNotNull(subscriptionRepository.getSubscriptionByUser(subscription.getUser()));
    }
}
