package org.isamm.Agile.model;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Entreprise implements Serializable  {
	
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long idE;
   private String nomE;
   private String adresseE;
   
    @OneToMany(mappedBy = "entreprise" , fetch = FetchType.LAZY)
   private Collection<ActorEntreprise> acteurEntreprises;
    
    @OneToMany(mappedBy = "entreprise" , fetch = FetchType.LAZY)
   	private Collection<Project> projets;
    
    
	public Entreprise( String nomE, String adresseE) {
		super();
		this.nomE = nomE;
		this.adresseE = adresseE;
	}


	public Entreprise(String nomE, String adresseE, Collection<ActorEntreprise> acteurEntreprises,
			Collection<Project> projets) {
		super();
		this.nomE = nomE;
		this.adresseE = adresseE;
		this.acteurEntreprises = acteurEntreprises;
		this.projets = projets;
	}
    
  
}