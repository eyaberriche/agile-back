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
   public ResponseEntity<?> createUs(@Valid @RequestBody UserStory us) {
      if (userStorydao.existsByNameAndBacklogId(us.getName(),us.getId()))  {
          return ResponseEntity
                  .badRequest()
                  .body(new MessageResponse("Le nom du sprint est déjà existe dans ce backlog !"));
      }

          System.out.println("id" + us.getId());
          ProductBacklog back = new ProductBacklog();
          back.setId(us.getId());
          us.setBacklog(back);
          us.setId(null);
          userStorydao.save(us);
          return ResponseEntity.ok(new MessageResponse("us cree !"));

  }


    @PutMapping("/tri" )
    public ResponseEntity<?> triUs(@Valid @RequestBody UserStory us) {
        UserStory userStory = userStorydao.findById(us.getId()).orElse(null);
        if(us.getName()==null)
        {userStory.setName(userStory.getName());}
        if(us.getSprint()==null)
        {userStory.setSprint(userStory.getSprint());}
        if(us.getBacklog()==null)
        {userStory.setBacklog(userStory.getBacklog());}
        userStory.setPeriorite(us.getPeriorite());
        userStorydao.save(userStory);
        return ResponseEntity.ok(new MessageResponse("us trieé !"));

    }

    @PutMapping("/update/{id}" )
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
                    .body(new MessageResponse("Le nom du user story est déjà existe dans ce backlog !"));
        }

       uss.setName(usrequest.getName());
        if (usrequest.getBacklog()== null)
        {uss.setBacklog(uss.getBacklog());}
        if (usrequest.getSprint()== null)
        {uss.setSprint(uss.getSprint());}
       if (usrequest.getPeriorite()== null)
       {uss.setPeriorite(uss.getPeriorite());}
        userStorydao.save(uss) ;

        return ResponseEntity.ok(new MessageResponse("us modifiée avec succés !"+uss.getName()));}



      @GetMapping("allbybacklog/{id}")
      public List<UserStory> getUsBybacklog(@PathVariable(value = "id") Long id)
             throws ResourceNotFoundException {
          return userStorydao.findByBacklog(id);}


          @GetMapping("allbySprint/{id}")
          public List<UserStory> getUsLibereBysprint(@PathVariable(value = "id") Long id)
             throws ResourceNotFoundException {
              Sprint sprint = sprintdao.findByidd(id);
              Long bcl = sprint.getBacklog().getId();
              return userStorydao.findByusSprint(bcl,id);
      }
          @GetMapping("usbySprint/{id}")
          public List<UserStory> getUsBysprint(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
           return userStorydao.findBySprint(id);
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




