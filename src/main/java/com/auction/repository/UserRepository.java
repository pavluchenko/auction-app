package com.auction.repository;

import com.auction.model.entity.Role;
import com.auction.model.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Helga on 23.10.17.
 */
@Repository
public interface UserRepository extends AuctionGenericRepository<User> {

    /**
     * This method for get user by email
     *
     * @param email
     * @return
     */
    @Query("select usr from User usr where usr.email = :email")
    User getUserByEmail(@Param("email") String email);

    /**
     * This method for get list of role by user
     *
     * @param id
     * @return
     */
    @Query("select usr.role from User usr where usr.id = :id")
    List<Role> getRoleByUser(@Param("id") Long id);

    /**
     * This method for update email and rating by id
     *
     * @param email
     * @param rating
     * @param id
     */
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update User us set us.email= :email, us.rating= :rating where us.id = :id")
    void update(@Param("email") String email, @Param("rating") String rating, @Param("id") Long id);

    /**
     * This method for disable user
     *
     * @param id
     */
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update User us set us.disable = true where us.id = :id")
    void disableUser(@Param("id") Long id);

    /**
     * This method for get count of all users
     *
     * @return
     */
    @Query("select count(us) from User us")
    int getUserSize();
}
