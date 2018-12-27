package tn.isetso.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tn.isetso.entities.Role;
import tn.isetso.entities.User;
@Service
public class UserDetailsServiceImpl implements UserDetailsService  {

	@Autowired
	private AccountService accountService;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User u = accountService.findUserByUsername(username) ;
			
		if (u==null) throw new UsernameNotFoundException("user not found ici user details service " + username);
		
		Collection<Role> authorities = new ArrayList<>();
		u.getRoles().forEach(r->{
			authorities.add(r);
			
		});
		
		
		return (UserDetails) new User(u.getUsername(),u.getPassword(),authorities); 
	}

	
}
