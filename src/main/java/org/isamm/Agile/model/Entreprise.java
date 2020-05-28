package org.isamm.Agile.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Entreprise implements Serializable  {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   private String name;

   private String adress;

    private String fax;

  private String email ;


    
  
}
