package org.isamm.Agile.Service.Backlog;

import org.isamm.Agile.Exception.ResourceNotFoundException;
import org.isamm.Agile.Repository.ProductBacklogDao;
import org.isamm.Agile.Repository.SprintDao;
import org.isamm.Agile.model.ProductBacklog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BacklogServiceImp  implements  BacklogService{
    @Autowired
    private ProductBacklogDao backlogDao;
    @Autowired
    private SprintDao sprintDao ;

    @Override
    public void deleteBacklog(Long id)  {
       backlogDao.deleteById(id);
        sprintDao.deleteAll(sprintDao.findByBacklog(id));
    }
}
