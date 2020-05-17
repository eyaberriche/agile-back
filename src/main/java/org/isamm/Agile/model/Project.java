package org.isamm.Agile.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Project implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique=true)
    private String name;
    private LocalDate creationDate;
    private LocalDate endDate;
    private String description;
  
    @ManyToMany(fetch=FetchType.EAGER)
    private  Collection <User> users ;
    @ManyToOne(fetch = FetchType.EAGER )
	private Departement departement;
   // @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER )
	private Entreprise entreprise;
    @ManyToOne(fetch=FetchType.EAGER)
    private Typeproject type;
    @OneToOne(fetch=FetchType.EAGER , cascade = CascadeType.ALL)
    private ProductBacklog backlog;
    /*@ManyToMany(fetch=FetchType.EAGER)
	private Set<Competence> competences = new HashSet<>();*/

}


	

	
	
    
