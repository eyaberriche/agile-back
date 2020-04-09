package org.isamm.Agile.Repository;

import org.isamm.Agile.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;


@Repository
public interface TeamDao extends JpaRepository<Team, Long>{

}
