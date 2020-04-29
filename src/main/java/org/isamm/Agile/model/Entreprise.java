package org.isamm.Agile.model;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Entreprise implements Serializable  {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   private String nomE;
   private String adresseE;

 public Entreprise( String nomE, String adresseE) {
		super();
		this.nomE = nomE;
		this.adresseE = adresseE;
	}

    
  
}
