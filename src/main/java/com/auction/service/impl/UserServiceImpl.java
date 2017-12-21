package com.auction.service.impl;

import com.auction.converter.entity.UserConverter;
import com.auction.model.entity.Lot;
import com.auction.model.entity.Role;
import com.auction.model.entity.User;
import com.auction.model.form.UserCreateForm;
import com.auction.repository.UserRepository;
import com.auction.repository.UserRepositoryCusom;
import com.auction.service.LotService;
import com.auction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Helga on 23.10.17.
 */
@Component
public class UserServiceImpl extends AuctionGenericServiceImpl<User, UserCreateForm, UserConverter> implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRepositoryCusom userRepositoryCusom;

    @Autowired
    private LotService lotService;


    public UserServiceImpl() {
    }

    /**
     * This method for get user by name
     *
     * @param email
     * @return
     */
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    /**
     * This method for get user by id
     *
     * @param id
     * @return
     */
    public User getUserById(Long id) {
        if (id != null) {
            return userRepository.findOne(id);
        }
        return null;
    }

    /**
     * This method for get check user email
     *
     * @param userCreateForm
     * @return
     */
    public User checkUser(UserCreateForm userCreateForm) {
        return userRepository.getUserByEmail(userCreateForm.getEmail());
    }

    /**
     * This method for update user
     *
     * @param loginUser
     */
    @Override
    public void update(UserCreateForm loginUser) {
        userRepository.update(loginUser.getEmail(), loginUser.getRating(), loginUser.getId());
    }

    /**
     * This method for disable user by id
     *
     * @param id
     */
    @Override
    public void disableUser(Long id) {
        userRepository.disableUser(id);
        List<Lot> lotList = lotService.getLotByUser(id);
        for (Lot lot : lotList) {
            lotService.disableLot(lot.getId());
        }
    }

    /**
     * This method for get user by page
     *
     * @param pageid
     * @param total
     * @return
     */
    @Override
    public List<User> getUserByPage(int pageid, int total) {
        return (List<User>) userRepositoryCusom.getByPage(new User(), pageid, total);
    }

    /**
     * This method for count of all users
     *
     * @return
     */
    @Override
    public int getUserSize() {
        return userRepository.getUserSize();
    }

    /**
     * This method for get list of roles by user id
     *
     * @param id
     * @return
     */
    @Override
    public List<Role> getRoleByUser(Long id) {
        return userRepository.getRoleByUser(id);
    }

}
