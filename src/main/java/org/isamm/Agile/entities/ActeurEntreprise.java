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
	@JoinColumn(name ="idE" )
	private Entreprise entreprise;
	
	@OneToOne
	@PrimaryKeyJoinColumn
    private Review review;

    

	}
	
	
	