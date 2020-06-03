package org.isamm.Agile.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    public Project(String name, LocalDate endDate, String description, Collection<User> users, Departement departement, Entreprise entreprise, Typeproject type) {
        this.name = name;
        this.endDate = endDate;
        this.description = description;
        this.users = users;
        this.departement = departement;
        this.entreprise = entreprise;
        this.type = type;
    }

   /* @OneToOne(mappedBy = "project" ,fetch=FetchType.EAGER , cascade = CascadeType.ALL)
    private ProductBacklog backlog;*/

}


	

	
	
    
