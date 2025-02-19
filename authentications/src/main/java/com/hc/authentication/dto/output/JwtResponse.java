package com.hc.authentication.dto.output;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data

@NoArgsConstructor
public class JwtResponse {
    private Long id;
    private String username;
    private String token;
    private String type = "Bearer";
    private List<String> roles;
    private Set<String> permissions;

    public JwtResponse(Long id, String username, String token, List<String> roles, Set<String> permissions) {
        this.id = id;
        this.username = username;
        this.token = token;
        this.roles = roles;
        this.permissions = permissions;
    }
}
