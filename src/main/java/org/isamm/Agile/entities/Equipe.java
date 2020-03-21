package org.isamm.Agile.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor


public class Equipe implements Serializable  {
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
   private Collection<Projet> projets;

public Equipe(String nomEq) {
	super();
	this.nomEq = nomEq;
}

   
  
}