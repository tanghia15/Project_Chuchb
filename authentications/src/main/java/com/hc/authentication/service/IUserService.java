package com.hc.authentication.service;

import com.hc.authentication.entity.User;

import java.util.List;

public interface IUserService {
    User registerUser(User user, String roleName);
    List<User> getUsers();
}
