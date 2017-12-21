package com.auction.validator;

import com.auction.model.entity.Feature;
import com.auction.model.form.FeatureCreateForm;
import com.auction.service.FeatureService;
import com.auction.service.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 * Created by Helga on 04.12.2017.
 */
@Component
public class FeatureValidator implements Validator {

    @Autowired
    private FeatureService featureService;

    @Autowired
    private LotService lotService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Feature.class.equals(aClass);
    }

    /**
     * Validate feature name by empty, by length and by duplicate
     *
     * @param o      RateCreateForm
     * @param errors
     */
    @Override
    public void validate(Object o, Errors errors) {
        FeatureCreateForm feature = (FeatureCreateForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty");

    }
}
