package org.isamm.Agile.model;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;



import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_evenement",discriminatorType = DiscriminatorType.STRING,length = 10)
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Evenement implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long idEv;
   private String nomEv;
   private Date datedebEv;
   private Date datefinEv;
   
   @ManyToMany   
   public Collection<User> users;
   
   @ManyToMany(mappedBy = "evenements")
   public Collection<Sprint> sprints;

public Evenement(String nomEv, Date datedebEv, Date datefinEv) {
	super();
	this.nomEv = nomEv;
	this.datedebEv = datedebEv;
	this.datefinEv = datefinEv;
}


   
  
   
   
   

}