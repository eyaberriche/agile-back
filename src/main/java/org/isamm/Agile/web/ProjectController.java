package org.isamm.Agile.web;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.isamm.Agile.Repository.UserDao;
import org.isamm.Agile.Service.project.ProjectServiceImp;
import org.isamm.Agile.model.DTOs.ProjectDTO;
import org.isamm.Agile.model.Project;
import org.isamm.Agile.model.RoleName;
import org.isamm.Agile.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api/project")
public class ProjectController {
@Autowired
private ProjectServiceImp projectService;
  @PostMapping("/projects" )
		public ResponseEntity<ProjectDTO> createNewProject(@RequestBody ProjectDTO ProjectDTOrequest) {
            Project projectRequest = mapProjectDTOToProject(ProjectDTOrequest);
            projectRequest.setDateCreation(LocalDate.now());
			Project project = projectService.saveProject(projectRequest) ;
			if (project != null && project.getId()!= null) {
				ProjectDTO projectDTO = mapProjectToProjectDTO(project);
				return new ResponseEntity<ProjectDTO>(projectDTO, HttpStatus.CREATED);
			}
			return new ResponseEntity<ProjectDTO>(HttpStatus.NOT_MODIFIED);}

    @GetMapping("/projects")
    public ResponseEntity<List<ProjectDTO>> getAllDepartements(){
        List<Project> projects = projectService.getAllprojects();
        if(!CollectionUtils.isEmpty(projects)) {
            //on retire tous les élts null que peut contenir cette liste
            projects.removeAll(Collections.singleton(null));
            List<ProjectDTO> projectDTOs = projects.stream().map(project -> {
                return mapProjectToProjectDTO(project);
            }).collect(Collectors.toList());
            return new ResponseEntity<List<ProjectDTO>>(projectDTOs, HttpStatus.OK);
        }
        return new ResponseEntity<List<ProjectDTO>>(HttpStatus.NO_CONTENT);
    }

 private Project mapProjectDTOToProject(ProjectDTO projectDTOrequest) {
        ModelMapper mapper = new ModelMapper();
        Project project = mapper.map(projectDTOrequest ,Project.class);
        return project;
    }
    private ProjectDTO mapProjectToProjectDTO(Project project) {
        ModelMapper mapper = new ModelMapper();
        ProjectDTO projectDTO = mapper.map(project, ProjectDTO.class);
        return projectDTO;









}}
