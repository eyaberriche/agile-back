package org.isamm.Agile.model;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor


public class ProductBacklog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private boolean cloture=false;

    @OneToMany(mappedBy="backlog",orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Collection<UserStory> us;



    public ProductBacklog(String name, Project project) {
        this.name = name;
        this.project = project;
    }

    @OneToOne (cascade=CascadeType.ALL)
    private Project project;

    public ProductBacklog(String name) {
        this.name = name;
    }

    public ProductBacklog(Long i) {
        super();
        this.id = i;
    }



    public boolean isCloture()
    {
        return this.cloture;
    }

    public void setCloture(boolean etat)
    {
        this.cloture=etat;
    }


    public Collection<UserStory> getUs() {
        return us;
    }

    public void setUs(Collection<UserStory> us) {
        this.us = us;
    }


}
