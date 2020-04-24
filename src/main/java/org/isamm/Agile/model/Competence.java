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
   private String nomC;

public Competence(String nomC) {
	super();
	this.nomC = nomC;
}

   
}
