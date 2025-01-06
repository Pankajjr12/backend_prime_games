package com.kumar.gamesstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kumar.gamesstore.modals.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
