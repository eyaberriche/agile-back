package org.isamm.Agile.model;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

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

    @OneToOne
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

    public void setCloture()
    {
        this.cloture=true;
    }


    public Collection<UserStory> getUs() {
        return us;
    }

    public void setUs(Collection<UserStory> us) {
        this.us = us;
    }
}
