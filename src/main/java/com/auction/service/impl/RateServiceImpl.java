package com.auction.service.impl;

import com.auction.converter.entity.RateConvert;
import com.auction.model.entity.Lot;
import com.auction.model.entity.Rate;
import com.auction.model.form.BaseForm;
import com.auction.repository.RateRepository;
import com.auction.service.LotService;
import com.auction.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Helga on 13.11.2017.
 */
@Service
public class RateServiceImpl extends AuctionGenericServiceImpl<Rate, BaseForm, RateConvert>
        implements RateService {

    @Autowired
    private LotService lotService;

    @Autowired
    private RateRepository rateRepository;

    /**
     * This method for get rate by id. If rate id is null, this method return null.
     *
     * @param id
     * @return
     */
    public Rate getRateById(Long id) {
        if (id != null) {
            return rateRepository.findOne(id);
        } else {
            return new Rate();
        }
    }

    /**
     * This method for get lits of rate by lot
     *
     * @param lotId
     * @return
     */
    public List<Rate> getRateByLot(Long lotId) {
        return rateRepository.getRateByLot(lotService.getLotById(lotId));
    }

    /**
     * This method for get last rate
     *
     * @param lot
     * @return
     */
    @Override
    public Rate getLastRate(Lot lot) {
        List<Rate> rates = rateRepository.getRateByLot(lot);
        if (rates.size() != 0) {
            return rates.get(rates.size() - 1);
        }
        return null;
    }
}
