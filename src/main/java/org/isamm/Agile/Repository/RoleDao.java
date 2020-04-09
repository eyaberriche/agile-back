package org.isamm.Agile.Repository;

import java.util.Optional;

import org.isamm.Agile.model.Role;
import org.isamm.Agile.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
	 Optional<Role> findByName(RoleName name);
}
