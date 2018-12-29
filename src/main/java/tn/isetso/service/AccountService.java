package tn.isetso.service;

import tn.isetso.entities.Role;
import tn.isetso.entities.Users;

public interface AccountService {

	public Users saveUser(Users u);
	public Role saveRole(Role r);
	public Users findUserByUsername(String username);
	public void addRoleToUser(String username , String roole);
	
}
