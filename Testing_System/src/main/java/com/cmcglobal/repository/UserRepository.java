package com.cmcglobal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cmcglobal.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
}
