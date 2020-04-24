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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idBL;
	private String nomBL;


	
   
   
}
