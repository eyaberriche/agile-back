package org.isamm.Agile.web;
import org.isamm.Agile.Security.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.isamm.Agile.Service.Entreprise.EntrepriseServiceImp;
import org.isamm.Agile.model.Entreprise;
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

	}



