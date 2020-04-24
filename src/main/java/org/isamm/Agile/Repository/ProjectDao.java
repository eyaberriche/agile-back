package org.isamm.Agile.Repository;



import java.util.List;

import org.isamm.Agile.model.Project;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectDao extends JpaRepository<Project,Long> {
	
	/*@Query(   "SELECT p "
			+ "FROM Project p "
			+ "INNER JOIN p.departement dep "
			+ "WHERE dep.id = :id"
		  )
	public List<Project> findByDepartement(@Param("id") Integer id);

	@Query(   "SELECT p "
			+ "FROM Project p "
			+ "INNER JOIN p.user use "
			+ "WHERE use.id = :id"
		  )
	public List<Project> findByUser(@Param("id") Integer id);
	@Query(   "SELECT p "
			+ "FROM Project p "
			+ "INNER JOIN p.entreprise ent "
			+ "WHERE ent.id = :id"
		  )
	public List<Project> findByEntreprise(@Param("id") Integer id);*/


}
