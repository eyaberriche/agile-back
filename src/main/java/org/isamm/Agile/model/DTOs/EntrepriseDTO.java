package org.isamm.Agile.model.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.isamm.Agile.model.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntrepriseDTO implements Comparable<EntrepriseDTO> {
    private Integer id;
    private String nomE;
    private String adresseE;

   @Override
    public int compareTo(EntrepriseDTO o) {
        return nomE.compareToIgnoreCase(o.getNomE());
    }
}
