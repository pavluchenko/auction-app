package com.auction.service;

import com.auction.model.entity.Category;
import com.auction.model.form.CategoryCreateForm;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Helga on 01.11.17.
 */
@Service
public interface CategoryService extends AuctionGenericService<Category, CategoryCreateForm> {
    /**
     * This method for get category from by id
     *
     * @param id
     * @return
     */
    CategoryCreateForm getCategoryById(Long id);

    /**
     * This method for get category by id
     *
     * @param id
     * @return
     */
    Category getCategoryId(Long id);

    /**
     * This method for get category by name
     *
     * @param name
     * @return
     */
    Category getCategoryByName(String name);

    /**
     * This method for update category name
     *
     * @param categoryCreateForm
     */
    void update(CategoryCreateForm categoryCreateForm);

    /**
     * This method for get list categories by page
     *
     * @param category
     * @param pageid
     * @param total
     * @return
     */
    List<Category> getCategoryByPage(Category category, int pageid, int total);

    /**
     * This method for get count of all categories
     *
     * @return
     */
    int getCategorySize();
}
