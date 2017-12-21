package com.auction.repository;

import com.auction.model.entity.Category;
import com.auction.model.entity.Lot;
import com.auction.model.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Helga on 26.10.17.
 */
@Repository
public interface LotRepository extends AuctionGenericRepository<Lot> {

    /**
     * This method for get list of lots by name
     *
     * @param name
     * @return
     */
    @Query("select lt from Lot lt where lt.name = :name")
    List<Lot> getLotByName(@Param("name") String name);

    /**
     * This method for get list of lots like name
     *
     * @param name
     * @return
     */
    @Query("select lt from Lot lt where lt.name like :name")
    List<Lot> searchLotByName(@Param("name") String name);

    /**
     * This method for get list of lots by user
     *
     * @param user
     * @return
     */
    @Query("select lt from Lot lt where lt.user = :user")
    List<Lot> getLotByUser(@Param("user") User user);

    /**
     * This method for get photo path by lot id
     *
     * @param id
     * @return
     */
    @Query("select lt.photo from Lot lt where lt.id = :id")
    String getPhotoPath(@Param("id") Long id);

    /**
     * This method for update lot by id
     *
     * @param name
     * @param description
     * @param category
     * @param id
     * @param bayoutPrice
     * @param photo
     * @param minPrice
     */
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Lot lt set lt.name = :name, lt.description = :description, lt.category = :category, lt.bayoutPrice = :bayoutPrice, lt.photo = :photo, lt.minPrice = :minPrice where lt.id = :id")
    void update(@Param("name") String name, @Param("description") String description, @Param("category") Category category,
                @Param("id") Long id, @Param("bayoutPrice") Double bayoutPrice, @Param("photo") String photo, @Param("minPrice") Double minPrice);


    /**
     * This method for update min price by id
     *
     * @param minPrice
     * @param id
     */
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Lot lt set lt.minPrice = :minPrice where lt.id = :id")
    void updateMinPrice(@Param("minPrice") Double minPrice, @Param("id") Long id);

    /**
     * This method for update name price by id
     *
     * @param lot
     * @param id
     */
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Lot lt set lt = :lot where lt.id = :id")
    void updateName(@Param("lot") Lot lot, @Param("id") Long id);

    /**
     * This method for disable lot
     *
     * @param id
     */
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Lot lt set lt.disable=true where lt.id = :id")
    void disableLot(@Param("id") Long id);

    /**
     * This method for set photo path
     *
     * @param photo
     * @param id
     */
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Lot lt set lt.photo= :photo where lt.id = :id")
    void setPhoto(@Param("photo") String photo, @Param("id") Long id);

    /**
     * This method for get count of all lots
     *
     * @return
     */
    @Query("select count(lt) from Lot lt")
    int getLotSize();
}
