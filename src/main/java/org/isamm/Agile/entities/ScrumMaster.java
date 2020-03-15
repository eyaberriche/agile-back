package org.isamm.Agile.entities;

import java.util.Collection;
import javax.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("SM")

public class ScrumMaster extends Personne {
	 @OneToMany(mappedBy = "scrumMaster" , fetch = FetchType.LAZY)
	 private Collection<Equipe> equipes;

	public ScrumMaster(String login, String mdp, String nom, String prenom, Long numtel, String mail) {
		super(login, mdp, nom, prenom, numtel, mail);
	}




	 

	
	
}