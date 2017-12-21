package com.auction.controller.admin;

import com.auction.model.entity.User;
import com.auction.model.form.UserCreateForm;
import com.auction.service.AuctionGenericService;
import com.auction.service.UserService;
import com.auction.utils.ApplicationConstants;
import com.auction.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Locale;

/**
 * Created by Helga on 03.12.2017.
 */

@RequestMapping(value = "admin/user")
@Controller
public class UserAdminController extends GeneralAdminController<User, UserCreateForm> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAdminController.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    public UserAdminController(AuctionGenericService<User, UserCreateForm> service) {
        super(service);
    }

    /**
     * This method for get page with users table
     *
     * @param pageId
     * @return
     */
    @RequestMapping(value = "/{pageId}", method = {RequestMethod.GET})
    public ModelAndView users(@PathVariable int pageId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userForm", new UserCreateForm());
        List<User> lots = userService.getUserByPage((pageId - 1) * ApplicationConstants.TOTAL_SIZE, ApplicationConstants.TOTAL_SIZE);
        modelAndView.addObject("users", lots);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        if (email != null) {
            modelAndView.addObject("loggedInUserEmail", email);
            LOGGER.error(messageSource.getMessage("email.not.found", null, Locale.ENGLISH));
        }
        modelAndView.setViewName("admin/users");
        modelAndView.addObject("pagUsers", pagination(pageId, userService.getUserSize()));
        LOGGER.info(messageSource.getMessage("user.admin.load.page", null, Locale.ENGLISH));
        return modelAndView;
    }

    /**
     * This method for update user
     *
     * @param userCreateForm
     * @return
     */
    @RequestMapping(value = "update", method = {RequestMethod.POST})
    public String updateUser(@ModelAttribute("userForm") UserCreateForm userCreateForm, BindingResult bindingResult) {
        userValidator.validate(userCreateForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "redirect:/admin/user/1";
        }
        userService.update(userCreateForm);
        return "redirect:/admin/user/1";
    }

    /**
     * This method for disable user
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "delete/{userId}", method = {RequestMethod.POST})
    public String disableUser(@PathVariable("userId") Long userId) {
        userService.disableUser(userId);
        return "redirect:/admin/user/1";
    }
}
