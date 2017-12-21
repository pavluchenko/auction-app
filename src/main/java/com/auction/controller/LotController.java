package com.auction.controller;

import com.auction.converter.form.RateFormConverter;
import com.auction.exceprion.AMSClientException;
import com.auction.exceprion.AMSServiceException;
import com.auction.model.entity.Category;
import com.auction.model.entity.Lot;
import com.auction.model.entity.Rate;
import com.auction.model.form.FeatureCreateForm;
import com.auction.model.form.LotCreateForm;
import com.auction.model.form.RateCreateForm;
import com.auction.service.CategoryService;
import com.auction.service.LotService;
import com.auction.service.RateService;
import com.auction.service.StorageService;
import com.auction.service.SubscriptionService;
import com.auction.service.UserService;
import com.auction.validator.LotValidator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Helga on 02.11.17.
 */
@Controller
public class LotController extends UtilsResolver {
    public static final String ERROR_MESSAGE = "errorMessage";
    private static final String MATCHER = "^\\D*$";
    private static final Logger LOGGER = LoggerFactory.getLogger(LotController.class);

    @Value("${ams.web}")
    private String amsPath;

    @Value("${bucket.name}")
    private String bucketName;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LotService lotService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private RateService rateService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private RateFormConverter rateFormConverter;

    @Autowired
    private LotValidator lotValidator;

    /**
     * This method will list all existing lots.
     *
     * @return ModelAndView
     */
    @RequestMapping(value = "/lots/{pageId}", method = RequestMethod.GET)
    public ModelAndView getAllLots(@PathVariable int pageId) {
        ModelAndView modelAndView = new ModelAndView();
        //остаток от деления на 5
        List<Lot> lots = lotService.getLotsByPage((pageId - 1) * 4, 4);
        modelAndView.addObject("lots", lots);

        modelAndView.setViewName("lots/lots");
        modelAndView.addObject("lotSize", pagination(pageId, lotService.getLotSize()));
        modelAndView.addObject("categories", getCategoryNames());
        setUserParams(modelAndView);
        LOGGER.info(messageSource.getMessage("message.lots.get.all", null, Locale.ENGLISH));
        return modelAndView;
    }

    /**
     * This method will get page for create new lot
     *
     * @param request
     * @return ModelAndView
     */
    @RequestMapping(value = {"/lot/create"}, method = RequestMethod.GET)
    public ModelAndView createLot(HttpServletRequest request, Model model) {
        ModelAndView modelAndView = new ModelAndView("lots/createLot", "lotForm", new LotCreateForm());
        setUserParams(modelAndView);
        modelAndView.addObject("categories", getCategoryNames());
        return modelAndView;
    }

    /**
     * This method will provide the medium to add a new lot.
     *
     * @param lotCreateForm
     * @param request
     * @return ModelAndView
     */
    @RequestMapping(value = "/lot/create", method = RequestMethod.POST)
    public String createLot(@ModelAttribute("lotForm") LotCreateForm lotCreateForm,
                            HttpServletRequest request,
                            BindingResult bindingResult) throws AMSServiceException, AMSClientException {
        String email = request.getUserPrincipal().getName();
        lotCreateForm.setUserId(userService.getUserByEmail(email).getId());

        lotValidator.validate(lotCreateForm, bindingResult);

        if (bindingResult.hasErrors()) {
            LOGGER.error(messageSource.getMessage("error.registration", null, Locale.ENGLISH));
            return "redirect:/lot/create";
        }

        Lot lot = lotService.create(lotCreateForm);
        String path = "";
        if (lotCreateForm.getFile() != null) {
            storageService.setLotId(lot.getId());
            path = storageService.upload(lotCreateForm.getFile());
        }
        lotCreateForm.setCategoryId(lotCreateForm.getCategoryId());
        // несколько

        lotService.setPhoto(path, lot.getId());
        return "redirect:/home";
    }

    /**
     * This method will get page for edit lot
     *
     * @param id
     * @param request
     * @return ModelAndView
     */
    @RequestMapping(value = "/lot/edit/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable("id") String id,
                               HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("lots/updateLot", "lotForm", new LotCreateForm());
        if (!id.matches(MATCHER)) {
            LotCreateForm lotCreateForm = lotService.getLotFormById(Long.valueOf(id));
            modelAndView.addObject("lot", lotCreateForm);
            if (lotCreateForm == null) {
                LOGGER.error("ID is error");
                modelAndView.addObject("error", "Id is error");
                modelAndView.setViewName("error/404");
                return modelAndView;
            }
        } else {
            LOGGER.error("ID is error");
            modelAndView.setViewName("error/404");
            return modelAndView;
        }
        modelAndView.addObject("categories", getCategoryNames());
        setUserParams(modelAndView);
        return modelAndView;
    }

