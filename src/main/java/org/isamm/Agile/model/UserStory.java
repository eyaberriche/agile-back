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
   private Long periorite;

    @ManyToOne
    private ProductBacklog backlog ;
    @ManyToOne(fetch=FetchType.LAZY)
    private Sprint sprint ;
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

    public Long getPeriorite() {
        return periorite;
    }

    public void setPeriorite(Long periorite) {
        this.periorite = periorite;
    }

    public UserStory(String name, Long periorite, ProductBacklog backlog, Sprint sprint) {
        this.name = name;
        this.periorite = periorite;
        this.backlog = backlog;
        this.sprint = sprint;
    }
}
