package org.isamm.Agile.dao;

import org.isamm.Agile.entities.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface EquipeDao extends JpaRepository<Equipe, Long>{

}
