package org.isamm.Agile.model;


import java.util.Date;

import javax.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@DiscriminatorValue("planning")

public class Planning extends Evenement {
	
	public Planning(String nomEv, Date datedebEv, Date datefinEv) {
		super(nomEv, datedebEv, datefinEv);
	}
}