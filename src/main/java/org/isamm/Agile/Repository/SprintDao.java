package org.isamm.Agile.Repository;

import org.isamm.Agile.model.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SprintDao extends JpaRepository<Sprint, Long> {
    @Query("SELECT s "
            + "FROM Sprint s "
            + "INNER JOIN s.backlog b "
            + "WHERE b.id = :id"
    )
    public List<Sprint> findByBacklog(@Param("id") Long id);
    Boolean existsByNameAndBacklogId(String  name , Long id);
    Boolean existsByName(String name);
}
