package com.auction.repository;

import com.auction.model.entity.Lot;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Helga on 16.11.2017.
 */
@Component
public interface LotRepositoryCustom extends AbstractCustomRepository<Lot> {
    /**
     * This method get last count lots
     *
     * @param count
     * @return
     */
    List<Lot> getLastLots(int count);
}
