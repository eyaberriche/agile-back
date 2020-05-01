package org.isamm.Agile.web;

import org.isamm.Agile.Security.payload.response.MessageResponse;
import org.isamm.Agile.Service.Departement.DepartementServiceImp;
import org.isamm.Agile.model.Departement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api/departement")
public class DepartementController {
    @Autowired
    private DepartementServiceImp departementService;
	@PostMapping("/create")
	public ResponseEntity<?> createNewEntreprise(@RequestBody Departement departementerequest) {
		Departement departement = departementService.saveDepartement(departementerequest) ;
		return ResponseEntity.ok(new MessageResponse("enterprise registred successfully!\n"+departement));
	}
	@GetMapping("/all")
    public ResponseEntity<?> getAllDepartements() {
		List<Departement> departements = departementService.getAllDepartements();
			return ResponseEntity.ok(departements);
		}


	}
