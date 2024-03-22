package com.ewertonilima.authuser.service.impl;

import com.ewertonilima.authuser.repositories.RoleRepository;
import com.ewertonilima.authuser.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
