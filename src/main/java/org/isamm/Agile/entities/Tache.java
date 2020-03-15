package org.isamm.Agile.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Tache implements Serializable {
   @Id 
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int idT;
   private String nomT;
   
   @ManyToOne
   
   private UserStory userstory ;
   
   @ManyToOne
   
   private MmebreDeL_equipe membredelequipe ;

public Tache(String nomT) {
	super();
	this.nomT = nomT;
}


}