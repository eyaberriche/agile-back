package org.isamm.Agile.Repository;

import java.util.Optional;

import org.isamm.Agile.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long>{
	 Optional<User> findByUsername(String username);
	 Boolean existsByUsername(String username);
     Boolean existsByMail(String mail);
}
