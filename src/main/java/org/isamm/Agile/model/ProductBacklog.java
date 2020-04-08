package org.isamm.Agile.model;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class ProductBacklog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBL;
	private int nomBL;
	
	@OneToMany(mappedBy = "backlog")
	private Collection<UserStory> userStories;
	
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy ="backlog")
	private Project projet ;


	public ProductBacklog(int nomBL) {
		super();
		this.nomBL = nomBL;
	}


	public ProductBacklog(int nomBL, Project projet) {
		super();
		this.nomBL = nomBL;
		this.projet = projet;
	}
	
   
   
}