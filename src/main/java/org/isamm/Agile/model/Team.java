package org.isamm.Agile.model;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor


public class Team implements Serializable  {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long idEq;
   private String nomEq;
   
   @ManyToMany(mappedBy = "equipes")
   private Collection<Membre> membres;
   
   @ManyToMany(mappedBy = "equipes")
   private Collection<ProductOwner> productOwners;
   
   @ManyToOne
   private ScrumMaster scrumMaster;
   
   @OneToMany(mappedBy = "equipe" , fetch = FetchType.LAZY)
   private Collection<Project> projets;

public Team(String nomEq) {
	super();
	this.nomEq = nomEq;
}

   
  
}