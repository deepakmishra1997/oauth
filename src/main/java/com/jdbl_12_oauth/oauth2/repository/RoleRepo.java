package com.jdbl_12_oauth.oauth2.repository;

import com.jdbl_12_oauth.oauth2.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepo extends CrudRepository<Role,Long> {
    public Optional<Role> findByRole(String role);
}
