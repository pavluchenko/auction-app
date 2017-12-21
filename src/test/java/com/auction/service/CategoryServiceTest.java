package com.auction.service;

import com.auction.model.entity.Category;
import com.auction.model.form.CategoryCreateForm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Helga on 05.12.2017.
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appconfig-root-test.xml"})
@Transactional
public class CategoryServiceTest {

    public CategoryCreateForm categoryCreateForm;

    @Autowired
    private CategoryService categoryService;

    @Before
    public void addCategoryTest() throws Exception {
        categoryCreateForm = new CategoryCreateForm();
        categoryCreateForm.setName("Category");
        Category category = categoryService.create(categoryCreateForm);
        categoryCreateForm.setId(category.getId());

    }

    @Test
    public void getCategoryByIdTest() {
        CategoryCreateForm categoryById = categoryService.getCategoryById(categoryCreateForm.getId());
        assertNotNull(categoryById);
    }

    @Test
    public void getCategoryByNameTest() {
        Category categoryByName = categoryService.getCategoryByName(categoryCreateForm.getName());
        assertNotNull(categoryByName);
    }

}
