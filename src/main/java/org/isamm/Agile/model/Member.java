package org.isamm.Agile.model;



import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;


public class Member extends User{

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Competence> competences = new HashSet<>();

    public Member(Set<Competence> competences) {
        this.competences = competences;
    }
}
