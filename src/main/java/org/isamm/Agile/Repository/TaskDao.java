package org.isamm.Agile.Repository;

import org.isamm.Agile.model.StatusTask;
import org.isamm.Agile.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskDao extends JpaRepository<Task, Long>{
    @Query("SELECT t "
            + "FROM Task t "
            + "INNER JOIN t.userStory us "
            + "WHERE us.id = :id"
    )
    public List<Task> findByUserStory(@Param("id") Long id);
    Boolean existsByTitleAndUserStoryId(String  name , Long id);
    Boolean existsByTitleAndUserId(String  name , Long id);
    public List<Task> findAllByUserStoryIdAndStatus(Long id , StatusTask status);
    public List<Task> findAllByUserId(Long id);

  //  @Query("seLECT task.id as id,task.creation_date,task.estimation_date,sprint.id as sprint_id FROM task,user_story,sprint WHERE user_story_id = user_story.id and user_story.sprint_id=sprint.id")
  @Query("SELECT t "
          + "FROM Task t "
          + "INNER JOIN t.userStory us "
          + "INNER JOIN us.sprint s "
          + "WHERE s.id = :id"
  )
    public List<Task> findBySprint(@Param("id")Long id);
}
