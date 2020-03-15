package org.isamm.Agile.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;


import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Sprint implements Serializable{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idSp;
  private String nomSp;
  private Double dureSp;
  private Date dateSp;
  private String objectif;
  private String planification;
  
  @OneToMany(mappedBy = "sprint" , fetch = FetchType.LAZY)
  public Collection<UserStory> userStory;
  
  @ManyToMany
  public Collection <Evenement> evenement ;
  
  public Sprint(String nomSp, Double dureSp, Date dateSp, String objectif, String planification) {
	super();
	this.nomSp = nomSp;
	this.dureSp = dureSp;
	this.dateSp = dateSp;
	this.objectif = objectif;
	this.planification = planification;
}


  
  
  
   
 

}