package com.apap.tutorial08.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tutorial08.model.UserRoleModel;

@Repository
public interface UserRoleDb extends JpaRepository<UserRoleModel, Long>{
	UserRoleModel findByusername (String username);	
}
