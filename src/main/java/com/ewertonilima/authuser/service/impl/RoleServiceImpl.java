package com.ewertonilima.authuser.service.impl;

import com.ewertonilima.authuser.enums.RoleType;
import com.ewertonilima.authuser.models.RoleModel;
import com.ewertonilima.authuser.repositories.RoleRepository;
import com.ewertonilima.authuser.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<RoleModel> findByRoleName(RoleType roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
