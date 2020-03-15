package org.isamm.Agile.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;


import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Projet implements Serializable{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long idP;
   private String nomP;
   private Date dateCreation;
   private String typeP;
  
    @ManyToMany
    private Collection<Equipe> equipes;
    
    @ManyToOne
	private Departement departement;
    
    @ManyToOne
	private Entreprise entreprise;
    
    @OneToOne
    @PrimaryKeyJoinColumn
    private BacklogProduit backlog;
    
    @ManyToMany
    private Collection<Competence> competences;

	public Projet(String nomP, Date dateCreation, String typeP) {
		super();
		this.nomP = nomP;
		this.dateCreation = dateCreation;
		this.typeP = typeP;
	}

	public Projet( String nomP, Date dateCreation, String typeP, Departement departement,
			Entreprise entreprise) {
		super();
		
		this.nomP = nomP;
		this.dateCreation = dateCreation;
		this.typeP = typeP;
		this.departement = departement;
		this.entreprise = entreprise;
	}

	
    
}