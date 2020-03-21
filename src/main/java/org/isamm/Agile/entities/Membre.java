package org.isamm.Agile.entities;

 
import java.util.Collection;

import javax.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("MEq")

public class Membre extends Personne {
	private String specialite ;  
	
   @OneToMany(mappedBy = "membre" , fetch = FetchType.LAZY)
   private Collection<Tache> taches;
  
   @ManyToMany(mappedBy = "membres")
   private Collection<Competence> competences;
   
   @ManyToMany
   private Collection<Equipe> equipes;

  public Membre(String login, String mdp, String nom, String prenom, Long numtel, String mail,
		String specialite) {
	super(login, mdp, nom, prenom, numtel, mail);
	this.specialite = specialite;
}




   
}