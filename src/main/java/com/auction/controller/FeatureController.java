package com.auction.controller;

import com.auction.model.entity.Lot;
import com.auction.model.form.FeatureCreateForm;
import com.auction.service.FeatureService;
import com.auction.service.LotService;
import com.auction.validator.FeatureValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Created by Helga on 24.11.2017.
 */
@Controller
@RequestMapping(value = "/feature")
public class FeatureController extends UtilsResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(FeatureController.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private FeatureService featureService;

    @Autowired
    private LotService lotService;

    @Autowired
    private FeatureValidator featureValidator;

    /**
     * This method get page with adding feature
     *
     * @param lotid
     * @return
     */
    @RequestMapping(value = "/{lotid}", method = RequestMethod.GET)
    public ModelAndView addFeature(@PathVariable("lotid") String lotid) {
        ModelAndView modelAndView = new ModelAndView("/lots/feature", "featureForm", new FeatureCreateForm());
        setUserParams(modelAndView);
        modelAndView.addObject("lot", lotService.getLotFormById(Long.parseLong(lotid)));
        return modelAndView;
    }

    /**
     * This method add feature for lot by lot creator
     *
     * @param lotid
     * @param featureCreateForm
     * @param request
     * @param bindingResult
     * @param redirectAttrs
     * @return
     */
    @RequestMapping(value = "/{lotid}", method = RequestMethod.POST)
    public String addFeatureToLot(@PathVariable("lotid") String lotid,
                                  @ModelAttribute("featureForm") FeatureCreateForm featureCreateForm,
                                  HttpServletRequest request,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttrs) {
        ModelAndView modelAndView = new ModelAndView();
        Lot lot = lotService.getLotById(Long.parseLong(lotid));
        featureValidator.validate(featureCreateForm, bindingResult);
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("home");
            redirectAttrs.addAttribute("lotId", lot.getId()).addFlashAttribute("message", "Account created!");
            LOGGER.error(messageSource.getMessage("feature.error", null, Locale.ENGLISH));
            return "redirect:/lot/{lotId}";
        }
        featureCreateForm.setLotId(lot.getId());
        featureService.create(featureCreateForm);
        modelAndView.setViewName("home");
        redirectAttrs.addAttribute("lotId", lot.getId()).addFlashAttribute("message", "Account created!");
        return "redirect:/lot/{lotId}";
    }
}
