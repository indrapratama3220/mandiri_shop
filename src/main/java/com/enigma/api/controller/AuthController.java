package com.enigma.api.controller;

import com.enigma.api.entity.Customer;
import com.enigma.api.entity.ERole;
import com.enigma.api.entity.Role;
import com.enigma.api.repository.CustomerRepository;
import com.enigma.api.repository.RoleRepository;
import com.enigma.api.security.jwt.JwtUtils;
import com.enigma.api.security.payload.request.LoginRequest;
import com.enigma.api.security.payload.request.SignupRequest;
import com.enigma.api.security.payload.response.JwtResponse;
import com.enigma.api.security.payload.response.MessageResponse;
import com.enigma.api.service.CustomerDetailsImpl;
import com.enigma.api.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getUserPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        CustomerDetailsImpl customerDetails = (CustomerDetailsImpl) authentication.getPrincipal();
        List<String> roles = customerDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt, customerDetails.getId(), customerDetails.getUsername(),
                customerDetails.getEmail(), customerDetails.getFirstName(), customerDetails.getLastName(),
                customerDetails.getAddress(), customerDetails.getStatus(), customerDetails.getDateOfBirth(), roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        if (customerRepository.existsByUserName(signupRequest.getUserName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken"));
        }
        if (customerRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use"));
        }

        Customer customer = new Customer(signupRequest.getFirstName(), signupRequest.getLastName(), signupRequest.getDateOfBirth(), signupRequest.getAddress(), signupRequest.getStatus(), signupRequest.getUserName(), signupRequest.getEmail(), passwordEncoder.encode(signupRequest.getUserPassword()));
        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(userRole);
                }
            });
        }
        customer.setRoles(roles);
        customerRepository.save(customer);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/role")
    public Role createRole(@RequestBody Role role){
        return roleService.saveRole(role);
    }

}
