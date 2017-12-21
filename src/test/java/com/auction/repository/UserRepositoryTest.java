package com.auction.repository;

import com.auction.model.entity.User;
import com.auction.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static com.auction.ApplicationConstantsTest.USER_TEST_EMAIL;
import static com.auction.ApplicationConstantsTest.USER_TEST_PASSWORD;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Helga on 05.12.2017.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appconfig-root-test.xml"})
@Transactional
public class UserRepositoryTest {

    public User user;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Before
    public void addUserTest() throws Exception {
        user = new User();
        user.setEmail(USER_TEST_EMAIL);
        user.setPassword(USER_TEST_PASSWORD);
        user.setPasswordConfirm(USER_TEST_PASSWORD);
        userRepository.saveAndFlush(user);
    }

    @Test
    public void getUserByIdTest() {
        assertNotNull(userRepository.findOne(user.getId()));
    }

    @Test
    public void getUserByEmailTest() {
        userRepository.getUserByEmail(user.getEmail());
    }

    @Test
    public void updateTest() {
    }

    @Test
    public void disableUserTest() {
        userRepository.disableUser(user.getId());
        assertEquals(userService.getUserById(user.getId()).getDisable(), Boolean.valueOf(true));
    }
}
