package com.sonu.authorizationsystem.repository;

import com.sonu.authorizationsystem.entity.ERole;
import com.sonu.authorizationsystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByRoleName(ERole roleName);
}
