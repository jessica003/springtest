package com.example.complyindia.repository.Auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.complyindia.enumeration.ERole;
import com.example.complyindia.model.Auth.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
	
	
}
