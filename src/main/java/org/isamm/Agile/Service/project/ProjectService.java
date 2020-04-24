package org.isamm.Agile.Service.project;

import org.isamm.Agile.model.Project;

import java.util.List;

public interface ProjectService {
    public Project saveProject(Project project);
    public List<Project> getAllprojects();
    public Project updateProject(Project project);
    public void deleteProject(Long id);

   /* public boolean checkIfIdExists(Integer id);
     public List<Project> findProjectByDepartement( Integer id);
    public List<Project> findProjectByUser(Integer id);
    public List<Project> findProjectByEntreprise(Integer id);*/


}
