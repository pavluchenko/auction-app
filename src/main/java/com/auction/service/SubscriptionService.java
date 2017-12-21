package com.auction.service;

import com.auction.model.entity.Subscription;
import com.auction.model.form.SubscriptionCreateForm;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Helga on 24.11.2017.
 */
@Service
public interface SubscriptionService extends AuctionGenericService<Subscription, SubscriptionCreateForm> {

    /**
     * This method for get subscription by id
     *
     * @param id
     * @return
     */
    Subscription getSubscriptionById(Long id);

    /**
     * This method for get list of subscriptions by user id
     *
     * @param user
     * @return
     */
    List<Subscription> getSubscriptionbyUser(Long user);

    /**
     * This method for get list of subscriptions by lot creator id
     *
     * @param creator
     * @return
     */
    List<Subscription> getSubscriptionByCreator(Long creator);

    /**
     * This method for get subscription by user id and lot creator id
     *
     * @param user
     * @param creator
     * @return
     */
    Subscription getSubscriptionByCreatorAndUser(Long user, Long creator);
}
