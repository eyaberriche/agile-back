package org.isamm.Agile.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User implements Serializable {
   @Id 
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String username;
   private String password;
   private String name;
   private String lastname;
   private Long tel;
   private String mail;
   private String specialite ;
  /* @ManyToMany(fetch=FetchType.EAGER)
   private Set<Evenement> evenements = new HashSet<>();*/
  
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Competence> competences = new HashSet<>();
    @ManyToOne(fetch=FetchType.EAGER)
    private Entreprise entreprise;

    public User(String username, String password, String name, String lastname, Long tel, String mail, String specialite, Set<Competence> competences) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.tel = tel;
        this.mail = mail;
        this.specialite = specialite;

        this.competences = competences;
    }

    public User(String username, String password, String name, String lastname, Long tel, String mail, String specialite) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.tel = tel;
        this.mail = mail;
        this.specialite = specialite;
    }
}
