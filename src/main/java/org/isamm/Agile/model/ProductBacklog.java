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
	private Integer id;
	private String name;

    public ProductBacklog(String name) {
        this.name = name;
    }
}
