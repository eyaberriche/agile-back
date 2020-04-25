package org.isamm.Agile.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Project implements Serializable{
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String nomP;
   private LocalDate dateCreation;
   private String typeP;
    private String description;
  
    @ManyToMany(fetch=FetchType.EAGER)
    private Set<User> users = new HashSet<>();
    @ManyToOne(fetch=FetchType.EAGER)
	private Departement departement;
    @ManyToOne(fetch=FetchType.EAGER)
	private Entreprise entreprise;
   @OneToOne(fetch=FetchType.EAGER)

    private ProductBacklog backlog;
    @ManyToMany(fetch=FetchType.EAGER)
	private Set<Competence> competences = new HashSet<>();
    public Project(String nomP, String typeP) {
        this.nomP = nomP;
        this.typeP = typeP;
    }
}


	

	
	
    
