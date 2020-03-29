package org.isamm.Agile.Repository;

import org.isamm.Agile.model.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//@RepositoryRestResource
public interface SprintDao extends JpaRepository<Sprint, Long> {

}
