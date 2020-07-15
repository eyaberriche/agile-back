package org.isamm.Agile.web;

import org.isamm.Agile.Exception.ResourceNotFoundException;
import org.isamm.Agile.Repository.EvenementDao;
import org.isamm.Agile.Repository.SprintDao;
import org.isamm.Agile.Repository.UserStoryDao;
import org.isamm.Agile.Security.payload.response.MessageResponse;
import org.isamm.Agile.model.ProductBacklog;
import org.isamm.Agile.model.Sprint;
import org.isamm.Agile.model.UserStory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api/sprint")
public class SprintController {
    @Autowired
    private SprintDao sprintDao ;
    @Autowired
    private EvenementDao eventdao ;
    @Autowired
    private UserStoryDao userStoryDao;
     @GetMapping("allbybacklog/{id}")
     public List<Sprint> getSprintBybacklog(@PathVariable(value = "id") Long id)
             throws ResourceNotFoundException {
              return sprintDao.findByBacklog(id);
  }
    @PostMapping("/create")
    public ResponseEntity<?> createNewSprint(@RequestBody Sprint sprintrequest)   {


        if (sprintDao.existsByNameAndBacklogId(sprintrequest.getName(),sprintrequest.getBacklog().getId()))  {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erreur : le nom du sprint est déjà existe dans ce backlog !"));
        }
                sprintrequest.setCreationDate(sprintrequest.getCreationDate().plusDays(1));
                sprintrequest.setEndDate(sprintrequest.getEndDate().plusDays(1));
                sprintDao.save(sprintrequest) ;
                Set<UserStory> uss= sprintrequest.getUs();
                uss.forEach(us -> {us.setSprint(sprintrequest);
                 UserStory us1 = userStoryDao.findByidd(us.getId());
                 String name = us1.getName();
                    us.setBacklog(sprintrequest.getBacklog());
                    if(us1.getName()!=null)
                   {us.setName(name);}
                  userStoryDao.save(us);


                });



        return ResponseEntity.ok(new MessageResponse(sprintrequest.getName()+""));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSprint(@PathVariable(value = "id") Long id,
                                              @Valid @RequestBody Sprint sprintrequest)  throws ResourceNotFoundException {

                 Sprint sprint = sprintDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("sprint not found for this id :: " +
                        id));
        if(sprintDao.existsByNameAndBacklogId(sprintrequest.getName(),sprint.getBacklog().getId())
                && (!(sprint.getName().equals(sprintrequest.getName()) )))
        { return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Le nom  du sprint est déjà existe dans ce backlog !"));
        }
           sprint.setName(sprintrequest.getName());
           sprint.setUs(sprintrequest.getUs());
           sprint.setCreationDate(sprintrequest.getCreationDate().plusDays(1));
           sprint.setEndDate(sprintrequest.getEndDate().plusDays(1));
           sprint.setObjective(sprintrequest.getObjective());
           if (sprintrequest.getBacklog()== null)
           {sprint.setBacklog(sprint.getBacklog());}
            sprintDao.save(sprint);
            List<UserStory> ss = userStoryDao.findBySprint(id);
            ss.forEach(uz -> {uz.setSprint(null);
            UserStory us1 = userStoryDao.findByidd(uz.getId());
            String name = us1.getName();
            uz.setBacklog(sprint.getBacklog());
            if(us1.getName()!=null)
            {uz.setName(name);}
            userStoryDao.save(uz);});

            Set<UserStory> uss= sprintrequest.getUs();
            uss.forEach(us -> {us.setSprint(sprint);
            UserStory us1 = userStoryDao.findByidd(us.getId());
            String name = us1.getName();
            us.setBacklog(sprint.getBacklog());
            if(us1.getName()!=null)
            {us.setName(name);}
            userStoryDao.save(us);


        });
        //sprintDao.save(sprint);



        return ResponseEntity.ok(new MessageResponse(sprint.getName()));}

    @GetMapping("/byId/{id}")
    public ResponseEntity<Sprint> getSprintById(@PathVariable(value = "id") Long sprintId)
            throws ResourceNotFoundException {
        Sprint sprint = sprintDao.findById(sprintId)
                .orElseThrow(() -> new ResourceNotFoundException("Sprint not found for this id : " + sprintId));
        return ResponseEntity.ok().body(sprint);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSprint(@PathVariable(value = "id") Long Id)
            throws ResourceNotFoundException {
        Sprint sprint = sprintDao.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("sprint not found for this id :: " +
                        Id));
        List<UserStory> uss = userStoryDao.findBySprint(Id);
        uss.forEach(us -> {us.setSprint(null);});
        eventdao.deleteAll(sprint.getEvenements());
        sprintDao.deleteById(Id);

        return ResponseEntity.ok(new MessageResponse(sprint.getName()+""+"supprimée !"));}
}


