package org.isamm.Agile.model.DTOs;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartementDTO implements Comparable<DepartementDTO> {
    private Integer id;
    private String libDep;

    @Override
    public int compareTo(DepartementDTO o) {
    return libDep.compareToIgnoreCase(o.getLibDep());
    }


}
