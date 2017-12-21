package com.auction.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Created by Helga on 23.10.17.
 */
@Service
public interface SecurityService {
    /**
     * This method is auto login
     *
     * @param email
     * @param password
     * @param userDetails
     */
    void autologin(String email, String password, UserDetails userDetails);
}
