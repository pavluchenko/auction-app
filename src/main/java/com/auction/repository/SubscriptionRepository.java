package com.auction.repository;


import com.auction.model.entity.Subscription;
import com.auction.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Helga on 24.11.2017.
 */
@Repository
public interface SubscriptionRepository extends AuctionGenericRepository<Subscription> {

    /**
     * This method for get list of subscriptions by user
     *
     * @param user
     * @return
     */
    @Query("select rt from Subscription rt where rt.user = :user")
    List<Subscription> getSubscriptionByUser(@Param("user") User user);

    /**
     * This method for get list of subscriptions by creator
     *
     * @param creator
     * @return
     */
    @Query("select rt from Subscription rt where rt.creator = :creator")
    List<Subscription> getSubscriptionByCreator(@Param("creator") User creator);

    /**
     * This method for get list of subscriptions by user and creator
     *
     * @param user
     * @param creator
     * @return
     */
    @Query("select rt from Subscription rt where rt.user = :user and rt.creator = :creator")
    Subscription getSubscriptionByCreatorAndUser(@Param("user") User user, @Param("creator") User creator);

}
