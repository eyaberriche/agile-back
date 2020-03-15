package org.isamm.Agile.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_pers",discriminatorType = DiscriminatorType.STRING,length = 3)
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Personne implements Serializable {
   @Id 
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String login;
   private String mdp;
   private String nom;
   private String prenom;
   private Long numtel;
   private String mail;
   
   @ManyToMany(mappedBy = "personnes")
   public Collection<Evenement> evenements;

 public Personne(String login, String mdp, String nom, String prenom, Long numtel, String mail) {
	super();
	this.login = login;
	this.mdp = mdp;
	this.nom = nom;
	this.prenom = prenom;
	this.numtel = numtel;
	this.mail = mail;
}
   
}