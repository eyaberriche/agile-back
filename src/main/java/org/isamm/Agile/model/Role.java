package org.isamm.Agile.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

import org.hibernate.annotations.NaturalId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role implements Serializable {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	 
	    @Enumerated(EnumType.STRING)
	    @NaturalId
	    @Column(length = 60)
	    private RoleName name;
/*	 @ManyToMany
	 private Collection<User> users;*/
}
