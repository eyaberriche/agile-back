package org.isamm.Agile.model;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Task implements Serializable {
   @Id 
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   private String name;
   
   @ManyToOne(fetch=FetchType.EAGER)
   private UserStory userStory ;
   
   @ManyToOne(fetch=FetchType.EAGER)
   private User user ;




}
