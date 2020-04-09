package org.isamm.Agile.Repository;

import org.isamm.Agile.model.UserStory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStoryDao extends JpaRepository<UserStory, Long>{

}