    /**
     * his method will provide the medium to update an existing lot.
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/lot/edit/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") String id, @ModelAttribute("lotForm") LotCreateForm lotCreateForm, BindingResult bindingResult) {
        Lot lot = lotService.getLotById(Long.valueOf(id));
        Category category = categoryService.getCategoryByName(lotCreateForm.getCategoryName());
        lotCreateForm.setCategoryId(category.getId());
        lotCreateForm.setUserId(lot.getUser().getId());
        lotValidator.validate(lotCreateForm, bindingResult);
        if (bindingResult.hasErrors()) {
            LOGGER.error(messageSource.getMessage("error.registration", null, Locale.ENGLISH));
            return "redirect:/home";
        }
        lotService.update(lotCreateForm);
        return "redirect:/home";
    }

    /**
     * This method will disableUser lot
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/lot/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") String id) {
        LotCreateForm lot = lotService.getLotFormById(Long.parseLong(id));
        List<Rate> rates = rateService.getRateByLot(lot.getId());
        if (!CollectionUtils.isEmpty(rates)) {
            for (Rate rate : rates) {
                rateService.delete(rateFormConverter.convert(rate));
            }
        }
        // перенести цикл в сервис
        lotService.delete(lot);
        return "redirect:/home";
    }

    /**
     * This method get last 10 lots for home page
     *
     * @param count
     * @param request
     * @return
     */
    @RequestMapping(value = "/lot/last", method = RequestMethod.GET)
    public ModelAndView getLastLot(int count, HttpServletRequest request) {
        return setParams(lotService.getLastLots(count));
    }

    /**
     * This method get lots by name
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/lot/name/{name}", method = RequestMethod.GET)
    public ModelAndView getLotByName(@PathVariable("name") String name) {
        return setParams(lotService.getLotByName(name));
    }

    /**
     * This method get lots by user for home page
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/lot/user/{userId}", method = RequestMethod.GET)
    public ModelAndView getLotByUser(@PathVariable("userId") String id) {
        return setParams(lotService.getLotByUser(Long.valueOf(id)));
    }

    /**
     * Utils method for set lots on jsp
     *
     * @param lots
     * @return
     */
    private ModelAndView setParams(List<Lot> lots) {
        ModelAndView modelAndView = new ModelAndView();
        setDataForRequest(modelAndView, lots);
        setUserParams(modelAndView);
        return modelAndView;
    }

    /**
     * This method will get lot by id
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/lot/{id}", method = RequestMethod.GET)
    public ModelAndView getLotById(@PathVariable("id") String id, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        if (!id.matches(MATCHER)) {
            Lot lot = lotService.getLotById(Long.valueOf(id));
            if (lot == null) {
                modelAndView.addObject(ERROR_MESSAGE, "error.lot.notfound");
                LOGGER.debug(messageSource.getMessage("error.lot.notfound", null, Locale.ENGLISH));
                return modelAndView;
            }

            //fix firebug
            if (lot.getId() != null && StringUtils.isNotEmpty(lotService.getPhotoPath(lot.getId()))) {
                String pathImage = lotService.getPhotoPath(lot.getId());
                if (amsPath != null && bucketName != null && pathImage != null) {
                    String path = amsPath + bucketName + "/" + pathImage;
                    lot.setPhoto(path);
                }
                modelAndView.addObject("lot", lot);
                List<Rate> rates = rateService.getRateByLot(Long.valueOf(id));
                if (rates != null && !rates.isEmpty()) {
                    modelAndView.addObject("rate", rates.get(rates.size() - 1));
                }
            }

            setUserParams(modelAndView);
            model.addAttribute("rateForm", new RateCreateForm());
            model.addAttribute("featureForm", new FeatureCreateForm());
            modelAndView.setViewName("lots/singleLot");
            modelAndView.addObject("subscription", subscriptionService.getSubscriptionByCreatorAndUser(lot.getUser().getId(), userService.getUserByEmail(email).getId()));
        } else {
            LOGGER.error("ID is error");
            modelAndView.setViewName("error/404");
            return modelAndView;
        }
        return modelAndView;
    }


    /**
     * General method by other methods
     *
     * @param modelAndView
     * @param lots
     * @return
     */
    private ModelAndView setDataForRequest(ModelAndView modelAndView, List<Lot> lots) {
        if (lots == null) {
            modelAndView.addObject(ERROR_MESSAGE, "error.lot.notfound");
            LOGGER.debug(messageSource.getMessage("error.lot.notfound", null, Locale.ENGLISH));
            return modelAndView;
        }
        modelAndView.addObject("lots", lots);
        return modelAndView;
    }


    private List<String> getCategoryNames() {
        List<Category> categories = categoryService.getAll();
        List<String> categoryNames = new ArrayList<String>();

        for (Category category : categories) {
            categoryNames.add(category.getName());
        }
        return categoryNames;
    }
}
