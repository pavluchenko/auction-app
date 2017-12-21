package com.auction.service;

import com.auction.model.entity.Role;
import com.auction.model.form.BaseForm;
import org.springframework.stereotype.Service;

/**
 * Created by Helga on 12.12.2017.
 */
@Service
public interface RoleService extends AuctionGenericService<Role, BaseForm> {
    /**
     * This method for get USER_ROLE
     *
     * @return
     */
    Role getUserRole();

    /**
     * This method for get ADMIN
     *
     * @return
     */
    Role getAdminRole();
}
