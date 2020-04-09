package org.isamm.Agile.Repository;

import org.isamm.Agile.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TaskDao extends JpaRepository<Task, Long>{

}
