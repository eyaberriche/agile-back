package org.isamm.Agile.model.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.isamm.Agile.model.ObjName;

import java.util.Collection;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvenementDTO  implements Comparable<EvenementDTO> {

    private Long idEv;
    private String nomEv;
    private Date datedebEv;
    private Date datefinEv;
    private ObjName objective ;

    public Collection<SprintDTO> sprints;
    @Override
    public int compareTo(EvenementDTO o) {
        return nomEv.compareToIgnoreCase(o.getNomEv());
    }
}
