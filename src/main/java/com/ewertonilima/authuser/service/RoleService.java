package com.ewertonilima.authuser.service;

import com.ewertonilima.authuser.enums.RoleType;
import com.ewertonilima.authuser.models.RoleModel;

import java.util.Optional;

public interface RoleService {
    Optional<RoleModel> findByRoleName(RoleType roleName);
}
