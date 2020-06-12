package org.isamm.Agile.web;
import java.util.List;

import org.isamm.Agile.Exception.ResourceNotFoundException;
import org.isamm.Agile.Repository.SprintDao;
import org.isamm.Agile.Repository.UserStoryDao;
import org.isamm.Agile.Security.payload.response.MessageResponse;
import org.isamm.Agile.model.ProductBacklog;
import org.isamm.Agile.model.Sprint;
import org.isamm.Agile.model.User;
import org.isamm.Agile.model.UserStory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api/us")
public class UserStoryController {
	
@Autowired
private UserStoryDao userStorydao;
@Autowired
private SprintDao sprintdao ;

  @PostMapping("/create" )
   public ResponseEntity<?> ajouterUS(@Valid @RequestBody UserStory us) {
      if (userStorydao.existsByNameAndBacklogId(us.getName(),us.getBacklog().getId()))  {
          return ResponseEntity
                  .badRequest()
                  .body(new MessageResponse("Erreur : le nom du sprint est déjà existe dans ce backlog !"));
      }
      System.out.println("id"+us.getId());
      ProductBacklog back = new ProductBacklog();
      back.setId(us.getId());
      us.setBacklog(back);
      us.setId(null);
       userStorydao.save(us);
      return ResponseEntity.ok(new MessageResponse("us cree !"));
  }

    @PatchMapping("/update/{id}" )
    public ResponseEntity<?> updateUs(@PathVariable(value = "id") Long id,
                                      @Valid @RequestBody UserStory usrequest)  throws ResourceNotFoundException {


        UserStory uss = userStorydao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("us not found for this id :: " +
                        id));
       if(userStorydao.existsByNameAndBacklogId(usrequest.getName(),uss.getBacklog().getId())
               && (!(uss.getName().equals(usrequest.getName()) )))
         {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erreur : le nom du user story est déjà existe dans ce backlog !"));
        }

       if (usrequest.getName()== null)
       {uss.setName(uss.getName());}
       else
       {uss.setName(usrequest.getName());}
       /* if (usrequest.getBacklog()== null)
        {uss.setBacklog(uss.getBacklog());}
        if (usrequest.getSprint()== null)
        {uss.setSprint(uss.getSprint());}*/
        userStorydao.save(uss) ;

        return ResponseEntity.ok(new MessageResponse("us modifiée avec succés !"));}



      @GetMapping("allbybacklog/{id}")
      public List<UserStory> getUsBybacklog(@PathVariable(value = "id") Long id)
             throws ResourceNotFoundException {
          return userStorydao.findByBacklog(id);}
          @GetMapping("allbySprint/{id}")
          public List<UserStory> getUsBysprint(@PathVariable(value = "id") Long id)
             throws ResourceNotFoundException {
              Sprint sprint = sprintdao.findByidd(id);
              Long bcl = sprint.getBacklog().getId();
              return userStorydao.findByusSprint(bcl,id);
      }
      @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUS(@PathVariable(value = "id") Long Id)
            throws ResourceNotFoundException {
        UserStory userStory = userStorydao.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("userstory not found for this id :: " +
                        Id));

        userStorydao.deleteById(Id);

        return ResponseEntity.ok(new MessageResponse(userStory.getName()+""+"supprimée !"));}
}




