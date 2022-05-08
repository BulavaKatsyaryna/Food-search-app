package com.example.foodsearchapp.controller;

import com.example.foodsearchapp.config.token.Utility;
import com.example.foodsearchapp.model.EStatus;
import com.example.foodsearchapp.model.Status;
import com.example.foodsearchapp.model.User;
import com.example.foodsearchapp.pojo.JsonWebTokenResponse;
import com.example.foodsearchapp.pojo.LoginRequest;
import com.example.foodsearchapp.pojo.MessResponse;
import com.example.foodsearchapp.pojo.RegistrationRequest;
import com.example.foodsearchapp.repo.StatusRepo;
import com.example.foodsearchapp.repo.UserRepo;
import com.example.foodsearchapp.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepo userRepo;

    @Autowired
    StatusRepo statusRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    Utility utility;

    @PostMapping("/signin")
    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest) {

        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPass()));

        SecurityContextHolder.getContext().setAuthentication(auth);
        String jsonWebToken = utility.generateJsonWebToken(auth);

        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        List<String> statuses = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JsonWebTokenResponse(jsonWebToken,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                statuses));
    }

    @PostMapping("/registry")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest registrationRequest) {

        if (userRepo.existsByUsername(registrationRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessResponse("Error: Username exist"));
        }

        if (userRepo.existsByEmail(registrationRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessResponse("Error: Email exist"));
        }

        User user = new User(registrationRequest.getUsername(),
                registrationRequest.getEmail(),
                passwordEncoder.encode(registrationRequest.getPass()));

        Set<String> requestStatus = registrationRequest.getStatuses();
        Set<Status> status = new HashSet<>();

        if (requestStatus == null) {
            Status userStatus = statusRepo
                    .findByName(EStatus.STATUS_USER)
                    .orElseThrow(() -> new RuntimeException("Error, USER status not found"));
            status.add(userStatus);
        } else {
            requestStatus.forEach(r -> {
                if ("admin".equals(r)) {
                    Status adminStatus = statusRepo
                            .findByName(EStatus.STATUS_RESTAURANT)
                            .orElseThrow(() -> new RuntimeException("Error, RESTAURANT status not found"));
                    status.add(adminStatus);
                } else {
                    Status userStatus = statusRepo
                            .findByName(EStatus.STATUS_USER)
                            .orElseThrow(() -> new RuntimeException("Error, USER status not found"));
                    status.add(userStatus);
                }
            });
        }
        user.setStatuses(status);
        userRepo.save(user);
        return ResponseEntity.ok(new MessResponse("User created!"));
    }
}
