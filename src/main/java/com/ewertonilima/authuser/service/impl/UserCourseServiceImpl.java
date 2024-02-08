package com.ewertonilima.authuser.service.impl;

import com.ewertonilima.authuser.models.UserCourseModel;
import com.ewertonilima.authuser.models.UserModel;
import com.ewertonilima.authuser.repositories.UserCourseRepository;
import com.ewertonilima.authuser.service.UserCourseService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserCourseServiceImpl implements UserCourseService {

    final
    UserCourseRepository userCourseRepository;

    public UserCourseServiceImpl(UserCourseRepository userCourseRepository) {
        this.userCourseRepository = userCourseRepository;
    }

    @Override
    public boolean existsByUserAndCourseId(UserModel userModel, UUID courseId) {
        return userCourseRepository.existsByUserAndCourseId(userModel, courseId);
    }

    @Override
    public UserCourseModel save(UserCourseModel userCourseModel) {
        return userCourseRepository.save(userCourseModel);
    }
}
