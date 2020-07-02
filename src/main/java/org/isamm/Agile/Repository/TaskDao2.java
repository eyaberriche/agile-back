package org.isamm.Agile.Repository;

import org.isamm.Agile.model.StatusTask;
import org.isamm.Agile.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskDao2 extends JpaRepository<Task, Long>{


}
