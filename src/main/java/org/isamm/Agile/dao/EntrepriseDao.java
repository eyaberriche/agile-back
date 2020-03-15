package org.isamm.Agile.dao;



import java.util.List;

import org.isamm.Agile.entities.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
//@RepositoryRestResource
public interface EntrepriseDao extends JpaRepository<Entreprise,Long>{
	/*@RestResource
	public List<Entreprise> findByNomE(String nomE);*/
}
