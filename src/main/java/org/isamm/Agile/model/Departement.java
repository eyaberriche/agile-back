package org.isamm.Agile.model;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Departement implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long idDep;
   private String libDep;
   private int nombreEmp;
   
   @OneToMany(mappedBy = "departement" , fetch = FetchType.LAZY)
   private Collection<Projet> projets;

public Departement(String libDep, int nombreEmp) {
	super();
	this.libDep = libDep;
	this.nombreEmp = nombreEmp;
}
   
 

}