package com.example.demo.services;

import com.example.demo.dao.UserDao;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    private final PasswordEncoder passwordEncoder;

	public UserDTO register(UserDTO user) {
        User u = User.builder()
            .username(user.getUsername())
            .password(passwordEncoder.encode(user.getPassword()))
            .build();
        User savedInfo = userDao.save(u);
        return UserDTO.builder().username(savedInfo.getUsername()).build();
	}


}
