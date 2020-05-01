package org.isamm.Agile.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import javax.persistence.*;


import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Sprint implements Serializable{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
    private String name;
    private Double duration;
    private LocalDate dateSp;
    private String objective;
    private String planification;
  
  @ManyToMany
  private Collection <Evenement> evenements;
  


  
  
  
   
 

}
