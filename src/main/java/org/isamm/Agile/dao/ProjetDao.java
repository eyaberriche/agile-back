package org.isamm.Agile.dao;



import java.util.List;

import org.isamm.Agile.entities.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

//@RepositoryRestResource
public interface ProjetDao extends JpaRepository<Projet, Long> {
	/*@RestResource
	public List<Projet> findByNomP(String nomP);*/
	
	

}
