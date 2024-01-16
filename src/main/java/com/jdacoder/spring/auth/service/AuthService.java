package com.jdacoder.spring.auth.service;

import com.jdacoder.spring.auth.payload.request.LoginRequest;
import com.jdacoder.spring.auth.payload.request.RegisterRequest;
import com.jdacoder.spring.auth.payload.response.JwtResponse;
import com.jdacoder.spring.auth.payload.response.MessageResponse;
import com.jdacoder.spring.auth.security.jwt.JwtUtils;
import com.jdacoder.spring.role.model.ERole;
import com.jdacoder.spring.role.model.Role;
import com.jdacoder.spring.role.repository.RoleRepository;
import com.jdacoder.spring.user.model.User;
import com.jdacoder.spring.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public ResponseEntity<JwtResponse> getJwtResponseResponseEntity(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.id(),
                userDetails.username(),
                userDetails.getEmail(), roles));
    }

    public ResponseEntity<MessageResponse> checkAndRegisterUserDetailsWithRole(RegisterRequest registerRequest) {
        ResponseEntity<MessageResponse> body = checkIfUserExistsByUserName(registerRequest);
        if (body != null) return body;

        User user = new User(registerRequest.getUsername(),
                registerRequest.getEmail(),
                encoder.encode(registerRequest.getPassword()));

        Set<String> strRoles = registerRequest.getRole();
        Set<Role> roles = new HashSet<>();

        verifyRoleAndAddRole(strRoles, roles);

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    private ResponseEntity<MessageResponse> checkIfUserExistsByUserName(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        return null;
    }

    private void verifyRoleAndAddRole(Set<String> strRoles, Set<Role> roles) {
        if (strRoles == null) {
            Role userRole = roleRepository.getRoleByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            checkIfRoleExistsElseAddRole(strRoles, roles);
        }
    }

    private void checkIfRoleExistsElseAddRole(Set<String> strRoles, Set<Role> roles) {
        strRoles.forEach(role -> {
            switch (role) {
                case "admin" -> {
                    Role adminRole = roleRepository.getRoleByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                }
                case "mod" -> {
                    Role modRole = roleRepository.getRoleByName(ERole.ROLE_MODERATOR)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(modRole);
                }
                default -> {
                    Role userRole = roleRepository.getRoleByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
                }
            }
        });
    }

}


