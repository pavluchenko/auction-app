package com.auction.repository;

import com.auction.model.entity.Lot;
import com.auction.model.entity.Rate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Helga on 10.11.17.
 */
public interface RateRepository extends AuctionGenericRepository<Rate> {

    /**
     * This method for get list of rates by lot
     *
     * @param lot
     * @return
     */
    @Query("select rt from Rate rt where rt.lot = :lot ORDER BY rt.date")
    List<Rate> getRateByLot(@Param("lot") Lot lot);
}
