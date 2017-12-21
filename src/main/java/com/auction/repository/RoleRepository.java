package com.auction.repository;

import com.auction.model.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Helga on 31.10.17.
 */
@Repository
public interface RoleRepository extends AuctionGenericRepository<Role> {

    /**
     * This method for get USER_ROLE
     *
     * @param name
     * @return
     */
    @Query("select rt from Role rt where rt.name = :name")
    Role getRoleUser(@Param("name") String name);

}
