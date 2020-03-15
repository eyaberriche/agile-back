package org.isamm.Agile.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class UserStory implements Serializable{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long idUS;
   private String nomUS;
   
   @OneToMany(mappedBy = "userstory" , fetch = FetchType.LAZY)
   public Collection<Tache> taches;
   
   @ManyToOne
   
   private BacklogProduit backlog ;
   

   @ManyToOne
   
   private Sprint sprint ;

public UserStory(String nomUS) {
	super();
	this.nomUS = nomUS;
}

public UserStory(String nomUS, BacklogProduit backlog, Sprint sprint) {
	super();
	this.nomUS = nomUS;
	this.backlog = backlog;
	this.sprint = sprint;
}
   
   
   
   
  
}