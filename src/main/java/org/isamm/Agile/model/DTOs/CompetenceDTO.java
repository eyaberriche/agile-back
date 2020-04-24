package org.isamm.Agile.model.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetenceDTO implements Comparable<CompetenceDTO > {
    private Long idC;
    private String nomC;

    @Override
    public int compareTo(CompetenceDTO o) {
        return nomC.compareToIgnoreCase(o.getNomC());
    }
}
