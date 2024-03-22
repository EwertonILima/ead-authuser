package com.ewertonilima.authuser.repositories;

import com.ewertonilima.authuser.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleModel, UUID> {
}
