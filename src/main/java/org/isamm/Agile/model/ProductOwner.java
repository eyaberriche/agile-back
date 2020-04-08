package org.isamm.Agile.model;

import java.util.Collection;
import java.util.Set;

import javax.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("PO")

public class ProductOwner extends User {
	   @ManyToMany
	   public Collection<Team> equipes;

	public ProductOwner(String username, String password, String name, String lastname, Long tel, String mail
			) {
		super(username, password, name, lastname, tel, mail);
	}

	
    
}