package com.auction.repository;

import com.auction.model.entity.Feature;
import com.auction.model.entity.Lot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Helga on 24.11.2017.
 */
@Repository
public interface FeatureRepository extends AuctionGenericRepository<Feature> {

    /**
     * This method for get list of features by name
     *
     * @param name
     * @param lot
     * @return
     */
    @Query("select rt from Feature rt where rt.name = :name and  rt.lot = :lot")
    List<Feature> getFeatureByName(@Param("name") String name, @Param("lot") Lot lot);
}
