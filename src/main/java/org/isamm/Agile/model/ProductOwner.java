package org.isamm.Agile.model;

import java.util.Collection;

import javax.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("PO")

public class ProductOwner extends User {
	   @ManyToMany
	   public Collection<Equipe> equipes;

	public ProductOwner(String login, String mdp, String nom, String prenom, Long numtel, String mail) {
		super(login, mdp, nom, prenom, numtel, mail);
		
	}
    
    
}