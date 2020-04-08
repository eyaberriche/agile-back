	package org.isamm.Agile.model;

import java.util.Collection;
import javax.persistence.*;
import lombok.*;

@Entity

@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("AE")
public class ActorEntreprise extends User {
	@ManyToOne
	private Entreprise entreprise;
	
	@OneToMany(mappedBy = "acteur" , fetch = FetchType.LAZY)
	 private Collection<Review> reviews;

    

	}
	
	
	