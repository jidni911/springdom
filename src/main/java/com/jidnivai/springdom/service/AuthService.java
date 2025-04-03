package com.jidnivai.springdom.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jidnivai.springdom.dto.JwtResponse;
import com.jidnivai.springdom.dto.LoginRequest;
import com.jidnivai.springdom.dto.UserSignupDto;
import com.jidnivai.springdom.entity.Role;
import com.jidnivai.springdom.entity.User;
import com.jidnivai.springdom.payload.MessageResponse;
import com.jidnivai.springdom.repository.RoleRepository;
import com.jidnivai.springdom.repository.UserRepository;
import com.jidnivai.springdom.security.jwt.JwtUtils;
import com.jidnivai.springdom.security.services.UserDetailsImpl;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public ResponseEntity<?> login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        User user = userDetails.getUser();

        ResponseCookie jwtCookie = jwtUtil.generateJwtCookie(userDetails);

        String token = jwtUtil.generateJwtToken(userDetails);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new JwtResponse(user, token));
    }

    public ResponseEntity<?> registerUser(
            UserSignupDto signUpRequest,
            MultipartFile profilePicture
            ) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        if (!signUpRequest.getPassword().equals(signUpRequest.getRetypePassword())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Passwords do not match!"));
        }

        User user = new User();

        user.setFullName(signUpRequest.getFullName());
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        // user.setGender(Gender.valueOf(signUpRequest.getGender().toUpperCase()));
        // user.setDob(signUpRequest.getDob());
        // user.setPhoneNumber(signUpRequest.getPhoneNumber());
        // user.setAddress(signUpRequest.getAddress());
        // TODO - Handle profile picture upload separately

        // Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setName("ROLE_USER");
        roles.add(roleRepository.save(role));

        user = userRepository.save(user);
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtil.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }
}
