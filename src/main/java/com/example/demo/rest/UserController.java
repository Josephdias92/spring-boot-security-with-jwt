package com.example.demo.rest;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.UserDTO;
import com.example.demo.services.JwtTokenUtil;
import com.example.demo.services.JwtUserDetailsService;
import com.example.demo.services.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequestMapping("api/users")
@RestController
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final JwtUserDetailsService userDetailsService;

    @PostMapping("/register")
    @SneakyThrows
    public ResponseEntity<?> register(@RequestBody UserDTO user) {
        UserDTO userDTO = userService.register(user);
        final String token = generateToken(userDTO);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @SneakyThrows
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserDTO user) {
        authenticate(user.getUsername(), user.getPassword());
        final String token = generateToken(user);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private String generateToken(UserDTO user) {
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    @SneakyThrows
    private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
    
}