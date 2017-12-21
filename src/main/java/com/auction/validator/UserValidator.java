package com.auction.validator;

import com.auction.model.entity.User;
import com.auction.model.form.UserCreateForm;
import com.auction.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.auction.utils.ApplicationConstants.EMAIL_MAX_LENGTH;
import static com.auction.utils.ApplicationConstants.EMAIL_MIN_LENGTH;
import static com.auction.utils.ApplicationConstants.PASSWORD_MAX_LENGTH;
import static com.auction.utils.ApplicationConstants.PASSWORD_MIN_LENGTH;


/**
 * Created by Helga on 30.10.17.
 */
@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    /**
     * This method for validate user email and password by empty and length, password and password confirm by equals
     *
     * @param o
     * @param errors
     */
    public void validate(Object o, final Errors errors) {
        UserCreateForm user = (UserCreateForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (StringUtils.isEmpty(user.getEmail())) {
            errors.rejectValue("email", "size.userForm.email");
        } else {
            if (user.getEmail().length() < EMAIL_MIN_LENGTH || user.getEmail().length() > EMAIL_MAX_LENGTH) {
                errors.rejectValue("email", "size.userForm.email");
            }
        }
        if (userService.getUserByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "duplicate.userForm.email");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() == 0) {
            errors.rejectValue("password", "size.userForm.password");
        } else {
            if (user.getPassword().length() < PASSWORD_MIN_LENGTH || user.getPassword().length() > PASSWORD_MAX_LENGTH) {
                errors.rejectValue("password", "size.userForm.password");
            }
        }

        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            errors.rejectValue("passwordConfirm", "diff.userForm.passwordConfirm");
        }
    }
}

