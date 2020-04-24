package org.isamm.Agile.web;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.isamm.Agile.Service.Entreprise.EntrepriseServiceImp;
import org.isamm.Agile.model.DTOs.EntrepriseDTO;
import org.isamm.Agile.model.Entreprise;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api/entreprise")
public class EntrepriseController {
    @Autowired
    private EntrepriseServiceImp entrepriseService;
    @PostMapping("/entreprises")
    public ResponseEntity<EntrepriseDTO> createNewEntreprise(@RequestBody EntrepriseDTO EntrepriseDTOrequest) {
        Entreprise entrepriseRequest = mapEntrepriseDTOToEntreprise(EntrepriseDTOrequest);
        Entreprise entreprise = entrepriseService.saveEntreprise(entrepriseRequest) ;
        if (entreprise != null && entreprise.getId()!= null) {
            EntrepriseDTO entrepriseDTO = mapEntrepriseToEntrepriseDTO(entreprise);
            return new ResponseEntity<EntrepriseDTO>(entrepriseDTO, HttpStatus.CREATED);
        }
        return new ResponseEntity<EntrepriseDTO>(HttpStatus.NOT_MODIFIED);}
    @GetMapping("/entreprises")
        public ResponseEntity<List<EntrepriseDTO>> getAllEntreprises(){
		List<Entreprise> entreprises = entrepriseService.getAllEntreprises();
		if(!CollectionUtils.isEmpty(entreprises)) {
			//on retire tous les élts null que peut contenir cette liste
			entreprises.removeAll(Collections.singleton(null));
			List<EntrepriseDTO> entrepriseDTOs = entreprises.stream().map(entreprise -> {
				return mapEntrepriseToEntrepriseDTO( entreprise);
			}).collect(Collectors.toList());
			return new ResponseEntity<List<EntrepriseDTO>>(entrepriseDTOs, HttpStatus.OK);
		}
		return new ResponseEntity<List<EntrepriseDTO>>(HttpStatus.NO_CONTENT);
	}



    private Entreprise mapEntrepriseDTOToEntreprise(EntrepriseDTO entrepriseDTOrequest) {
        ModelMapper mapper = new ModelMapper();
        Entreprise entreprise = mapper.map(entrepriseDTOrequest ,Entreprise.class);
        return entreprise;
    }
    private EntrepriseDTO mapEntrepriseToEntrepriseDTO(Entreprise entreprise) {
        ModelMapper mapper = new ModelMapper();
        EntrepriseDTO entrepriseDTO = mapper.map(entreprise, EntrepriseDTO.class);
        return entrepriseDTO;
    }
}
