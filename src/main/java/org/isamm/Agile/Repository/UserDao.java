package org.isamm.Agile.Repository;

import java.util.List;

import org.isamm.Agile.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource
public interface UserDao extends JpaRepository<User, Long>{
	/*@Query("select * from Personne p where p.type_pers = AE ")
	List<Personne> findByNom(String nom);	*/
}
