package org.isamm.Agile.web;

import java.util.List;

import org.isamm.Agile.Repository.ProductBacklogDao;
import org.isamm.Agile.Repository.TypeDao;
import org.isamm.Agile.Security.payload.response.MessageResponse;
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

private ProductBacklogDao backlogDao;


    @GetMapping("/all")
    public  List<ProductBacklog> getAllBacklogs(){
    	return  backlogDao.findAll();
         
    }
    @GetMapping("/byUser/{id}")
    public List<ProductBacklog> getBacklogbyUser(@PathVariable(value = "id") Long Id){
        return   backlogDao.findByUser(Id);

    }
    
    @PostMapping("/update" )
    public  ProductBacklog updateBacklog(@RequestBody ProductBacklog backlog) {

        ProductBacklog back = backlogDao.findById(backlog.getId()).orElse(null);
        back.setCloture(!back.isCloture());
        return backlogDao.save(back);
    }



}
