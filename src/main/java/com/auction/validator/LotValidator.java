package com.auction.validator;

import com.auction.model.entity.Lot;
import com.auction.model.form.LotCreateForm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.auction.utils.ApplicationConstants.EMAIL_MAX_LENGTH;
import static com.auction.utils.ApplicationConstants.LOT_MIN_LENGTH;

/**
 * Created by Helga on 20.11.2017.
 */

@Component
public class LotValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Lot.class.equals(aClass);
    }

    /**
     * Validate Lot name by empty and name, lot descriptions, bayoutPrice, minPrice, file by empty
     *
     * @param o      LotCreateForm
     * @param errors
     */
    @Override
    public void validate(Object o, Errors errors) {
        LotCreateForm lot = (LotCreateForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        if (StringUtils.isEmpty(lot.getName())) {
            errors.rejectValue("name", "size.lotForm.email");
        } else {
            if (lot.getName().length() < LOT_MIN_LENGTH || lot.getName().length() > EMAIL_MAX_LENGTH) {
                errors.rejectValue("name", "size.lotForm.email");
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bayoutPrice", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "minPrice", "NotEmpty");
    }
}
