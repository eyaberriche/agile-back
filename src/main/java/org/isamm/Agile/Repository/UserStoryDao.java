package org.isamm.Agile.Repository;

import org.isamm.Agile.model.UserStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserStoryDao extends JpaRepository<UserStory, Long>{
    @Query("SELECT us "
            + "FROM UserStory us "
            + "INNER JOIN us.backlog b "
            + "WHERE b.id = :id"
    )
    public List<UserStory> findByBacklog(@Param("id") Long id);
}
