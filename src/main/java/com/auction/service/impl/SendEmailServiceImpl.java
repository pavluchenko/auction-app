package com.auction.service.impl;

import com.auction.builder.EmailForm;
import com.auction.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Created by Helga on 07.12.2017.
 */
@Service
public class SendEmailServiceImpl implements SendEmailService {

    @Autowired
    private JavaMailSender mailSender;

    /**
     * This method for send email form email form
     *
     * @param email
     */
    @Override
    public void sendEmail(EmailForm email) {
        SimpleMailMessage emails = new SimpleMailMessage();
        emails.setTo(email.getRecipient());
        emails.setSubject(email.getRecipient());
        emails.setText(email.getMessage());
        mailSender.send(emails);
    }
}
