package com.hc.authentication.service.imp;

import com.hc.authentication.entity.Role;
import com.hc.authentication.entity.User;
import com.hc.authentication.exception.UserAlreadyExistsException;
import com.hc.authentication.repository.RoleRepository;
import com.hc.authentication.repository.UserRepository;
import com.hc.authentication.service.IUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public User registerUser(User user, String roleName) {
        if (userRepository.existsByUsername(user.getUsername())){
            throw new UserAlreadyExistsException(user.getUsername() + " already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        Role userRole = findRoleByName(roleName);
        user.setRoles(Collections.singletonList(userRole));

        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    private Role findRoleByName(String roleName) {
        return switch (roleName.toUpperCase()) {
            case "ADMIN" -> roleRepository.findByName("ADMIN")
                    .orElseThrow(() -> new RuntimeException("Admin role not found"));
            case "MAKER" -> roleRepository.findByName("MAKER")
                    .orElseThrow(() -> new RuntimeException("Maker role not found"));
            default -> roleRepository.findByName("CHECKER")
                    .orElseThrow(() -> new RuntimeException("Checker role not found"));
        };
    }
}
