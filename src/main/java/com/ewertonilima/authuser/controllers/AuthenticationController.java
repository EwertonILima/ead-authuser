package com.ewertonilima.authuser.controllers;

import com.ewertonilima.authuser.configs.security.JwtProvider;
import com.ewertonilima.authuser.dtos.JwtDto;
import com.ewertonilima.authuser.dtos.LoginDto;
import com.ewertonilima.authuser.dtos.UserDto;
import com.ewertonilima.authuser.enums.RoleType;
import com.ewertonilima.authuser.enums.UserStatus;
import com.ewertonilima.authuser.enums.UserType;
import com.ewertonilima.authuser.models.RoleModel;
import com.ewertonilima.authuser.models.UserModel;
import com.ewertonilima.authuser.service.RoleService;
import com.ewertonilima.authuser.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    UserService userService;

    final RoleService roleService;
    final PasswordEncoder passwordEncoder;
    final JwtProvider jwtProvider;
    final AuthenticationManager authenticationManager;

    public AuthenticationController(RoleService roleService, PasswordEncoder passwordEncoder, JwtProvider jwtProvider, AuthenticationManager authenticationManager) {
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@RequestBody @Validated(UserDto.UserView.RegistrationPost.class)
                                               @JsonView(UserDto.UserView.RegistrationPost.class) UserDto userDto) {
        log.debug("POST registerUser userDto received {} ", userDto.toString());
        if (userService.existsByUsername(userDto.getUsername())) {
            log.warn("Username {} is Already Taken ", userDto.getUsername());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Username is Already Taken!");
        }
        if (userService.existsByEmail(userDto.getEmail())) {
            log.warn("Email {} is Already Taken ", userDto.getEmail());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Email is Already Taken!");
        }
        RoleModel roleModel = roleService.findByRoleName(RoleType.ROLE_STUDENT)
                .orElseThrow(() -> new RuntimeException("Error: Role Not Found"));
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setUserStatus(UserStatus.ACTIVE);
        userModel.setUserType(UserType.STUDENT);
        userModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.getRoles().add(roleModel);
        userService.saveUser(userModel);
        log.debug("POST registerUser userId saved {} ", userModel.getUserId());
        log.info("User saved successfully userId {} ", userModel.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> authenticateUser(@Valid @RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwt(authentication);
        return ResponseEntity.ok(new JwtDto(jwt));
    }

    @GetMapping("/")
    public String index() {
        log.trace("TRACE");
        log.debug("DEBUG");
        log.info("INFO");
        log.warn("WARN");
        log.error("ERROR");
        return "Logging Spring Boot";
    }

}
