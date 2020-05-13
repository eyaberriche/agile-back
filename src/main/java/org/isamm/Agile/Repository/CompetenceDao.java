package org.isamm.Agile.Repository;

import org.isamm.Agile.model.Competence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetenceDao extends JpaRepository<Competence, Long>{
    Boolean existsByName(String  name);
}
