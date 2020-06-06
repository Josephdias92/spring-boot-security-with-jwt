package com.example.demo.services;

import java.util.ArrayList;

import com.example.demo.dao.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

	private final UserDao userDao;

    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.example.demo.entity.User user = this.userDao.findByUsername(username);
		if (null != user) {
			return new User(user.getUsername(), user.getPassword(),
					new ArrayList());
		}
		throw new UsernameNotFoundException(username + "doesnt exist");
	}
}
