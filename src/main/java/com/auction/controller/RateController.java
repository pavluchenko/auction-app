package com.auction.controller;

import com.auction.builder.EmailForm;
import com.auction.model.entity.Lot;
import com.auction.model.entity.Rate;
import com.auction.model.entity.Subscription;
import com.auction.model.entity.User;
import com.auction.model.form.RateCreateForm;
import com.auction.model.form.SubscriptionCreateForm;
import com.auction.model.form.soket.RateMessage;
import com.auction.service.LotService;
import com.auction.service.RateService;
import com.auction.service.SendEmailService;
import com.auction.service.SubscriptionService;
import com.auction.service.UserService;
import com.auction.validator.RateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Helga on 13.11.2017.
 */
@Controller
@RequestMapping("/rate")
public class RateController extends UtilsResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(RateController.class);

    @Value("${message.subject}")
    private String messageSubject;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private RateService rateService;

    @Autowired
    private LotService lotService;

    @Autowired
    private UserService userService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private SendEmailService sendEmailService;

    @Autowired
    private RateValidator validator;

    /**
     * This method add rate to lot, create subscription and send email for lot creator and for user
     *
     * @param message
     * @return
     */

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public ResponseEntity addRateForLot(RateMessage message) {
        LOGGER.info("message.getRateMessage() = " + message.getPrice());

        //в сервис перенести
        User user = userService.getUserByEmail(message.getUserEmail());
        Lot lot = lotService.getLotById(Long.parseLong(message.getLotId()));
        Rate rate = rateService.getLastRate(lot);
        Double lastRate = null;
        Locale dLocale = new Locale.Builder().setLanguage("ru").setScript("Cyrl").build();
        Object[] objects = new Object[]{message.getUserEmail(), message.getPrice(), lot.getName()};
        message.setRateMessage(messageSource.getMessage("user.add.rate.for.lot", objects, dLocale));

        if (rate != null) {
            lastRate = rate.getPrice();
        }

        if (message.getPrice().equals("")) {
            LOGGER.error(messageSource.getMessage("user.add.rate.for.lot", objects, dLocale));
            return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
        }

        RateCreateForm rateCreateForm = new RateCreateForm();
        rateCreateForm.setUserId(user.getId());
        rateCreateForm.setLotId(lot.getId());
        rateCreateForm.setPrice(Double.parseDouble(message.getPrice()));
        rateCreateForm.setDate(new Date());

        boolean isValid = validator.validate(message.getUserEmail(), user, lot, rateCreateForm, lastRate);


        if (isValid) {
            // disable lot
            if (lot.getBayoutPrice() != null && rateCreateForm.getPrice() >= lot.getBayoutPrice()) {
                lotService.disableLot(Long.parseLong(message.getLotId()));
            }

            rateService.create(rateCreateForm);

            createSubscription(rateCreateForm, user.getId(), lot.getId());

            // mail for lot creator
            EmailForm emailCreatorEmail = EmailForm.newBuilder().setRecipient(message.getUserEmail()).setSubject(messageSubject).
                    setMessage(messageSource.getMessage("user.add.rate.for.lot", objects, dLocale)).build();
            sendEmailService.sendEmail(emailCreatorEmail);

            // mail for user who add rate
            EmailForm lotUserEmail = EmailForm.newBuilder().setRecipient(lot.getUser().getEmail()).setSubject(messageSubject).
                    setMessage(messageSource.getMessage("user.rate.for.lot", objects, dLocale)).build();
            sendEmailService.sendEmail(lotUserEmail);

            return new ResponseEntity(message, HttpStatus.OK);
        }
        return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
    }

    /**
     * Get rates for lot
     *
     * @param lotId
     * @param request
     * @return
     */
    @RequestMapping(value = {"/all/{lotId}"}, method = RequestMethod.GET)
    public ModelAndView getRatesForLot(@PathVariable("lotId") String lotId, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        setUserParams(modelAndView);
        modelAndView.addObject("rates", rateService.getRateByLot(Long.parseLong(lotId)));
        modelAndView.addObject("lot", lotService.getLotById(Long.parseLong(lotId)));
        modelAndView.setViewName("rates");
        setUserParams(modelAndView);
        return modelAndView;
    }

    /**
     * Сreate вubscription by user and lot
     *
     * @param rateCreateForm
     * @param userId
     * @param lotId
     */

    public void createSubscription(RateCreateForm rateCreateForm, Long userId, Long lotId) {
        SubscriptionCreateForm subscriptionCreateForm = new SubscriptionCreateForm();
        subscriptionCreateForm.setDate(rateCreateForm.getDate());
        subscriptionCreateForm.setUserId(userId);
        subscriptionCreateForm.setCreatorId(lotService.getLotById(lotId).getUser().getId());
        List<Subscription> subscription = subscriptionService.getSubscriptionbyUser(subscriptionCreateForm.getUserId());
        if (subscription == null) {
            subscriptionService.create(subscriptionCreateForm);
        }
    }
}
