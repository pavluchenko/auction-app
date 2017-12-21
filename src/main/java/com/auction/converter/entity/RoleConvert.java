package com.auction.converter.entity;

import com.auction.model.entity.Role;
import com.auction.model.form.BaseForm;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Helga on 12.12.2017.
 */
@Component
public class RoleConvert implements Converter<BaseForm, Role> {
    @Override
    public Role convert(BaseForm baseForm) {
        Role role = new Role();
        role.setId(baseForm.getId());
        return role;
    }
}
