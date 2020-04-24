package org.isamm.Agile.web;

import org.isamm.Agile.Service.Departement.DepartementServiceImp;
import org.isamm.Agile.model.DTOs.DepartementDTO;
import org.isamm.Agile.model.Departement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api/departement")
public class DepartementController {
    @Autowired
    private DepartementServiceImp departementService;
	@GetMapping("/departements")
    public ResponseEntity<List<DepartementDTO>> getAllDepartements(){
		List<Departement> departements = departementService.getAllDepartements();
		if(!CollectionUtils.isEmpty(departements)) {
			//on retire tous les élts null que peut contenir cette liste
			departements.removeAll(Collections.singleton(null));
			List<DepartementDTO> departementDTOs = departements.stream().map(departement -> {
				return mapDepartementToDepartementDTO(departement);
			}).collect(Collectors.toList());
			return new ResponseEntity<List<DepartementDTO>>(departementDTOs, HttpStatus.OK);
		}
		return new ResponseEntity<List<DepartementDTO>>(HttpStatus.NO_CONTENT);
	}


    private DepartementDTO mapDepartementToDepartementDTO(Departement departement) {
        ModelMapper mapper = new ModelMapper();
        DepartementDTO departementDTO = mapper.map(departement, DepartementDTO.class);
        return departementDTO;
    }






}
