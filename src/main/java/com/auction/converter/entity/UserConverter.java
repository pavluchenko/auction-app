package com.auction.converter.entity;

import com.auction.model.entity.Role;
import com.auction.model.entity.User;
import com.auction.model.form.UserCreateForm;
import com.auction.service.RoleService;
import com.auction.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Helga on 02.11.17.
 */
@Component
public class UserConverter implements Converter<UserCreateForm, User> {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User convert(UserCreateForm userCreateForm) {
        User user = userService.getUserById(userCreateForm.getId());
        if (user == null) {
            user = new User();
        }
        user.setEmail(userCreateForm.getEmail());
        user.setRating(userCreateForm.getRating());
        if (StringUtils.isNotEmpty(userCreateForm.getPassword()) && StringUtils.isNotEmpty(userCreateForm.getPasswordConfirm())) {
            user.setPassword(bCryptPasswordEncoder.encode(userCreateForm.getPassword()));
            user.setPasswordConfirm(bCryptPasswordEncoder.encode(userCreateForm.getPasswordConfirm()));
        }

        if (userCreateForm.getId() != null) {
            // get role by user
            user.setRole((List<Role>) userService.getRoleByUser(userCreateForm.getId()));
        } else if (roleService.getUserRole() != null) {
            user.setRole(Arrays.asList(roleService.getUserRole()));
        }
        return user;
    }
}
