package org.isamm.Agile.Repository;

import org.isamm.Agile.model.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartementDao extends JpaRepository<Departement,Long>{

}
