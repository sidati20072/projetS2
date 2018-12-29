package tn.isetso.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.isetso.entities.Users;
import tn.isetso.service.AccountService;

@RestController
public class UserController {

	@Autowired
	private AccountService accountService;
	
	@PostMapping("/users")
	public Users signUp(@RequestBody RegistrationForm data) {
		String username=data.getUsername();
		Users user = accountService.findUserByUsername(username);
		if (user!=null) throw new RuntimeException("This user already exist");
		String password=data.getPassword(); 
		String repassword=data.getRepassword(); 
		if(!password.equals(repassword)) throw new RuntimeException("you must confirm your password");
		Users u = new Users();
		u.setUsername(username);u.setPassword(password);
		accountService.saveUser(u);
		accountService.addRoleToUser(username, "USER");
		
		return u;
	}
}
