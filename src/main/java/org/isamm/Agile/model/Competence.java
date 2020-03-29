package org.isamm.Agile.model;

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
   public Collection<Membre> membres;
   
   @ManyToMany(mappedBy = "competences" ,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   public Collection<Projet> projets;

public Competence(int nomC) {
	super();
	this.nomC = nomC;
}

   
}
