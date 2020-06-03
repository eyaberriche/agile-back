package org.isamm.Agile.Service.Backlog;

import org.isamm.Agile.Exception.ResourceNotFoundException;

public interface BacklogService  {
    public void deleteBacklog(Long id) throws ResourceNotFoundException;

}
