package com.ewertonilima.authuser.service.impl;

import com.ewertonilima.authuser.repositories.UserCourseRepository;
import com.ewertonilima.authuser.service.UserCourseService;
import org.springframework.stereotype.Service;

@Service
public class UserCourseServiceImpl implements UserCourseService {

    final
    UserCourseRepository userCourseRepository;

    public UserCourseServiceImpl(UserCourseRepository userCourseRepository) {
        this.userCourseRepository = userCourseRepository;
    }
}
