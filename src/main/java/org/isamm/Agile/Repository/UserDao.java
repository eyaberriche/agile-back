package org.isamm.Agile.Repository;

import java.util.List;
import java.util.Optional;

import org.isamm.Agile.model.RoleName;
import org.isamm.Agile.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long>{
	 Optional<User> findByUsername(String username);
	 Boolean existsByUsername(String username);
     Boolean existsByMail(String mail);
	/*@Query( "select u from User u inner join u.Role r where r.name = ROLE_SM" )
	List<User> getListByRole(@Param("u") User user);*/
	//List<User> findAllByRolesContains(RoleName role);
	@Query("SELECT u "
			+ "FROM User u "
			+ "INNER JOIN u.roles role "
			+ "WHERE role.name = :name"
	)
	public List<User> findByrole(@Param("name") RoleName name);
	//List<User> getUserListByRoles(RoleName roleName);
}
