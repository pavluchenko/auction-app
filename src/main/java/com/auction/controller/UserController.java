package com.auction.controller;

import com.auction.builder.EmailForm;
import com.auction.converter.form.SubscriptionFormConvert;
import com.auction.model.entity.Subscription;
import com.auction.model.entity.User;
import com.auction.model.form.SubscriptionCreateForm;
import com.auction.model.form.UserCreateForm;
import com.auction.service.SecurityService;
import com.auction.service.SendEmailService;
import com.auction.service.SubscriptionService;
import com.auction.service.UserService;
import com.auction.validator.UserValidator;
import org.apache.commons.collections4.CollectionUtils;
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
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.auction.utils.ApplicationConstants.LOGGED_IN_USER_EMAIL;
import static com.auction.utils.ApplicationConstants.LOGGED_IN_USER_ID;

@Controller
public class UserController extends UtilsResolver {
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String SUCCESS_MESSAGE = "successMessage";
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private MessageSource messageSource;

    @Value("${message.subject}")
    private String messageSubject;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private SubscriptionFormConvert subscriptionFormConvert;

    @Autowired
    private SendEmailService sendEmailService;

    /**
     * This method will get registration page
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("userForm", new UserCreateForm());

        modelAndView.setViewName("registration");
        return modelAndView;
    }

    /**
     * This method will provide the medium to add a new user.
     *
     * @param userForm
     * @param bindingResult
     * @param request
     * @return
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registration(@ModelAttribute("userForm") UserCreateForm userForm,
                                     BindingResult bindingResult,
                                     HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        HttpSession httpSession = request.getSession();

        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
            LOGGER.debug(messageSource.getMessage("error.registration", null, Locale.ENGLISH));
            return modelAndView;
        }
        User user = userService.create(userForm);

        securityService.autologin(userForm.getEmail(), userForm.getPassword(), null);
        setPageInfo(modelAndView);
        setUserParams(modelAndView);

        httpSession.setAttribute(LOGGED_IN_USER_EMAIL, user.getEmail());
        httpSession.setAttribute(LOGGED_IN_USER_ID, user.getId());

        EmailForm lotUserEmail = EmailForm.newBuilder().setRecipient(user.getEmail()).setSubject(messageSubject).
                setMessage(messageSource.getMessage("user.register", null, Locale.ENGLISH)).build();
        sendEmailService.sendEmail(lotUserEmail);

        return modelAndView;
    }

    /**
     * This method will get login page
     *
     * @param model
     * @param error
     * @param logout
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(Model model, String error, String logout) {
        ModelAndView modelAndView = new ModelAndView();
        if (error != null) {
            model.addAttribute(ERROR_MESSAGE, "message.user.login");
            LOGGER.debug(messageSource.getMessage("message.user.login", null, Locale.ENGLISH));
        }

        if (logout != null) {
            model.addAttribute(SUCCESS_MESSAGE, "error.user.login");
            LOGGER.debug(messageSource.getMessage("error.registration", null, Locale.ENGLISH));
        }

        modelAndView.setViewName("login");
        return modelAndView;
    }

    /**
     * This method will get home page
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        setPageInfo(modelAndView);
        setUserParams(modelAndView);
        LOGGER.info(messageSource.getMessage("message.home.page.category", null, Locale.ENGLISH));
        return modelAndView;
    }

    /**
     * This method for subscribe first user by second user
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/subscribe/{userId}", method = RequestMethod.POST)
    public String subscribeToUser(@PathVariable Long userId) {
        SubscriptionCreateForm subscriptionCreateForm = new SubscriptionCreateForm();
        subscriptionCreateForm.setDate(new Date());
        subscriptionCreateForm.setUserId(userService.getUserByEmail(getUserEmail()).getId());
        subscriptionCreateForm.setCreatorId(userId);

        List<Subscription> subscription = subscriptionService.getSubscriptionbyUser(userId);
        if (CollectionUtils.isEmpty(subscription)) {
            subscriptionService.create(subscriptionCreateForm);
        }
        return "redirect:/home";
    }

    /**
     * This method for unsubscribe  user
     *
     * @return
     */
    @RequestMapping(value = "/unsubscribe", method = RequestMethod.POST)
    public String unSubscribeToUser() {
        List<Subscription> subscription = subscriptionService.getSubscriptionbyUser(userService.getUserByEmail(getUserEmail()).getId());
        for (Subscription sub : subscription) {
            // dlelete by id
            subscriptionService.delete(subscriptionFormConvert.convert(sub));
        }
        return "redirect:/home";
    }

    /**
     * This method for get current user email
     *
     * @return
     */
    private String getUserEmail() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = "";
        if (auth != null) {
            email = auth.getName();
        }
        return email;
    }
}
