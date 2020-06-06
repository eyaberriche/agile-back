package org.isamm.Agile.web;
import java.util.List;

import org.isamm.Agile.Exception.ResourceNotFoundException;
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

  @PostMapping("/create" )
  public  UserStory ajouterUS(@Valid @RequestBody UserStory us) {
      System.out.println("id"+us.getId());
      ProductBacklog back = new ProductBacklog();
      back.setId(us.getId());
      us.setBacklog(back);
      us.setId(null);
      return userStorydao.save(us);}

    @PutMapping("/update/{id}" )//ki tji tbdl ism us f nfs bcklog
    public ResponseEntity<?> updateUs(@PathVariable(value = "id") Long id,
                                      @Valid @RequestBody UserStory usrequest)  throws ResourceNotFoundException {

        UserStory uss = userStorydao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("entreprise not found for this id :: " +
                        id));


       if (usrequest.getName()== null)
       {uss.setName(uss.getName());}
       else
       {uss.setName(usrequest.getName());}
        if (usrequest.getBacklog()== null)
        {uss.setBacklog(uss.getBacklog());}
        if (usrequest.getSprint()== null)
        {uss.setSprint(uss.getSprint());}
        userStorydao.save(uss) ;

        return ResponseEntity.ok(new MessageResponse("us modifiée avec succés !"));}

    @PutMapping("/liberer/{id}" )//libere us , ywli id sprint mt3ha null (us.sprint=null) ama 9tlo y5li us.bcklog w nom nfshom
    public ResponseEntity<?> libereUs(@PathVariable(value = "id") Long id,
                                      @Valid @RequestBody UserStory usrequest)  throws ResourceNotFoundException {

        UserStory uss = userStorydao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("entreprise not found for this id :: " +
                        id));
        if (usrequest.getName()== null)
        {uss.setName(uss.getName());}
        else
        {uss.setName(usrequest.getName());}
        if (usrequest.getBacklog()== null)
        {uss.setBacklog(uss.getBacklog());}
        uss.setSprint(null);
        userStorydao.save(uss);
        return ResponseEntity.ok(new MessageResponse("us liberé avec succés !"));}

      @GetMapping("allbybacklog/{id}")
      public List<UserStory> getUsBybacklog(@PathVariable(value = "id") Long id)
             throws ResourceNotFoundException {
          return userStorydao.findByBacklog(id);}
          @GetMapping("allbySprint/{id}")
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




