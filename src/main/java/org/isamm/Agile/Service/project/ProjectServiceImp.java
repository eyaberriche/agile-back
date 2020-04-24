package org.isamm.Agile.Service.project;

import org.isamm.Agile.Repository.ProjectDao;
import org.isamm.Agile.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class ProjectServiceImp implements ProjectService {
    @Autowired
    private ProjectDao projectdao;

    @Override
    public Project saveProject(Project project) {
        return projectdao.save(project);
    }

    @Override
    public List<Project> getAllprojects() {
        return  projectdao.findAll();
    }

    @Override
    public Project updateProject(Project project) {
        return projectdao.save(project);
    }

    @Override
    public void deleteProject(Long id) {
        projectdao.deleteById(id);
    }

   /* @Override
    public List<Project> findProjectByDepartement(Integer id) {
        return projectdao.findByDepartement(id);
    }

    @Override
    public List<Project> findProjectByUser(Integer id) {
        return projectdao.findByUser(id);
    }

    @Override
    public List<Project> findProjectByEntreprise(Integer id) {
        return projectdao.findByEntreprise(id);
    }*/


}

