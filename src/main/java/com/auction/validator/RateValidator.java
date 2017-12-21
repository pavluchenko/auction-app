package com.auction.validator;

import com.auction.model.entity.Lot;
import com.auction.model.entity.User;
import com.auction.model.form.RateCreateForm;
import org.springframework.stereotype.Component;

/**
 * Created by Helga on 20.11.2017.
 */
@Component
public class RateValidator {
    /**
     * This method for validate rate
     *
     * @param email
     * @param user
     * @param lot
     * @param rateCreateForm
     * @param lastRate
     * @return
     */
    public boolean validate(String email, User user, Lot lot, RateCreateForm rateCreateForm,
                            Double lastRate) {
        if (email != null && user != null && lot != null) {
            // min price and last rate
            if (lot.getMinPrice() != null && rateCreateForm.getPrice() < lot.getMinPrice() ||
                    lastRate != null && rateCreateForm.getPrice() <= lastRate) {
                return false;
            }
            return true;
        }
        return false;
    }
}