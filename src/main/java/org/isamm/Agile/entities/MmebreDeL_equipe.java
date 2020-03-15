package org.isamm.Agile.entities;

 
import java.util.Collection;

import javax.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("MEq")

public class MmebreDeL_equipe extends Personne {
	private String specialite ;  
	
   @OneToMany(mappedBy = "membredelequipe" , fetch = FetchType.LAZY)
   public Collection<Tache> tache;
  
   @ManyToMany(mappedBy = "membredelequipe")
   public Collection<Competence> competences;
   
   @ManyToMany
   public Collection<Equipe> equipes;

public MmebreDeL_equipe(String login, String mdp, String nom, String prenom, Long numtel, String mail,
		String specialite) {
	super(login, mdp, nom, prenom, numtel, mail);
	this.specialite = specialite;
}




   
}