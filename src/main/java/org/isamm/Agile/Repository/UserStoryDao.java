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
            + "WHERE b.id = :id And us.sprint.id =null "

    )
    public List<UserStory> findByBacklog(@Param("id") Long id);
    //public  List<UserStory> findAllByBacklogAndId(Long id , Long idus);

    @Query("SELECT us "
            + "FROM UserStory us "
            + "INNER JOIN us.sprint s "
            + "WHERE s.id = :id "
    )
    public List<UserStory> findBySprint(@Param("id") Long id);
    @Query("SELECT us "
            + "FROM UserStory us "

            + "WHERE us.id = :id"
    )
    public UserStory findByidd(@Param("id") Long id);
    @Query("SELECT us "
            + "FROM UserStory us "
            + "INNER JOIN us.backlog b "
            + "WHERE b.id = :id And (us.sprint.id =null or us.sprint.id = :idd)"

    )
    public List<UserStory> findByusSprint(@Param("id") Long id , @Param("idd") Long idd);
}
