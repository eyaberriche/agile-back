package org.isamm.Agile.model;


import java.util.Date;
import javax.persistence.*;

@Entity
@DiscriminatorValue("retrospective")
public class Retrospective extends Evenement {
	
	public Retrospective(String nomEv, Date datedebEv, Date datefinEv) {
		super(nomEv, datedebEv, datefinEv);
	}
}