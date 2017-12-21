package com.auction.controller.admin;

import com.auction.controller.UtilsResolver;
import com.auction.model.entity.BaseEntity;
import com.auction.model.form.BaseForm;
import com.auction.model.form.UserCreateForm;
import com.auction.service.AuctionGenericService;
import com.auction.validator.CategoryValidator;
import com.auction.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>
 * Created by Helga on 17.11.2017.
 */
@Controller
public abstract class GeneralAdminController<T extends BaseEntity, TForm extends BaseForm> extends UtilsResolver {
    private AuctionGenericService<T, TForm> service;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private CategoryValidator categoryValidator;

    public GeneralAdminController(AuctionGenericService<T, TForm> service) {
        this.service = service;
    }

    /**
     * This general method for create user/category
     *
     * @param tForm
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    String create(@ModelAttribute("tForm") TForm tForm, BindingResult bindingResult) {
        if (tForm.getClass().equals(UserCreateForm.class)) {
            userValidator.validate(tForm, bindingResult);

            if (bindingResult.hasErrors()) {
                return "redirect:/admin/user/1";
            }
        } else {
            categoryValidator.validate(tForm, bindingResult);
            if (bindingResult.hasErrors()) {
                return "redirect:/admin/category/1";
            }
        }
        service.create(tForm);
        return getPath(tForm);
    }

    /**
     * This general mathod for delete user/category
     *
     * @param tForm
     * @return
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    String delete(@ModelAttribute("id") TForm tForm) {
        service.delete(tForm);
        return getPath(tForm);
    }

    String getPath(TForm tForm) {
        String path = "redirect:/admin";
        if (tForm.getClass().equals(UserCreateForm.class)) {
            return path + "/user/1";
        } else {
            return path + "/category/1";
        }
    }


}
