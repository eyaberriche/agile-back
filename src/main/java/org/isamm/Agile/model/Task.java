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
   @Column(length = 60)
   private StatusTask status;
   @ManyToOne(fetch=FetchType.LAZY)
   private UserStory userStory ;

   private LocalDate creationDate;
   private LocalDate endDate ;
   @ManyToOne(fetch=FetchType.LAZY)
   private User user ;




}
