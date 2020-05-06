package org.isamm.Agile.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
   private String firstname;
   private String lastname;
   private String tel;
   private String email;
   private String specialite ;
  /**/

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Competence> competences = new HashSet<>();


    public User(String username, String password, String firstname, String lastname, String tel, String email, String specialite, Set<Competence> competences) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.tel = tel;
        this.email = email;
        this.specialite = specialite;
        this.competences = competences;
    }

    public User(String username, String password, String firstname, String lastname, String tel, String email, String specialite) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.tel = tel;
        this.email = email;
        this.specialite = specialite;
    }




}
