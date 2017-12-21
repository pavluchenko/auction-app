package com.auction.service;

import com.auction.model.entity.Lot;
import com.auction.model.form.LotCreateForm;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Helga on 26.10.17.
 */
@Service
public interface LotService extends AuctionGenericService<Lot, LotCreateForm> {

    /**
     * This method for get count list of lots
     *
     * @param count
     * @return
     */
    List<Lot> getLastLots(int count);

    /**
     * This method for get list of lots by name
     *
     * @param name
     * @return
     */
    List<Lot> getLotByName(String name);

    /**
     * This method for get list of lots by user
     *
     * @param userId
     * @return
     */
    List<Lot> getLotByUser(Long userId);

    /**
     * This method for get lot by id. If lot id is null, this method return null.
     *
     * @param id
     * @return
     */
    Lot getLotById(Long id);

    /**
     * This method for get lot form by id
     *
     * @param id
     * @return
     */
    LotCreateForm getLotFormById(Long id);

    /**
     * This method for update lot
     *
     * @param lotCreateForm
     */
    void update(LotCreateForm lotCreateForm);

    /**
     * This method for update min price
     *
     * @param minPrice
     * @param lotId
     */
    void updateMinPrice(Double minPrice, Long lotId);

    /**
     * This method for update by name
     *
     * @param lotCreateForm
     */
    void updateLotByName(LotCreateForm lotCreateForm);

    /**
     * This method for disable lot
     *
     * @param id
     */
    void disableLot(Long id);

    /**
     * This method for get list of lots by page
     *
     * @param pageid
     * @param total
     * @return
     */
    List<Lot> getLotsByPage(int pageid, int total);

    /**
     * This method for set photo path by lot id
     *
     * @param path
     * @param id
     */
    void setPhoto(String path, Long id);

    /**
     * This method for get photo path by lot id
     *
     * @param id
     * @return
     */
    String getPhotoPath(Long id);

    /**
     * This method for get list of lots by name
     *
     * @param name
     * @return
     */
    List<LotCreateForm> searchLotByName(String name);

    /**
     * This method for get count of all lots
     *
     * @return
     */
    int getLotSize();
}
