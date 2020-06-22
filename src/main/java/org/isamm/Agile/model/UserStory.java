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

public class UserStory implements Serializable{
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String name;
   private int periorite;

    @ManyToOne
    private ProductBacklog backlog ;
    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Sprint sprint ; //haka bch yhot sprint win el fazet mta3 el sprint eli kona fiha win tselectionni .. attribut adi nnn nes2ll fk 3al page win mta3 ajout sprint maw kotlk mch frd method

    public UserStory(String name) {
        super();
        this.name = name;
    }

    public UserStory(String name, ProductBacklog backlog, Sprint sprint) {
        super();
        this.name = name;
        this.backlog = backlog;
        this.sprint = sprint;
    }
    @JsonIgnore
    public ProductBacklog getBacklog() {
        return backlog;
    }
    @JsonIgnore
      public Sprint getSprint() {
        return sprint;
    }

    public int getPeriorite() {
        return periorite;
    }

    public void setPeriorite(int periorite) {
        this.periorite = periorite;
    }

    public UserStory(String name, int periorite, ProductBacklog backlog, Sprint sprint) {
        this.name = name;
        this.periorite = periorite;
        this.backlog = backlog;
        this.sprint = sprint;
    }
}
