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

import tn.isetso.entities.MyUserPrincipal;
import tn.isetso.entities.Role;
import tn.isetso.entities.Users;
@Service
public class UserDetailsServiceImpl implements UserDetailsService  {

	@Autowired
	private AccountService accountService;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users u = accountService.findUserByUsername(username) ;
			
		if (u==null) throw new UsernameNotFoundException("user not found ici user details service " + username);
		
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		
		u.getRoles().forEach(r->{
			System.out.println(r.getRole());
			authorities.add(new SimpleGrantedAuthority(r.getRole()));
			
		});
		
		
		
		MyUserPrincipal muser = new MyUserPrincipal();
		muser.setUser(u);
		muser.setAuthorities(authorities);
		
		System.out.println(muser.getAuthorities().toString());
		return muser;
		//return  (UserDetails) new Users(u.getUsername(),u.getPassword(),authorities); 
	}

	
}
