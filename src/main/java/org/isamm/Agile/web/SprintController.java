package org.isamm.Agile.web;

import org.isamm.Agile.Exception.ResourceNotFoundException;
import org.isamm.Agile.Repository.EvenementDao;
import org.isamm.Agile.Repository.SprintDao;
import org.isamm.Agile.Security.payload.response.MessageResponse;
import org.isamm.Agile.model.Sprint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api/sprint")
public class SprintController {
    @Autowired
    private SprintDao sprintDao ;
    @Autowired
    private EvenementDao eventdao ;
     @GetMapping("allbybacklog/{id}")
     public List<Sprint> getUsBybacklog(@PathVariable(value = "id") Long id)
             throws ResourceNotFoundException {
              return sprintDao.findByBacklog(id);
  }
    @PostMapping("/create")
    public ResponseEntity<?> createNewSprint(@RequestBody Sprint sprintrequest) {
         Sprint sprintt = new Sprint();
        if (sprintDao.existsByNameAndBacklogId(sprintrequest.getName(),sprintrequest.getBacklog().getId()))  {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erreur : le nom du sprint est déjà existe !"));
        }
         sprintrequest.setCreationDate(LocalDate.now());
         Sprint sprint = sprintDao.save(sprintrequest) ;
        return ResponseEntity.ok(new MessageResponse(sprint.getName()));
    }
    @PutMapping("/update/{id}" )
    public ResponseEntity<?> updateEntreprise(@PathVariable(value = "id") Long id,
                                              @Valid @RequestBody Sprint sprintrequest)  throws ResourceNotFoundException {

                 Sprint sprint = sprintDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("sprint not found for this id :: " +
                        id));
        if ((sprintDao.existsByName(sprintrequest.getName())) &&  (!(sprint.getName().equals(sprintrequest.getName())))
                && sprintDao.existsByNameAndBacklogId(sprintrequest.getName(),sprintrequest.getBacklog().getId())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erreur : le nom du sprint est déjà existe dans ce backlog !"));
        }
        sprint.setName(sprintrequest.getName());
        sprint.setEndDate(sprintrequest.getEndDate());
        sprint.setEstimation(sprintrequest.getEstimation());
        sprint.setEvenements(sprint.getEvenements());
        sprint.setObjective(sprint.getObjective());
        sprintDao.save(sprint);
        return ResponseEntity.ok(new MessageResponse(sprint.getName()));}

    @GetMapping("/byId/{id}")
    public ResponseEntity<Sprint> getSprintById(@PathVariable(value = "id") Long sprintId)
            throws ResourceNotFoundException {
        Sprint sprint = sprintDao.findById(sprintId)
                .orElseThrow(() -> new ResourceNotFoundException("Sprint not found for this id :: " + sprintId));
        return ResponseEntity.ok().body(sprint);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSprint(@PathVariable(value = "id") Long Id)
            throws ResourceNotFoundException {
        Sprint sprint = sprintDao.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("sprint not found for this id :: " +
                        Id));
        eventdao.deleteAll(sprint.getEvenements());
        sprintDao.deleteById(Id);

        return ResponseEntity.ok(new MessageResponse(sprint.getName()+""+"supprimée !"));}
}


