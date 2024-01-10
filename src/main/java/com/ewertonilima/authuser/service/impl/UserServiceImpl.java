package com.ewertonilima.authuser.service.impl;

import com.ewertonilima.authuser.repositories.UserRepository;
import com.ewertonilima.authuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

}
