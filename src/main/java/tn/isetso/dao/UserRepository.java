package tn.isetso.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import tn.isetso.entities.Users;

@RepositoryRestResource

public interface UserRepository  extends JpaRepository<Users, Long>{

	public Users findByUsername(String username);
}
