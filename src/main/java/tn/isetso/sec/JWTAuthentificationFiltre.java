package tn.isetso.sec;

import java.io.IOException;

import javax.naming.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import tn.isetso.entities.User;

public class JWTAuthentificationFiltre extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	
	public JWTAuthentificationFiltre(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request , HttpServletResponse reponse)  {
		
		User user = null;
		try {
			user = new ObjectMapper().readValue(request.getInputStream(),User.class);
			
		}catch(Exception e) {
			
			throw new RuntimeException(e);
			
		}
		
		return authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						user.getUsername(),
						user.getPassword()
						));
	}
	
	
	//@Override
	protected void successfullAuthentication(HttpServletRequest request , HttpServletResponse reponse , FilterChain chain,Authentication authResult) throws IOException , ServletException{
	
		//User springUser = (User) authResult.getPrincipal();
	}
	
	
	
}
