package com.auction.service;

import com.auction.converter.entity.UserConverter;
import com.auction.converter.form.UserFormConverter;
import com.auction.model.entity.User;
import com.auction.model.form.UserCreateForm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static com.auction.ApplicationConstantsTest.USER_TEST_EMAIL;
import static com.auction.ApplicationConstantsTest.USER_TEST_NEW_EMAIL;
import static com.auction.ApplicationConstantsTest.USER_TEST_PASSWORD;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Helga on 04.11.17.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appconfig-root-test.xml"})
@Transactional
public class UserServiceTest {

    UserCreateForm userCreateForm;

    @Autowired
    UserService userService;

    @Autowired
    UserConverter userConverter;

    @Autowired
    UserFormConverter userFormConverter;

    @Before
    public void addUserTest() throws Exception {
        userCreateForm = new UserCreateForm();
        userCreateForm.setEmail(USER_TEST_EMAIL);
        userCreateForm.setPassword(USER_TEST_PASSWORD);
        userCreateForm.setPasswordConfirm(USER_TEST_PASSWORD);
        User user = userService.create(userCreateForm);
        userCreateForm.setId(user.getId());
        System.out.println(userCreateForm.getEmail());
    }

    @Test
    public void deleteUserTest() throws Exception {
        userService.delete(userCreateForm);
    }

    @Test
    public void editUserTest() throws Exception {
        userCreateForm.setEmail(USER_TEST_NEW_EMAIL);
        userService.update(userCreateForm);
        assertEquals(userCreateForm.getEmail(), userService.getUserById(userCreateForm.getId()).getEmail());
    }

    @Test
    public void getAllUsersTest() throws Exception {
        assertNotNull(userService.getAll());
    }

    @Test
    public void getByIdUserTest() throws Exception {
        Long id = userCreateForm.getId();
        assertNotNull(userService.getUserById(id));
    }
}
