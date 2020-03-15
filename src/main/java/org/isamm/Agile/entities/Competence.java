package org.isamm.Agile.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Competence implements Serializable  {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long idC;
   private int nomC;
   
   @ManyToMany
   public Collection<MmebreDeL_equipe> membredelequipe;
   
   @ManyToMany(mappedBy = "competences")
   public Collection<Projet> projets;

public Competence(int nomC) {
	super();
	this.nomC = nomC;
}

   
}
