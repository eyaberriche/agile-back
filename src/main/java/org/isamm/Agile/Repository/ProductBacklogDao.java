package org.isamm.Agile.Repository;

import org.isamm.Agile.model.ProductBacklog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductBacklogDao extends JpaRepository<ProductBacklog, Long>{
    @Query("SELECT p "
            + "FROM ProductBacklog p "
            + "INNER JOIN p.project e "
            + "WHERE e.id = :id"
    )
    public ProductBacklog findByProject(@Param("id") Long id);

}
