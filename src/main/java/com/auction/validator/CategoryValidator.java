package com.auction.validator;

import com.auction.model.entity.Category;
import com.auction.model.form.CategoryCreateForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Helga on 20.12.2017.
 */
@Component
public class CategoryValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Category.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CategoryCreateForm createForm = (CategoryCreateForm) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
    }
}
