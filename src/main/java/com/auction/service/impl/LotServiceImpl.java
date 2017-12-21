package com.auction.service.impl;

import com.auction.converter.entity.LotConverter;
import com.auction.converter.form.LotFormConverter;
import com.auction.model.entity.Lot;
import com.auction.model.form.LotCreateForm;
import com.auction.repository.LotRepository;
import com.auction.repository.LotRepositoryCustom;
import com.auction.repository.UserRepository;
import com.auction.service.CategoryService;
import com.auction.service.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Helga on 26.10.17.
 */
@Service
public class LotServiceImpl extends AuctionGenericServiceImpl<Lot, LotCreateForm, LotConverter>
        implements LotService {

    @Autowired
    private LotRepository lotRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LotRepositoryCustom lotRepositoryCustom;

    @Autowired
    private LotConverter lotConverter;

    @Autowired
    private LotFormConverter lotFormConverter;

    @Autowired
    private CategoryService categoryService;


    /**
     * This method for get count list of lots
     *
     * @param count
     * @return
     */
    public List<Lot> getLastLots(int count) {
        return lotRepositoryCustom.getLastLots(count);
    }

    /**
     * This method for get list of lots by name
     *
     * @param name
     * @return
     */
    public List<Lot> getLotByName(String name) {
        return lotRepository.getLotByName(name);
    }

    /**
     * This method for get list of lots by user
     *
     * @param userId
     * @return
     */
    public List<Lot> getLotByUser(Long userId) {
        return lotRepository.getLotByUser(userRepository.findOne(userId));
    }

    /**
     * This method for get lot by id. If lot id is null, this method return null.
     *
     * @param id
     * @return
     */
    public Lot getLotById(Long id) {
        if (id != null) {
            return lotRepository.findOne(id);
        } else {
            return null;
        }
    }

    /**
     * This method for get lot form by id
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public LotCreateForm getLotFormById(Long id) {
        if (id != null) {
            Lot lot = lotRepository.findOne(id);
            if (lot == null) {
                return null;
            }
            return lotFormConverter.convert(lot);
        } else {
            return null;
        }
    }

    /**
     * This method for update lot
     *
     * @param lotCreateForm
     */
    public void update(LotCreateForm lotCreateForm) {
        lotRepository.update(lotCreateForm.getName(), lotCreateForm.getDescription(),
                categoryService.getCategoryId(lotCreateForm.getCategoryId()), lotCreateForm.getId(),
                lotCreateForm.getBayoutPrice(), lotCreateForm.getPhoto(), lotCreateForm.getMinPrice());
    }

    /**
     * This method for update min price
     *
     * @param minPrice
     * @param lotId
     */
    @Override
    public void updateMinPrice(Double minPrice, Long lotId) {
        lotRepository.updateMinPrice(minPrice, lotId);
    }

    /**
     * This method for update by name
     *
     * @param lotCreateForm
     */
    public void updateLotByName(LotCreateForm lotCreateForm) {
        lotRepository.updateName(lotConverter.convert(lotCreateForm), lotCreateForm.getId());
    }

    /**
     * This method for disable lot
     *
     * @param id
     */
    @Override
    public void disableLot(Long id) {
        lotRepository.disableLot(id);
    }

    /**
     * This method for get list of lots by page
     *
     * @param pageid
     * @param total
     * @return
     */
    @Override
    public List<Lot> getLotsByPage(int pageid, int total) {
        return (List<Lot>) lotRepositoryCustom.getByPage(new Lot(), pageid, total);
    }

    /**
     * This method for set photo path by lot id
     *
     * @param path
     * @param id
     */
    @Override
    public void setPhoto(String path, Long id) {
        lotRepository.setPhoto(path, id);
    }

    /**
     * This method for get photo path by lot id
     *
     * @param id
     * @return
     */
    @Override
    public String getPhotoPath(Long id) {
        if (id != null) {
            return lotRepository.getPhotoPath(id);
        } else {
            return null;
        }
    }

    /**
     * This method for get list of lots by name
     *
     * @param name
     * @return
     */
    @Override
    public List<LotCreateForm> searchLotByName(String name) {
        List<Lot> lots = lotRepository.searchLotByName("%" + name + "%");
        List<LotCreateForm> result = new ArrayList<>();
        for (Lot lot : lots) {
            LotCreateForm lotCreateForm = lotFormConverter.convert(lot);
            result.add(lotCreateForm);
        }

        return result;
    }

    /**
     * This method for get count of all lots
     *
     * @return
     */
    @Override
    public int getLotSize() {
        return lotRepository.getLotSize();
    }
}
