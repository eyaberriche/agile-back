package org.isamm.Agile.model;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;


import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor


public class Project implements Serializable{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long idP;
   private String nomP;
   private Date dateCreation;
   private String typeP;
   
  
    @ManyToOne
    private Team equipe;
    
    @ManyToOne
	private Departement departement;
    
    @ManyToOne
	private Entreprise entreprise;
    
    @OneToOne
    @PrimaryKeyJoinColumn
    private ProductBacklog backlog;
    
    @ManyToMany
    private Collection<Competence> competences;

	public Project(String nomP, Date dateCreation, String typeP) {
		super();
		this.nomP = nomP;
		this.dateCreation = dateCreation;
		this.typeP = typeP;
	}

	public Project(String nomP, Date dateCreation, String typeP, Team equipe,
			Departement departement, Entreprise entreprise) {
		super();
		
		this.nomP = nomP;
		this.dateCreation = dateCreation;
		this.typeP = typeP;
		
		this.equipe = equipe;
		this.departement = departement;
		this.entreprise = entreprise;
	}

	@Override
	public String toString() {
		return "Projet [idP=" + idP + ", nomP=" + nomP + ", dateCreation=" + dateCreation + ", typeP=" + typeP + "]";
	}
	
	

}


	

	
	
    
