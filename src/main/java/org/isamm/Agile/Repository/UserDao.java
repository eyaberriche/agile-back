package org.isamm.Agile.Repository;

import java.util.List;
import java.util.Optional;

import org.isamm.Agile.model.RoleName;
import org.isamm.Agile.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long>{
	 Optional<User> findByUsername(String username);
	 Boolean existsByUsername(String username);
     Boolean existsByEmail(String email);
	@Query("SELECT u "
			+ "FROM User u "
			+ "INNER JOIN u.roles role "
			+ "WHERE role.name = :name"
	)
	public List<User> findByrole(@Param("name") RoleName name);

	/*@RestResource(path = "byName")
	public List<User> findByLastnameContains(@Param("name") String lastname);
	@RestResource(path = "byrole")
	@Query("SELECT u "
			+ "FROM User u "
			+ "INNER JOIN u.roles role "
			+ "WHERE role.description = :description"
	)
	public List<User> findByrolec(@Param("description") String description);*/

}
