package org.isamm.Agile.web;
import java.time.LocalDate;
import java.util.List;

import org.isamm.Agile.Repository.TypeDao;
import org.isamm.Agile.Security.payload.response.MessageResponse;
import org.isamm.Agile.Service.project.ProjectServiceImp;
import org.isamm.Agile.model.Project;
import org.isamm.Agile.model.Typeproject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api/project")
public class ProjectController {
@Autowired
private ProjectServiceImp projectService;
@Autowired
private TypeDao typeDao ;
  @PostMapping("/create" )
  public ResponseEntity<?> createNewProject(@RequestBody Project projectrequest) {
      if (projectService.checkIfnameExists(projectrequest.getName())) {
          return ResponseEntity
                  .badRequest()
                  .body(new MessageResponse("Error: Name is already taken!"));
      }
            projectrequest.setCreationDate(LocalDate.now());
			Project project = projectService.saveProject(projectrequest) ;
      return ResponseEntity.ok(project);}


    @GetMapping("/all")
    public ResponseEntity<?> getAllProjects(){
        List<Project> projects = projectService.getAllprojects();
        return ResponseEntity.ok(projects);
    }
    @GetMapping("/allTypes")
    public ResponseEntity<?> getAllTypeProjects(){
        List<Typeproject> types = typeDao.findAll();
        return ResponseEntity.ok(types);
    }








}
