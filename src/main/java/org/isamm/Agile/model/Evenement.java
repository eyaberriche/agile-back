package org.isamm.Agile.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.NaturalId;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_evenement",discriminatorType = DiscriminatorType.STRING,length = 10)
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Evenement implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate fdate;
    private LocalDate ldate;
    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private ObjName objective ;

    @ManyToMany(fetch=FetchType.EAGER)
    private Set<User> users = new HashSet<>();
  /* @ManyToMany(fetch=FetchType.EAGER)
   public Set<Sprint> sprints = new HashSet<>();*/



}
