package org.isamm.Agile.model;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;



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
   private Long idEv;
   private String nomEv;
    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private ObjName objective ;
    private Date datedebEv;
    private Date datefinEv;

   @ManyToMany(mappedBy = "evenements")
   public Collection<Sprint> sprints;

public Evenement(String nomEv, Date datedebEv, Date datefinEv) {
	super();
	this.nomEv = nomEv;
	this.datedebEv = datedebEv;
	this.datefinEv = datefinEv;
}


   
  
   
   
   

}
