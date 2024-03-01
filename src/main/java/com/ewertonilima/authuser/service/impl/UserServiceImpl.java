package com.ewertonilima.authuser.service.impl;

import com.ewertonilima.authuser.clients.CourseClient;
import com.ewertonilima.authuser.enums.ActionType;
import com.ewertonilima.authuser.models.UserModel;
import com.ewertonilima.authuser.publishers.UserEventPublisher;
import com.ewertonilima.authuser.repositories.UserRepository;
import com.ewertonilima.authuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    final CourseClient courseClient;
    final UserEventPublisher userEventPublisher;

    public UserServiceImpl(CourseClient courseClient, UserEventPublisher userEventPublisher) {
        this.courseClient = courseClient;
        this.userEventPublisher = userEventPublisher;
    }

    @Override
    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserModel> findById(UUID userId) {
        return userRepository.findById(userId);
    }

    @Transactional
    @Override
    public void delete(UserModel userModel) {
        userRepository.delete(userModel);
    }

    @Override
    public void save(UserModel userModel) {
        userRepository.save(userModel);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Page<UserModel> findAll(Specification<UserModel> spec, Pageable pageable) {
        return userRepository.findAll(spec, pageable);
    }

    @Transactional
    @Override
    public UserModel saveUser(UserModel userModel) {
        userRepository.save(userModel);

        userEventPublisher.publisherUserEvent(userModel.convertToUserEventDto(), ActionType.CREATE);
        return userModel;
    }
}
