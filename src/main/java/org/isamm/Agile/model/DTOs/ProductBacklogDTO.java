package org.isamm.Agile.model.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductBacklogDTO implements Comparable<ProductBacklogDTO> {

    private Integer idBL;
    private String nomBL;
    @Override
    public int compareTo(ProductBacklogDTO o)
    {
        return nomBL.compareToIgnoreCase(o.getNomBL());
    }
}
