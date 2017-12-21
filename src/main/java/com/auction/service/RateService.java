package com.auction.service;

import com.auction.model.entity.Lot;
import com.auction.model.entity.Rate;
import com.auction.model.form.BaseForm;
import org.jvnet.hk2.annotations.Service;

import java.util.List;

/**
 * Created by Helga on 13.11.2017.
 */
@Service
public interface RateService extends AuctionGenericService<Rate, BaseForm> {

    /**
     * This method for get rate by id. If rate id is null, this method return null.
     *
     * @param id
     * @return
     */
    Rate getRateById(Long id);

    /**
     * This method for get lits of rate by lot
     *
     * @param lotId
     * @return
     */
    List<Rate> getRateByLot(Long lotId);

    /**
     * This method for get last rate
     *
     * @param lot
     * @return
     */
    Rate getLastRate(Lot lot);
}
