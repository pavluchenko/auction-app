package com.auction.converter.form;

import com.auction.model.entity.User;
import com.auction.model.form.UserCreateForm;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Helga on 16.11.2017.
 */
@Component
public class UserFormConverter implements Converter<User, UserCreateForm> {
    public UserCreateForm convert(User user) {
        UserCreateForm userCreateForm = new UserCreateForm();
        userCreateForm.setId(user.getId());
        userCreateForm.setEmail(user.getEmail());
        userCreateForm.setPassword(user.getPassword());
        userCreateForm.setPasswordConfirm(user.getPasswordConfirm());
        return userCreateForm;
    }
}
