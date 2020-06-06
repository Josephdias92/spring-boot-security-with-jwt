package com.example.demo.dao;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
