package com.auction.repository;

import com.auction.model.entity.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Helga on 26.10.17.
 */
@Repository
public interface CategoryRepository extends AuctionGenericRepository<Category> {

    /**
     * This method for get category by name
     *
     * @param name
     * @return
     */
    @Query("select ct from Category ct where ct.name = :name")
    Category getCategoryByName(@Param("name") String name);

    /**
     * This method for update category
     *
     * @param name
     * @param id
     */
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Category ct set ct.name = :name where ct.id = :id")
    void update(@Param("name") String name, @Param("id") Long id);

    /**
     * This method for get count of all categories
     *
     * @return
     */
    @Query("select count(ct) from Category ct")
    int getCategorySize();
}
