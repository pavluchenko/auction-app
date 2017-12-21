package com.auction.service.impl;

import com.auction.converter.entity.SubscriptionConvert;
import com.auction.model.entity.Subscription;
import com.auction.model.form.SubscriptionCreateForm;
import com.auction.repository.SubscriptionRepository;
import com.auction.service.SubscriptionService;
import com.auction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Helga on 24.11.2017.
 */
@Service
public class SubscriptionServiceImpl extends AuctionGenericServiceImpl<Subscription, SubscriptionCreateForm, SubscriptionConvert>
        implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserService userService;

    /**
     * This method for get subscription by id
     *
     * @param id
     * @return
     */
    @Override
    public Subscription getSubscriptionById(Long id) {
        if (id != null) {
            return subscriptionRepository.findOne(id);
        } else {
            return new Subscription();
        }
    }

    /**
     * This method for get list of subscriptions by user id
     *
     * @param userId
     * @return
     */
    @Override
    public List<Subscription> getSubscriptionbyUser(Long userId) {
        return subscriptionRepository.getSubscriptionByUser(userService.getUserById(userId));
    }

    /**
     * This method for get list of subscriptions by lot creator id
     *
     * @param creatorId
     * @return
     */
    @Override
    public List<Subscription> getSubscriptionByCreator(Long creatorId) {
        return subscriptionRepository.getSubscriptionByUser(userService.getUserById(creatorId));
    }

    /**
     * This method for get subscription by user id and lot creator id
     *
     * @param userId
     * @param creatorId
     * @return
     */
    @Override
    public Subscription getSubscriptionByCreatorAndUser(Long userId, Long creatorId) {
        return subscriptionRepository.getSubscriptionByCreatorAndUser(userService.getUserById(creatorId), userService.getUserById(userId));
    }
}
