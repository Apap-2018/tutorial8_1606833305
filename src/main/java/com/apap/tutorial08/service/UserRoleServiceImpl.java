package com.apap.tutorial08.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.apap.tutorial08.model.UserRoleModel;
import com.apap.tutorial08.repository.UserRoleDb;

@Service
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	private UserRoleDb userDb;
	
	@Override
	public UserRoleModel addUser (UserRoleModel user) {
		String pass = encrypt(user.getPassword());
		user.setPassword(pass);
		return userDb.save(user);
	}

	@Override
	public String encrypt(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}

	@Override
	public void updatePassUser(UserRoleModel user, String pass_baru) {
		String pass = encrypt(pass_baru);
		user.setPassword(pass);
		userDb.save(user);
	}

	@Override
	public UserRoleModel getUser(String username) {
		return userDb.findByusername(username);
	}
	

}
