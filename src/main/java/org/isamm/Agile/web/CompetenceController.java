package org.isamm.Agile.web;


import org.isamm.Agile.Exception.ResourceNotFoundException;
import org.isamm.Agile.Repository.CompetenceDao;
import org.isamm.Agile.Security.payload.response.MessageResponse;
import org.isamm.Agile.model.Competence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api/competence")
public class CompetenceController {
    @Autowired
    private CompetenceDao compdao ;
    @GetMapping("/allCompetences")
    public List<Competence> getAllcompetence() {
        return compdao.findAll();
    }
    @PostMapping("/createCompetence")
    public ResponseEntity<?> createCompetence(@RequestBody Competence competencerequest) {

        if (compdao.existsByName(competencerequest.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erreur : le nom de compétence est déjà existe !"));
        }

        Competence competence = compdao.save(competencerequest) ;
        return ResponseEntity.ok(new MessageResponse("ajout de compétence réussi !"));
    }
    @DeleteMapping("/deleteCompetence/{id}")
    public ResponseEntity<?> deleteCompetence(@PathVariable(value = "id") Long competenceId)
            throws ResourceNotFoundException {
        Competence competence = compdao.findById(competenceId)
                .orElseThrow(() -> new ResourceNotFoundException("competence not found for this id :: " +
                        competenceId));
        compdao.deleteById(competenceId);
        return ResponseEntity.ok(new MessageResponse("compétence supprimeé !"));}
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTask(@PathVariable(value = "id") Long id,
                                        @Valid @RequestBody Competence comprequest) throws ResourceNotFoundException {

        Competence competence = compdao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("competence not found for this id :: " +
                        id));
        if ((compdao.existsByName(comprequest.getName())) &&  (!(competence.getName().equals(comprequest.getName())))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Le nom  du compétence est déjà existe !"));
        }
        competence.setName(comprequest.getName());
        compdao.save(competence);
        return ResponseEntity.ok(new MessageResponse("compétence modifieé !"));}
}
