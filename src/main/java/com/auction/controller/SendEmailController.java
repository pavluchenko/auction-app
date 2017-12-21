package com.auction.controller;

import com.auction.model.form.EmailCreateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Helga on 28.11.2017.
 */
@Controller
@RequestMapping(value = "mail")
public class SendEmailController {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${email.from}")
    private String emailFrom;

    /**
     * This method will get page with email form
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView sendEmail() {
        return new ModelAndView("emailForm", "emailForm", new EmailCreateForm());
    }

    /**
     * This method for send email to recipient with subject and messages
     *
     * @param emailForm
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView sendEmail(@ModelAttribute("emailForm") EmailCreateForm emailForm) {
        ModelAndView modelAndView = new ModelAndView();
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(emailForm.getRecipient());
        email.setFrom(emailFrom);
        email.setSubject(emailForm.getSubject());
        email.setText(emailForm.getMessage());

        mailSender.send(email);
        return modelAndView;
    }
}
