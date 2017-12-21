package com.auction.controller;

import com.auction.model.entity.Lot;
import com.auction.service.CategoryService;
import com.auction.service.LotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(value = "/")
public class HomeController extends UtilsResolver {
    public static final String ERROR_MESSAGE = "errorMessage";
    private static final Logger LOGGER = LoggerFactory.getLogger(UtilsResolver.class);

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private LotService lotService;

    @Autowired
    private MessageSource messageSource;

    /**
     * This method for get index page
     *
     * @param request
     * @return
     */

    @RequestMapping(method = {RequestMethod.GET})
    public ModelAndView index(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();
        List<Lot> lots = lotService.getLastLots(10);
        int size = 0;
        if (lots == null) {
            modelAndView.addObject(ERROR_MESSAGE, "error.lot.notfound");
            LOGGER.debug(messageSource.getMessage("error.lot.notfound", null, Locale.ENGLISH));
        }
        if (lots != null && !lots.isEmpty()) {
            size = lots.size();
        }
        modelAndView.addObject("categories", categoryService.getAll());
        modelAndView.addObject("allLots", lots);

        modelAndView.addObject("lotsCount", size);
        setUserParams(modelAndView);
        modelAndView.setViewName("index");
        LOGGER.info(messageSource.getMessage("message.home.page.category", null, Locale.ENGLISH));
        return modelAndView;
    }

    /**
     * This method for get admin page
     *
     * @return
     */
    @RequestMapping(value = "/admin", method = {RequestMethod.GET})
    public ModelAndView admin() {
        ModelAndView modelAndView = new ModelAndView();
        //setUserParams(modelAndView);
        modelAndView.setViewName("admin/index");
        return modelAndView;
    }

}
