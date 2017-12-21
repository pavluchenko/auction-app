package com.auction.converter.form;

import com.auction.model.entity.Subscription;
import com.auction.model.form.SubscriptionCreateForm;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Helga on 09.12.2017.
 */
@Component
public class SubscriptionFormConvert implements Converter<Subscription, SubscriptionCreateForm> {
    @Override
    public SubscriptionCreateForm convert(Subscription subscription) {
        SubscriptionCreateForm subscriptionCreateForm = new SubscriptionCreateForm();
        subscriptionCreateForm.setId(subscription.getId());
        subscriptionCreateForm.setUserId(subscription.getUser().getId());
        subscriptionCreateForm.setCreatorId(subscription.getCreator().getId());
        //subscriptionCreateForm.setDate(subscription.getDate());
        return subscriptionCreateForm;
    }
}
