package org.isamm.Agile.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "typeuser",discriminatorType = DiscriminatorType.STRING,length = 20)
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User implements Serializable {
   @Id 
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String username;
   private String password;
   private String name;
   private String lastname;
   private Long tel;
   private String mail;
   
   @ManyToMany(mappedBy = "users")
   private Collection<Evenement> evenements;
  
   @ManyToMany(fetch = FetchType.EAGER)
   
     private Set<Role> roles = new HashSet<>();

public User(String username, String password, String name, String lastname, Long tel, String mail, Set<Role> roles) {
	super();
	this.username = username;
	this.password = password;
	this.name = name;
	this.lastname = lastname;
	this.tel = tel;
	this.mail = mail;
	this.roles = roles;
	
}

public User( String username, String password, String name, String lastname, Long tel, String mail) {
	super();
	
	this.username = username;
	this.password = password;
	this.name = name;
	this.lastname = lastname;
	this.tel = tel;
	this.mail = mail;
	
}

public User(String username, String password, String mail) {
	super();
	this.username = username;
	this.password = password;
	this.mail = mail;
}


   



   
}