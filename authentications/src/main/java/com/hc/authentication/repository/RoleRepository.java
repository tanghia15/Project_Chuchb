package com.hc.authentication.repository;

import com.hc.authentication.entity.Permission;
import com.hc.authentication.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String role);
    boolean existsByName(String role);

/*    @Query(value = "SELECT p.* " +
            "FROM permission p " +
            "JOIN cm_role_permission crp ON p.id = crp.permission_id " +
            "WHERE crp.role_id = :roleId", nativeQuery = true)
    List<Permission> findPermissionsByRoleId(@Param("roleId") Long roleId);*/
}
