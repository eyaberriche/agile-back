package org.isamm.Agile.web;
import org.isamm.Agile.Exception.ResourceNotFoundException;
import org.isamm.Agile.Security.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.isamm.Agile.Service.Entreprise.EntrepriseServiceImp;
import org.isamm.Agile.model.Entreprise;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api/entreprise")
public class EntrepriseController {
    @Autowired
    private EntrepriseServiceImp entrepriseService;
    @PostMapping("/create")
    public ResponseEntity<?> createNewEntreprise(@RequestBody Entreprise Entrepriserequest) {
        if (entrepriseService.checkIfnameExists(Entrepriserequest.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Name is already taken!"));
        }

        if (entrepriseService.checkIfemailExists(Entrepriserequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        Entreprise entreprise = entrepriseService.saveEntreprise(Entrepriserequest) ;
        return ResponseEntity.ok(new MessageResponse("enterprise registred successfully!"+"\n"+entreprise));
        }
    @GetMapping("/all")
        public ResponseEntity<?> getAllEntreprises(){
		List<Entreprise> entreprises = entrepriseService.getAllEntreprises();
		 return ResponseEntity.ok(entreprises);
		}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEntreprise(@PathVariable(value = "id") Long Id)
            throws ResourceNotFoundException {
        Entreprise entreprise = entrepriseService.findbyid(Id)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " +
                        Id));
        entrepriseService.deleteEntreprise(Id);
        return ResponseEntity.ok(new MessageResponse("Entreprise"+""+entreprise.getName()+""+"supprimée !"));}

    @PutMapping("/update/{id}" )
    public ResponseEntity<?> updateEntreprise(@PathVariable(value = "id") Long id,
                                           @Valid @RequestBody Entreprise entrepriserequest)  throws ResourceNotFoundException {

        Entreprise entreprise = entrepriseService.findbyid(id)
                .orElseThrow(() -> new ResourceNotFoundException("entreprise not found for this id :: " +
                        id));
        if ((entrepriseService.checkIfnameExists(entrepriserequest.getName())) &&  (!(entreprise.getName().equals(entrepriserequest.getName())))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Name of entreprise is already taken!"));
        }
        if ((entrepriseService.checkIfemailExists(entrepriserequest.getEmail())) &&  (!(entreprise.getEmail().equals(entrepriserequest.getEmail())))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: email of entreprise is already taken!"));
        }

        entreprise.setName(entrepriserequest.getName());
        entreprise.setAdress(entrepriserequest.getAdress());
        entreprise.setEmail(entrepriserequest.getEmail());
        entreprise.setFax(entrepriserequest.getFax());
        entrepriseService.updateEntreprise(entreprise) ;

        return ResponseEntity.ok(new MessageResponse("entreprise updated"+entreprise));}

    @GetMapping("/byId/{id}")
    public ResponseEntity<Entreprise> getEntrepriseById(@PathVariable(value = "id") Long entrepriseId)
            throws ResourceNotFoundException {
        Entreprise entreprise = entrepriseService.findbyid(entrepriseId)
                .orElseThrow(() -> new ResourceNotFoundException("Entreprise not found for this id :: " + entrepriseId));
        return ResponseEntity.ok().body(entreprise);
    }
}



