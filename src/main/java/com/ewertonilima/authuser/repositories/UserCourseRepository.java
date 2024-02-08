package com.ewertonilima.authuser.repositories;

import com.ewertonilima.authuser.models.UserCourseModel;
import com.ewertonilima.authuser.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourseModel, UUID> {

    boolean existsByUserAndCourseId(UserModel userModel, UUID courseId);
}
