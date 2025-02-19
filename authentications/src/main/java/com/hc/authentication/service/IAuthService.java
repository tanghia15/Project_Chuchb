package com.hc.authentication.service;

import com.hc.authentication.dto.input.LoginRequest;
import com.hc.authentication.dto.output.JwtResponse;

public interface IAuthService {
    JwtResponse authenticateUser(LoginRequest request);
}