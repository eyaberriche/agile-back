package org.isamm.Agile.Repository;



import java.util.List;

import org.isamm.Agile.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrepriseDao extends JpaRepository<Entreprise,Integer>{
    Boolean existsByName(String  name);
    Boolean existsByEmail(String email);
}
