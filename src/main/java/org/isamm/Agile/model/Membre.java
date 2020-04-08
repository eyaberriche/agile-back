package org.isamm.Agile.model;

 
import java.util.Collection;
import java.util.Set;

import javax.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("MEq")

public class Membre extends User {
	private String specialite ;  
	
   @OneToMany(mappedBy = "membre" , fetch = FetchType.LAZY)
   private Collection<Task> taches;
  
   @ManyToMany(mappedBy = "membres")
   private Collection<Competence> competences;
   
   @ManyToMany
   private Collection<Team> equipes;

public Membre(String username, String password, String name, String lastname, Long tel, String mail,
		String specialite) {
	super(username, password, name, lastname, tel, mail);
	this.specialite = specialite;
}

 




   
}