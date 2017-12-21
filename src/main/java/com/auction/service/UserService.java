package com.auction.service;

import com.auction.model.entity.Role;
import com.auction.model.entity.User;
import com.auction.model.form.UserCreateForm;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Helga on 23.10.17.
 */
@Service
public interface UserService extends AuctionGenericService<User, UserCreateForm> {

    /**
     * This method for get user by name
     *
     * @param email
     * @return
     */
    User getUserByEmail(String email);

    /**
     * This method for get user by id
     *
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * This method for get check user email
     *
     * @param loginUser
     * @return
     */
    User checkUser(UserCreateForm loginUser);

    /**
     * This method for update user
     *
     * @param loginUser
     */
    void update(UserCreateForm loginUser);

    /**
     * This method for disable user by id
     *
     * @param id
     */
    void disableUser(Long id);

    /**
     * This method for get user by page
     *
     * @param pageid
     * @param total
     * @return
     */
    List<User> getUserByPage(int pageid, int total);

    /**
     * This method for count of all users
     *
     * @return
     */
    int getUserSize();

    /**
     * This method for get list of roles by user id
     *
     * @param id
     * @return
     */
    List<Role> getRoleByUser(Long id);
}
