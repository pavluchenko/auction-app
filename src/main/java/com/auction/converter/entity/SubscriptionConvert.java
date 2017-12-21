package com.auction.converter.entity;

import com.auction.model.entity.Subscription;
import com.auction.model.form.SubscriptionCreateForm;
import com.auction.service.SubscriptionService;
import com.auction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Helga on 24.11.2017.
 */
@Component
public class SubscriptionConvert implements Converter<SubscriptionCreateForm, Subscription> {

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private UserService userService;


    @Override
    public Subscription convert(SubscriptionCreateForm subscriptionCreateForm) {
        Subscription subscription = subscriptionService.getSubscriptionById(subscriptionCreateForm.getId());
        if (subscription == null) {
            subscription = new Subscription();
        }
        //subscription.setDate(subscriptionCreateForm.getDate());
        subscription.setUser(userService.getUserById(subscriptionCreateForm.getUserId()));
        subscription.setCreator(userService.getUserById(subscriptionCreateForm.getCreatorId()));
        return subscription;
    }
}
