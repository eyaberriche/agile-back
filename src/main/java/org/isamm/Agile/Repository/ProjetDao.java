package org.isamm.Agile.Repository;



import java.util.List;

import org.isamm.Agile.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

//@RepositoryRestResource
public interface ProjetDao extends JpaRepository<Project, Long> {
	
	
	

}
