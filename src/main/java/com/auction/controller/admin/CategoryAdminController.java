package com.auction.controller.admin;

import com.auction.model.entity.Category;
import com.auction.model.form.CategoryCreateForm;
import com.auction.service.AuctionGenericService;
import com.auction.service.CategoryService;
import com.auction.utils.ApplicationConstants;
import com.auction.validator.CategoryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Helga on 07.12.2017.
 */
@RequestMapping(value = "admin/category")
@Controller
public class CategoryAdminController extends GeneralAdminController<Category, CategoryCreateForm> {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryValidator categoryValidator;

    public CategoryAdminController(AuctionGenericService<Category, CategoryCreateForm> service) {
        super(service);
    }

    /**
     * This method for get page with category table
     *
     * @param pageId
     * @return
     */
    @RequestMapping(value = "/{pageId}", method = {RequestMethod.GET})
    public ModelAndView categories(@PathVariable int pageId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categoryForm", new CategoryCreateForm());
        modelAndView.setViewName("admin/categories");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        if (email != null) {
            modelAndView.addObject("loggedInUserEmail", email);
        }
        modelAndView.addObject("categories", categoryService.getCategoryByPage(new Category(), (pageId - 1) * ApplicationConstants.TOTAL_SIZE, ApplicationConstants.TOTAL_SIZE));
        modelAndView.addObject("pagCategories", pagination(pageId, categoryService.getCategorySize()));

        return modelAndView;
    }

    /**
     * This method for update category
     *
     * @param categoryCreateForm
     * @return
     */
    @RequestMapping(value = "update", method = {RequestMethod.POST})
    public String updateCategory(@ModelAttribute("categoryForm") CategoryCreateForm categoryCreateForm, BindingResult bindingResult) {
        categoryValidator.validate(categoryCreateForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "redirect:/admin/category/1";
        }
        categoryService.update(categoryCreateForm);
        return "redirect:/admin/category";
    }

}
