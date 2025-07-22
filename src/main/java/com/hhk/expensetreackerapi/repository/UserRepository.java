package com.hhk.expensetreackerapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hhk.expensetreackerapi.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Boolean existsByEmail(String email);

	 Optional<User> findByEmail(String email);

}
