package org.isamm.Agile.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private LocalDate creationDate;
    private LocalDate endDate;
    private String objective;

  
  @OneToMany(mappedBy="sprint",orphanRemoval = true,
          cascade = CascadeType.ALL)
  private Set<Evenement> evenements = new HashSet<>();
  @ManyToOne
  private ProductBacklog backlog ;
  @Transient
  @OneToMany(mappedBy="sprint",orphanRemoval = true,
          cascade = CascadeType.ALL )
  private Set<UserStory> us ;
 /* @JsonIgnore
  public ProductBacklog getBacklog() {
    return backlog;
  }*/


}
