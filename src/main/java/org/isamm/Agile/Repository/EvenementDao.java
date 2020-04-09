package org.isamm.Agile.Repository;

import org.isamm.Agile.model.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvenementDao extends JpaRepository<Evenement, Long>{

}
