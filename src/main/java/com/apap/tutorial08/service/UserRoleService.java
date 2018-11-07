package com.apap.tutorial08.service;

import com.apap.tutorial08.model.UserRoleModel;

public interface UserRoleService {
	UserRoleModel addUser (UserRoleModel user);
	UserRoleModel getUser (String username);
	public String encrypt (String password);
	public void updatePassUser (UserRoleModel user, String pass_baru);
	//public boolean cekPass (UserRoleModel user, String pass_lama);

}
