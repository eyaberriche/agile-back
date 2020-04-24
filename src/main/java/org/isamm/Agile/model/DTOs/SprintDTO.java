package org.isamm.Agile.model.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SprintDTO  implements Comparable<SprintDTO> {
    private Long id;
    private String nomSp;
    private Double dureSp;
    private Date dateSp;
    private String objectif;
    private String planification;
    private Collection <EvenementDTO> evenements;
   @Override
    public int compareTo(SprintDTO o) {
        return nomSp.compareToIgnoreCase(o.getNomSp());
    }
}
