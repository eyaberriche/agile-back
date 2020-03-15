package org.isamm.Agile.entities;


import java.util.Date;

import javax.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@DiscriminatorValue("dailyscrum")
public class DailyScrum extends Evenement {
	
	public DailyScrum(String nomEv, Date datedebEv, Date datefinEv) {
		super(nomEv, datedebEv, datefinEv);
	}
	
}