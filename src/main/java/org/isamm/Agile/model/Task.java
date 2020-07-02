package org.isamm.Agile.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Task implements Serializable {
   @Id 
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String title;
   private String content;
   @Enumerated(EnumType.STRING)
   //@NaturalId
  // @Column(length = 60)
   //@JoinColumn(name="state")
   private StatusTask status;
   @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserStory.class)
   private UserStory userStory ;

   private LocalDateTime creationDate;
   private LocalDateTime estimationDate ;
   private LocalDateTime endDate ;//tb3t alik hh gghhhh maalma :p taarf bahdheya jiren yadhhkou w yahkiw narvzouni 3al e5er hhhhhhhhhhhhhhhhhhhhhhhhhhhhh
   private boolean cloture=false;
   public boolean isCloture()
   {
      return this.cloture;
   }

   public void setCloture(boolean etat)
   {
      this.cloture=etat;
   }
   @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
   private User user ;




}
