package tn.isetso.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import tn.isetso.entities.Role;

@RepositoryRestResource
public interface RoleRepository extends JpaRepository<Role, Long> {

	public Role findByRole(String role);

}
