package com.user.service.repositiry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.service.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
