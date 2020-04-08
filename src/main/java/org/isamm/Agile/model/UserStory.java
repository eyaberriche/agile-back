package org.isamm.Agile.model;

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
   
   @OneToMany(mappedBy = "userStory" , fetch = FetchType.LAZY)
   private Collection<Task> taches;
   
   @ManyToOne   
   private ProductBacklog backlog ;
   

   @ManyToOne   
   private Sprint sprint ;

public UserStory(String nomUS) {
	super();
	this.nomUS = nomUS;
}

public UserStory(String nomUS, ProductBacklog backlog, Sprint sprint) {
	super();
	this.nomUS = nomUS;
	this.backlog = backlog;
	this.sprint = sprint;
}
   
   
   
   
  
}