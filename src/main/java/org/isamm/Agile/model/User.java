package org.isamm.Agile.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private String specialite;

    /**/


    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    public User(String username, String password, String firstname, String lastname, String tel, String email, Entreprise entreprise) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.tel = tel;
        this.email = email;
        this.entreprise = entreprise;
    }

    //@JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    private Entreprise entreprise;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Competence> competences = new HashSet<>();


    public User(String username, String password, String firstname, String lastname, String tel, String email, String specialite, Set<Competence> competences, Entreprise entreprise) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.tel = tel;
        this.email = email;
        this.specialite = specialite;
        this.competences = competences;
        this.entreprise = entreprise;
    }

    public User(String username, String password, String firstname, String lastname, String tel, String email, String specialite, Set<Role> roles, Entreprise entreprise, Set<Competence> competences) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.tel = tel;
        this.email = email;
        this.specialite = specialite;
        this.roles = roles;
        this.entreprise = entreprise;
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
