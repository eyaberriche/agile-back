package org.isamm.Agile.web;
import java.util.List;

import org.isamm.Agile.Exception.ResourceNotFoundException;
import org.isamm.Agile.Repository.UserStoryDao;
import org.isamm.Agile.Security.payload.response.MessageResponse;
import org.isamm.Agile.model.ProductBacklog;
import org.isamm.Agile.model.User;
import org.isamm.Agile.model.UserStory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins ="http://localhost:4200" )
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

      @GetMapping("allbybacklog/{id}")
      public List<UserStory> getUsBybacklog(@PathVariable(value = "id") Long id)
             throws ResourceNotFoundException {
          return userStorydao.findByBacklog(id);
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




