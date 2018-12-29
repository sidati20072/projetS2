package tn.isetso.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tn.isetso.dao.RoleRepository;
import tn.isetso.dao.UserRepository;
import tn.isetso.entities.Role;
import tn.isetso.entities.Users;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	
	@Override
	public Users saveUser(Users u) {
		u.setPassword(bcryptPasswordEncoder.encode(u.getPassword()));
		

		return userRepository.save(u);
	}

	@Override
	public Role saveRole(Role r) {

		return roleRepository.save(r);
	}

	@Override
	public Users findUserByUsername(String username) {

		return userRepository.findByUsername(username);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		
		Users user=userRepository.findByUsername(username);
		Role role=roleRepository.findByRole(roleName);
		user.getRoles().add(role);
	}

}
