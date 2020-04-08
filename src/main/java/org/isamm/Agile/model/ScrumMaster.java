package org.isamm.Agile.model;

import java.util.Collection;
import java.util.Set;

import javax.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("SM")

public class ScrumMaster extends User {
	 @OneToMany(mappedBy = "scrumMaster" , fetch = FetchType.LAZY)
	 private Collection<Team> equipes;

	public ScrumMaster(String username, String password, String name, String lastname, Long tel, String mail
			) {
		super(username, password, name, lastname, tel, mail);
	}





	 

	
	
}