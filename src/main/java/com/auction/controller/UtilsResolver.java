package com.auction.controller;

import com.auction.model.entity.User;
import com.auction.service.CategoryService;
import com.auction.service.LotService;
import com.auction.service.UserService;
import com.auction.utils.ApplicationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Class with helper methods
 * <p>
 * Created by Helga on 07.12.2017.
 */

@Component
public class UtilsResolver {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private LotService lotService;

    @Autowired
    private UserService userService;

    public void setPageInfo(ModelAndView modelAndView) {
        // положить ams path
        modelAndView.addObject("categories", categoryService.getAll());
        modelAndView.setViewName("home");
    }

    public void setUserParams(ModelAndView modelAndView) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            String email = auth.getName(); //get logged in username
            if (email != null) {
                User user = userService.getUserByEmail(email);
                modelAndView.addObject("loggedInUserEmail", email);
                if (user.getId() != null) {
                    modelAndView.addObject("allLotsFromUser", lotService.getLotByUser(user.getId()));
                }
            }
        }
    }

    public List<Integer> pagination(int pageId, int tSize) {
        Double size = Double.valueOf(tSize);
        List<Integer> pages = new ArrayList<>();
        int temp = 0;

        int startpage = 1;
        int endpage = (int) Math.ceil(Double.valueOf(size / ApplicationConstants.TOTAL_SIZE));

        for (int i = startpage; i <= endpage; ++i) {
            pages.add(++temp);
        }
        return pages;
    }
}
