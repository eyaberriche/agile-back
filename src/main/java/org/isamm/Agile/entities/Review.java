package org.isamm.Agile.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("review")

public class Review extends Evenement {
	
	 @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy ="review")
	 private ActeurEntreprise acteur;

	public Review(String nomEv, Date datedebEv, Date datefinEv) {
		super(nomEv, datedebEv, datefinEv);
		
	}

}