package com.auction.service;


import com.auction.builder.EmailForm;
import org.springframework.stereotype.Service;

/**
 * Created by Helga on 07.12.2017.
 */
@Service
public interface SendEmailService {
    /**
     * This method for send email form email form
     *
     * @param email
     */
    void sendEmail(EmailForm email);
}
