package com.auction.converter.form;

import com.auction.model.entity.Category;
import com.auction.model.entity.Lot;
import com.auction.model.form.CategoryCreateForm;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Helga on 05.12.2017.
 */
@Component
public class CategoryFormConverter implements Converter<Category, CategoryCreateForm> {

    @Override
    public CategoryCreateForm convert(Category category) {
        CategoryCreateForm createForm = new CategoryCreateForm();
        createForm.setId(category.getId());
        createForm.setName(category.getName());
        List<Long> lotIds = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(category.getLots())) {
            List<Lot> lots = category.getLots();
            for (Lot lot : lots) {
                lotIds.add(lot.getId());
            }
            createForm.setLots(lotIds);
        }
        return createForm;
    }
}
