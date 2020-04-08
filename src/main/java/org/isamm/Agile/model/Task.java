package org.isamm.Agile.model;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Task implements Serializable {
   @Id 
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int idT;
   private String nomT;
   
   @ManyToOne   
   private UserStory userStory ;
   
   @ManyToOne   
   private Membre membre ;

public Task(String nomT) {
	super();
	this.nomT = nomT;
}


}