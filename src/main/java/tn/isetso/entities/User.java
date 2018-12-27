package tn.isetso.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id ;
	private String username; 
	private String password; 
	private String email;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<Role> roles = new ArrayList<>();

	public User(String username, String password,  Collection<Role> roles) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
	
	
	
}
