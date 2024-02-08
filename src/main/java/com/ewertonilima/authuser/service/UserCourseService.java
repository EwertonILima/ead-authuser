package com.ewertonilima.authuser.service;

import com.ewertonilima.authuser.models.UserCourseModel;
import com.ewertonilima.authuser.models.UserModel;

import java.util.UUID;

public interface UserCourseService {
    boolean existsByUserAndCourseId(UserModel userModel, UUID courseId);

    UserCourseModel save(UserCourseModel userCourseModel);
}
