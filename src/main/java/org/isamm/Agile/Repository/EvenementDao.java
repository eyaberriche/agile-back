package org.isamm.Agile.Repository;

import org.isamm.Agile.model.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvenementDao extends JpaRepository<Evenement, Long>{
    /*@Query("SELECT e "
            + "FROM Evenement e "
            + "INNER JOIN e.sprints s "
            + "WHERE s.id = :id"
    )
    public List<Evenement> findBySprint(@Param("id") Long id);*/
}
