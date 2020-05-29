package org.isamm.Agile.web;

import java.util.List;

import org.isamm.Agile.Repository.TypeDao;
import org.isamm.Agile.Security.payload.response.MessageResponse;
import org.isamm.Agile.Service.backlog.BacklogServiceImp;
import org.isamm.Agile.model.ProductBacklog;
import org.isamm.Agile.model.Typeproject;
import org.isamm.Agile.model.UserStory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api/backlog")
public class BacklogController {
@Autowired

private BacklogServiceImp backlogService;


    @GetMapping("/all")
    public  List<ProductBacklog> getAllBacklogs(){
    	return backlogService.getBacklog();
         
    }
    
    @PostMapping("/update" )
    public  ProductBacklog ajouterProductBacklog(@RequestBody ProductBacklog backlog) {
  	       
  	  return backlogService.cloturerBacklog(backlog);
    }



}
