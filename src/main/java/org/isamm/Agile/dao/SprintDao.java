package org.isamm.Agile.dao;

import org.isamm.Agile.entities.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//@RepositoryRestResource
public interface SprintDao extends JpaRepository<Sprint, Long> {

}
