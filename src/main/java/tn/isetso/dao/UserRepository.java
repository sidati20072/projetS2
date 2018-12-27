package tn.isetso.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import tn.isetso.entities.User;

@RepositoryRestResource

public interface UserRepository  extends JpaRepository<User, Long>{

	public User findByUsername(String username);
}
