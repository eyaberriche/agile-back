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
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String name;

public Competence(String name) {
	super();
	this.name = name;
}

   
}
