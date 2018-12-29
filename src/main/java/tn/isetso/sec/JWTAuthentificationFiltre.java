package tn.isetso.sec;

import java.io.IOException;
import java.util.Date;

import javax.naming.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tn.isetso.entities.Users;

public class JWTAuthentificationFiltre extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	
	public JWTAuthentificationFiltre(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request , HttpServletResponse reponse)  {
		
		Users user = null;
		try {
			user = new ObjectMapper().readValue(request.getInputStream(),Users.class);
			
		}catch(Exception e) {
			
			throw new RuntimeException(e);
			
		}
		
		return authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						user.getUsername(),
						user.getPassword()
						));
	}
	
	
	protected void successfullAuthentication(HttpServletRequest request , HttpServletResponse reponse , FilterChain chain,Authentication authResult) throws IOException , ServletException{
	
		User springUser = (User) authResult.getPrincipal();
		String jwtToken=Jwts.builder()
				.setSubject(springUser.getName())
				.setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
				.claim("roles", springUser.getRoles())
				.compact();
		reponse.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX+jwtToken);
		
	}
	
	
	
}
