package org.isamm.Agile.dao;

import java.util.List;

import org.isamm.Agile.entities.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource
public interface PersonneDao extends JpaRepository<Personne, Long>{
	/*@Query("select * from Personne p where p.type_pers = AE ")
	List<Personne> findByNom(String nom);	*/
}
