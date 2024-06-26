package org.isamm.Agile.model;

import java.io.Serializable;
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
   private Long id;
   @NotBlank
   private String name;
   @NotBlank
   private String adress;
    @NotBlank

  private String fax;
    @NotBlank

    @Email
   private String email ;



    
  
}
