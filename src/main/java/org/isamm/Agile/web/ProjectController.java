package org.isamm.Agile.web;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.isamm.Agile.Exception.ResourceNotFoundException;
import org.isamm.Agile.Repository.ProductBacklogDao;
import org.isamm.Agile.Repository.SprintDao;
import org.isamm.Agile.Repository.TypeDao;
import org.isamm.Agile.Security.payload.response.MessageResponse;
import org.isamm.Agile.Service.Backlog.BacklogService;
import org.isamm.Agile.Service.project.ProjectServiceImp;
import org.isamm.Agile.model.ProductBacklog;
import org.isamm.Agile.model.Project;
import org.isamm.Agile.model.Typeproject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api/project")
public class ProjectController {
@Autowired
private ProjectServiceImp projectService;
@Autowired
private TypeDao typeDao ;
@Autowired
private ProductBacklogDao backlogDao;
@Autowired
private SprintDao sprintDao;
@Autowired
private BacklogService bc ;


    @PostMapping("/create" )
  public ResponseEntity<?> createNewProject(@RequestBody Project projectrequest) {
      if (projectService.checkIfnameExists(projectrequest.getName())) {
          return ResponseEntity
                  .badRequest()
                  .body(new MessageResponse("  Nom  du projet  déjà existe !"));}

            projectrequest.setCreationDate(LocalDate.now().plusDays(1));
            projectrequest.setEndDate(projectrequest.getEndDate().plusDays(2));
            ProductBacklog backlog = new ProductBacklog(projectrequest.getName());
            backlog.setProject(projectrequest);
            backlogDao.save(backlog);
            //projectrequest.setBacklog(backlog);
			Project project = projectService.saveProject(projectrequest) ;
            return ResponseEntity.ok(new MessageResponse(project.getName()));}
    @PutMapping("/update/{id}" )
     public ResponseEntity<?> updateProject(@PathVariable(value = "id") Long id,
                                            @Valid @RequestBody Project projectrequest)  throws ResourceNotFoundException {

       Project project = projectService.checkIfIdExists(id)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " +
                        id));
        if ((projectService.checkIfnameExists(projectrequest.getName())) &&  (!(project.getName().equals(projectrequest.getName())))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Le nom  du projet est déjà existe !"));
        }
       // project.setId(project.getId());
        project.setName(projectrequest.getName());
        ProductBacklog back = backlogDao.findByProject(id);
        back.setName(projectrequest.getName());
        back.setProject(project);
        backlogDao.save(back);
        project.setEndDate(projectrequest.getEndDate());
        project.setDescription(projectrequest.getDescription());
        project.setType(projectrequest.getType());
        project.setDepartement(projectrequest.getDepartement());
        project.setEntreprise(projectrequest.getEntreprise());
        project.setUsers(projectrequest.getUsers());
        project.setEndDate(projectrequest.getEndDate());

        projectService.updateProject(project) ;

        return ResponseEntity.ok(new MessageResponse(project.getName()));}


    @GetMapping("/all")
    public ResponseEntity<?> getAllProjects(){
        List<Project> projects = projectService.getAllprojects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/allTypes")
    public ResponseEntity<?> getAllTypes(){
        List<Typeproject> types = typeDao.findAll();
        return ResponseEntity.ok(types);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable(value = "id") Long Id)
            throws ResourceNotFoundException {
        Project project = projectService.findbyid(Id)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " +
                        Id));

        ProductBacklog backlog = backlogDao.findByProject(Id);
        backlog.setProject(null);
        bc.deleteBacklog(backlog.getId());
        projectService.deleteProject(Id);
        return ResponseEntity.ok(new MessageResponse("project suppppppp"));}

    @GetMapping("/byId/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable(value = "id") Long projectId)
            throws ResourceNotFoundException {
        Project project = projectService.findbyid(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("project not found for this id :: " + projectId));
        return ResponseEntity.ok().body(project);
    }



}
