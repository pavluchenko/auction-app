package com.auction.service.impl;

import com.auction.converter.entity.RoleConvert;
import com.auction.model.entity.Role;
import com.auction.model.form.BaseForm;
import com.auction.repository.RoleRepository;
import com.auction.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Helga on 12.12.2017.
 */
@Service
public class RoleServiceImpl extends AuctionGenericServiceImpl<Role, BaseForm, RoleConvert> implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    /**
     * This method for get USER_ROLE
     *
     * @return
     */
    @Override
    public Role getUserRole() {
        return roleRepository.getRoleUser("ROLE_ADMIN");
    }

    /**
     * This method for get ADMIN
     *
     * @return
     */
    @Override
    public Role getAdminRole() {
        return roleRepository.getRoleUser("ROLE_ADMIN");
    }
}
