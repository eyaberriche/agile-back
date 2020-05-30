package org.isamm.Agile.model;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class UserStory implements Serializable{
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String name;
   
   @ManyToOne(fetch=FetchType.EAGER)
   private ProductBacklog backlog ;
   @ManyToOne(fetch=FetchType.EAGER)
   private Sprint sprint ;

public UserStory(String name) {
	super();
	this.name = name;
}

public UserStory(String name, ProductBacklog backlog, Sprint sprint) {
	super();
	this.name = name;
	this.backlog = backlog;
	this.sprint = sprint;
}
    @JsonIgnore
    public ProductBacklog getBacklog() {
        return backlog;
    }
   
   
   
   
  
}
