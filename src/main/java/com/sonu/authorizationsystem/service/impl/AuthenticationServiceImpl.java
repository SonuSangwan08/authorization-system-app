package com.sonu.authorizationsystem.service.impl;

import com.sonu.authorizationsystem.entity.ERole;
import com.sonu.authorizationsystem.entity.Role;
import com.sonu.authorizationsystem.entity.User;
import com.sonu.authorizationsystem.jwt.JwtUtils;
import com.sonu.authorizationsystem.model.request.LoginRequest;
import com.sonu.authorizationsystem.model.request.RegistrationRequest;
import com.sonu.authorizationsystem.model.response.LoginResponse;
import com.sonu.authorizationsystem.model.response.RegistrationResponse;
import com.sonu.authorizationsystem.repository.RoleRepository;
import com.sonu.authorizationsystem.repository.UserRepository;
import com.sonu.authorizationsystem.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public LoginResponse authenticateUser(LoginRequest loginRequest) {
        //Let Spring security authenticate the login request using Username/Password combo.
        // Returns the User ID card(Authentication object) with all information.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        log.info("Token generated: {}", jwt);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        log.info("Assigned roles for user: {} are: {}", loginRequest.getUsername(), roles);

        return new LoginResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }

    @Override
    public RegistrationResponse registerUser(RegistrationRequest registrationRequest) {
        // First validating if username/email are available or not
        if (userRepository.existsByUserName(registrationRequest.getUserName())) {
            return new RegistrationResponse("Error: Username is already taken!", true);
        }

        if (userRepository.existsByEmail(registrationRequest.getEmail())) {
            return new RegistrationResponse("Error: Email is already in use!", true);
        }
        log.info("Registering a new user");
        // Create new user's account
        User user = new User(registrationRequest.getUserName(),
                registrationRequest.getEmail(),
                encoder.encode(registrationRequest.getPassword()));

        Set<String> strRoles = registrationRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByRoleName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByRoleName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;

                    default:
                        Role userRole = roleRepository.findByRoleName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
        return new RegistrationResponse("User registered successfully!", false);
    }
}
