package tn.isetso.service;

import tn.isetso.entities.Role;
import tn.isetso.entities.User;

public interface AccountService {

	public User saveUser(User u);
	public Role saveRole(Role r);
	public User findUserByUsername(String username);
	public void addRoleToUser(String username , String roole);
	
}
