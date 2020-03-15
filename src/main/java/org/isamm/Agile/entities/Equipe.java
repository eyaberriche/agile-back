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
   public Collection<MmebreDeL_equipe> mmebreDeL_equipe;
   
   @ManyToMany(mappedBy = "equipes")
   public Collection<ProductOwner> productOwner;
   
   @ManyToOne
   public ScrumMaster scrumMaster;
   
   @ManyToMany(mappedBy = "equipes")
   public Collection<Projet> projet;

public Equipe(String nomEq) {
	super();
	this.nomEq = nomEq;
}
   
  
}