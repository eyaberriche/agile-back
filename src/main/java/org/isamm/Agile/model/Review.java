package org.isamm.Agile.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("review")

public class Review extends Evenement {
	
	 @ManyToOne
	 private ActorEntreprise acteur;

	  public Review(String nomEv, Date datedebEv, Date datefinEv) {
		super(nomEv, datedebEv, datefinEv);
		
	}

}