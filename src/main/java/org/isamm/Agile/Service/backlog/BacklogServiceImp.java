package org.isamm.Agile.Service.backlog;


import org.isamm.Agile.Repository.ProductBacklogDao;
import org.isamm.Agile.model.ProductBacklog;
import org.isamm.Agile.model.UserStory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class BacklogServiceImp  {
	
    @Autowired
    private ProductBacklogDao backlogdao;


	public List<ProductBacklog> getBacklog()
	{
		return backlogdao.findAll();
	}
	
	
    public ProductBacklog cloturerBacklog(ProductBacklog backlog)
	{
    	backlog.setCloture();
		return backlogdao.save(backlog);
	}


}

