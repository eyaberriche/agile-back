	package org.isamm.Agile.entities;

import java.util.Collection;
import javax.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("AE")
public class ActeurEntreprise extends Personne {
	@ManyToOne
	
	private Entreprise entreprise;
	
	@OneToMany(mappedBy = "acteur" , fetch = FetchType.LAZY)
	 private Collection<Review> reviews;

    

	}
	
	
	