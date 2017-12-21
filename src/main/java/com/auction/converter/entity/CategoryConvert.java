package com.auction.converter.entity;

import com.auction.model.entity.Category;
import com.auction.model.entity.Lot;
import com.auction.model.form.CategoryCreateForm;
import com.auction.service.CategoryService;
import com.auction.service.LotService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Helga on 13.11.2017.
 */
@Component
public class CategoryConvert implements Converter<CategoryCreateForm, Category> {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private LotService lotService;


    public Category convert(final CategoryCreateForm categoryCreateForm) {
        Category category = categoryService.getCategoryId(categoryCreateForm.getId());
        if (category == null) {
            category = new Category();
        }
        category.setName(categoryCreateForm.getName());
        category.setId(categoryCreateForm.getId());

        if (CollectionUtils.isNotEmpty(categoryCreateForm.getLots())) {
            List<Lot> lots = new ArrayList<>();
            for (Long id : categoryCreateForm.getLots()) {
                lots.add(lotService.getLotById(id));
            }
            category.setLots(lots);
        }

        return category;
    }
}
