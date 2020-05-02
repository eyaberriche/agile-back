package org.isamm.Agile.model;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Entreprise implements Serializable  {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Integer id;
   @NotBlank
   private String name;
   @NotBlank
   private String adress;
    @NotBlank
    @Email
   private String email ;
    private String fax ;

 public Entreprise( String name, String adress) {
		super();
		this.name = name;
		this.adress = adress;
	}

    
  
}
