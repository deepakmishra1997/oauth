package com.jdbl_12_oauth.oauth2.repository;

import com.jdbl_12_oauth.oauth2.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo  extends  CrudRepository<User,Long> {

    public Optional<User> findByUserName(String user);
}
