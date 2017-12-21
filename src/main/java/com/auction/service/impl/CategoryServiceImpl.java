package com.auction.service.impl;

import com.auction.converter.entity.CategoryConvert;
import com.auction.converter.form.CategoryFormConverter;
import com.auction.model.entity.Category;
import com.auction.model.form.CategoryCreateForm;
import com.auction.repository.CategoryRapositoryCustom;
import com.auction.repository.CategoryRepository;
import com.auction.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Helga on 01.11.17.
 */
@Service
public class CategoryServiceImpl extends AuctionGenericServiceImpl<Category, CategoryCreateForm, CategoryConvert>
        implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryFormConverter categoryConvert;

    @Autowired
    private CategoryRapositoryCustom categoryRapositoryCustom;

    /**
     * This method for get category from by id
     *
     * @param id
     * @return
     */
    public CategoryCreateForm getCategoryById(Long id) {
        return categoryConvert.convert(getCategoryId(id));
    }

    /**
     * This method for get category by id
     *
     * @param id
     * @return
     */
    @Override
    public Category getCategoryId(Long id) {
        if (id != null) {
            return categoryRepository.findOne(id);
        } else {
            return new Category();
        }
    }

    /**
     * This method for get category by name
     *
     * @param name
     * @return
     */
    public Category getCategoryByName(String name) {
        return categoryRepository.getCategoryByName(name);
    }

    /**
     * This method for update category name
     *
     * @param categoryCreateForm
     */
    @Override
    public void update(CategoryCreateForm categoryCreateForm) {
        categoryRepository.update(categoryCreateForm.getName(), categoryCreateForm.getId());
    }

    /**
     * This method for get list categories by page
     *
     * @param category
     * @param pageid
     * @param total
     * @return
     */
    @Override
    public List<Category> getCategoryByPage(Category category, int pageid, int total) {
        return (List<Category>) categoryRapositoryCustom.getByPage(category, pageid, total);
    }

    /**
     * This method for get count of all categories
     *
     * @return
     */
    @Override
    public int getCategorySize() {
        return categoryRepository.getCategorySize();
    }
}
