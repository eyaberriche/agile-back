package org.isamm.Agile.Repository;

import org.isamm.Agile.model.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface EquipeDao extends JpaRepository<Equipe, Long>{

}
