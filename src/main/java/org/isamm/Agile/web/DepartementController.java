package org.isamm.Agile.web;

import org.isamm.Agile.Repository.DepartementDao;
import org.isamm.Agile.Security.payload.response.MessageResponse;
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
    private DepartementDao departementdao;
	@GetMapping("/all")
    public ResponseEntity<?> getAllDepartements() {
		List<Departement> departements = departementdao.findAll();
			return ResponseEntity.ok(departements);
		}


	}
