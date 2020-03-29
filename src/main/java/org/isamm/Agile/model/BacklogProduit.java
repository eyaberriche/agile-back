package org.isamm.Agile.model;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class BacklogProduit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBL;
	private int nomBL;
	
	@OneToMany(mappedBy = "backlog")
	private Collection<UserStory> userStories;
	
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy ="backlog")
	private Projet projet ;


	public BacklogProduit(int nomBL) {
		super();
		this.nomBL = nomBL;
	}


	public BacklogProduit(int nomBL, Projet projet) {
		super();
		this.nomBL = nomBL;
		this.projet = projet;
	}
	
   
   
}