package org.isamm.Agile.Repository;



import java.util.List;

import org.isamm.Agile.model.Project;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectDao extends JpaRepository<Project,Long> {
    Boolean existsByName(String  name);
    @Query("SELECT p "
            + "FROM Project p "
            + "INNER JOIN p.entreprise e "
            + "WHERE e.id = :id"
    )
    public List<Project> findByEntreprise(@Param("id") Long id);


	@Query(   "SELECT p "
			+ "FROM Project p "
			+ "INNER JOIN p.users use "
			+ "WHERE use.id = :id"
		  )
	public List<Project> findByUser(@Param("id") Long id);


}
